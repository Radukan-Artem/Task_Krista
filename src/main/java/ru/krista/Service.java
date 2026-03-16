package ru.krista;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ru.krista.Repository;
import ru.krista.Archiver;
import ru.krista.XMLConverter;
import ru.krista.DataItem;
import ru.krista.DataResponse;

public class Service
{
    private final String url;
    private final String lastUpdateFrom;
    private final String lastUpdateTo;
    private final Repository repository;
    private final Archiver archiver;
    private final XMLConverter converter;

    public Service(String lastUpdateFrom, String lastUpdateTo)
    {
        this.url = "https://budget.gov.ru/epbs/registry/ubpandnubp/data";
        this.lastUpdateFrom = lastUpdateFrom;
        this.lastUpdateTo = lastUpdateTo;
        this.repository = new Repository("jdbc:postgresql://db:5432/taskkristadb", "taskkrista_user", "password");
        try
        {
            repository.createTable();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        this.archiver = new Archiver("/archive", "kristaData.zip");
        this.converter = new XMLConverter();
    }

    private int getCountPages(CloseableHttpClient httpClient)
    {
        HttpGet request = new HttpGet(url + "?filterminloaddate=" + lastUpdateFrom + "&filtermaxloaddate=" + lastUpdateTo + "&pageSize=1");
        int countRecords = 0;
        int countPages = 0;

        try (CloseableHttpResponse response = httpClient.execute(request))
        {
            String result = EntityUtils.toString(response.getEntity());

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(result, HashMap.class);

            countRecords = ((Integer) map.get("recordCount")).intValue();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        if (countRecords > 0)
        {
            countPages = (int) Math.ceil((double) countRecords / 1000);
        }

        return countPages;
    }

    private void saveFiles(String text, int number_page)
    {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        String nameFile = "page_" + number_page + "_" + lastUpdateFrom + "_" + lastUpdateTo + "_" + timestamp;

        archiver.addtoArchive(text, nameFile + ".json");

        try 
        {
            String xmlData = converter.convert(text, DataItem.class);
            archiver.addtoArchive(xmlData, nameFile + ".xml");
        }
        catch (Exception e)
        {
            System.err.println("Failed to convert JSON to XML: " + e.getMessage());
        }
    }

    public void loadData()
    {
        try (CloseableHttpClient httpClient = HttpClients.createDefault())
        {
            int countPages = getCountPages(httpClient);
            for (int i = 1; i <= countPages; i++)
            {
                HttpGet page = new HttpGet(url + "?filterminloaddate=" + lastUpdateFrom + "&filtermaxloaddate=" + lastUpdateTo + "&pageSize=1000&page=" + i);

                try (CloseableHttpResponse response = httpClient.execute(page))
                {
                    String result = EntityUtils.toString(response.getEntity());
                    ObjectMapper mapper = new ObjectMapper();
                    
                    saveFiles(result, i);

                    Map<String, Object> map = mapper.readValue(result, HashMap.class);

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) map.get("data");
                    for (Map<String, Object> elem: listOfMaps)
                    // for (int j = 0; j < listOfMaps.size(); j++)
                    {
                        int id_record = Integer.parseInt((String) elem.get("id"));
                        repository.insertRecord(id_record, elem);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}