package ru.krista;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.*;


public class XMLConverter
{
    private ObjectMapper jsonMapper = new ObjectMapper();

    public String convert(String full_json, Class<?> itemClass)
    {
        try
        {
            Map<String, Object> map = jsonMapper.readValue(full_json, HashMap.class);
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) map.get("data");

            List<Object> pojoList = new ArrayList<>();
            for (Map<String, Object> itemMap : listOfMaps)
            {
                Map<String, String> stringMap = new HashMap<>();
                for (Map.Entry<String, Object> entry : itemMap.entrySet())
                {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof Map || value instanceof List)
                    {
                        stringMap.put(key, jsonMapper.writeValueAsString(value));
                    }
                    else
                    {
                        stringMap.put(key, String.valueOf(value));
                    }
                }

                Object pojo = jsonMapper.convertValue(stringMap, itemClass);
                pojoList.add(pojo);
            }

            DataResponse response = new DataResponse(pojoList);

            JAXBContext context = JAXBContext.newInstance(DataResponse.class, itemClass);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            marshaller.marshal(response, sw);
            return sw.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}