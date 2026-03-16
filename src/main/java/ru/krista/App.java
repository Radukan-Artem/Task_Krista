package ru.krista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ru.krista.Service;
// import ru.krista.Repository;

/**
 * Hello world!
 *
 */
public class App 
{
    private static void checkDates(String lastUpdateFrom, String lastUpdateTo)
    {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try{
            LocalDate lastUpdateFromDate = LocalDate.parse(lastUpdateFrom, dateFormat);
            LocalDate lastUpdateToDate = LocalDate.parse(lastUpdateTo, dateFormat);

            if (lastUpdateFromDate.isAfter(lastUpdateToDate)) 
            {
                throw new IllegalArgumentException("lastUpdateFrom must be less or equal than to lastUpdateTo");
            }
        }
        catch (DateTimeParseException e)
        {
            System.err.println("Invalid date format. Use dd.MM.yyyy");
            System.exit(1);
        }
        catch (IllegalArgumentException e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main( String[] args )
    {
        if (args.length != 2)
        {
            System.err.println("To start: java -jar target/taskkrista-1.0-SNAPSHOT.jar <lastUpdateFrom> <lastUpdateTo>. Format: 'dd.MM.yyyy'.");
            System.exit(1);
        }

        String lastUpdateFrom = args[0]; 
        String lastUpdateTo = args[1];

        checkDates(lastUpdateFrom, lastUpdateTo);

        // System.out.println("lastUpdateFrom: " + lastUpdateFrom);
        // System.out.println("lastUpdateTo: " + lastUpdateTo);

        Service service = new Service(lastUpdateFrom, lastUpdateTo);
        service.loadData();


        // System.out.println( "Hello World!" );


        // Repository repo = new Repository("jdbc:postgresql://db:5432/taskkristadb", "taskkrista_user", "password");

        // try
        // {
        //     repo.createTable();

        //     repo.insertRecord("john_doe", "john@example.com");
        //     repo.insertRecord("alice_smith", "alice@example.com");

        //     repo.selectTable();
        // }
        // catch (Exception e)
        // {
        //     e.printStackTrace();
        // }
    }
}
