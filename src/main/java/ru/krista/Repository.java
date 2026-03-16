package ru.krista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Optional;

public class Repository
{
    private final String url;
    private final String user;
    private final String password;

    public Repository(String url, String user, String password)
    {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void createTable()
    {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement statement = con.createStatement())
        {
            String sql = "CREATE TABLE IF NOT EXISTS crista_registry (\n" +
                         "  id INTEGER NOT NULL PRIMARY KEY,\n" +
                         "  info TEXT,\n" +
                         "  activities TEXT,\n" +
                         "  authorities TEXT,\n" +
                         "  heads TEXT,\n" +
                         "  facialAccounts TEXT,\n" +
                         "  foAccounts TEXT,\n" +
                         "  contract TEXT,\n" +
                         "  participantPermissions TEXT,\n" +
                         "  nonParticipantPermissions TEXT,\n" +
                         "  procurementPermissions TEXT,\n" +
                         "  acceptAuths TEXT,\n" +
                         "  transfauth TEXT,\n" +
                         "  ubpTransFauthBP TEXT,\n" +
                         "  successions TEXT,\n" +
                         "  contacts TEXT,\n" +
                         "  ubpfinfku TEXT,\n" +
                         "  ubpfin TEXT,\n" +
                         "  ubpTransfAuthBU TEXT,\n" +
                         "  ksAccounts TEXT\n);";
            statement.executeUpdate(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void insertRecord(int id, Map<String, Object> data)
    {
        String sql = "INSERT INTO crista_registry (id, info, activities, authorities, heads," +
                     "facialAccounts, foAccounts, contract, participantPermissions, nonParticipantPermissions," +
                     "procurementPermissions, acceptAuths, transfauth, ubpTransFauthBP, successions," +
                     "contacts, ubpfinfku, ubpfin, ubpTransfAuthBU, ksAccounts) \n" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                     "ON CONFLICT (id) DO UPDATE SET info = EXCLUDED.info, activities = EXCLUDED.activities," +
                     "authorities = EXCLUDED.authorities, heads = EXCLUDED.heads," +
                     "facialAccounts = EXCLUDED.facialAccounts, foAccounts = EXCLUDED.foAccounts," + 
                     "contract = EXCLUDED.contract, participantPermissions = EXCLUDED.participantPermissions," +
                     "nonParticipantPermissions = EXCLUDED.nonParticipantPermissions," +
                     "procurementPermissions = EXCLUDED.procurementPermissions, acceptAuths = EXCLUDED.acceptAuths," + 
                     "transfauth = EXCLUDED.transfauth, ubpTransFauthBP = EXCLUDED.ubptransfauthbp," + 
                     "successions = EXCLUDED.successions," +
                     "contacts = EXCLUDED.contacts, ubpfinfku = EXCLUDED.ubpfinfku, ubpfin = EXCLUDED.ubpfin," + 
                     "ubpTransfAuthBU = EXCLUDED.ubptransfauthbu, ksAccounts = EXCLUDED.ksaccounts;";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = con.prepareStatement(sql))
        {
            statement.setInt(1, id);
            statement.setString(2, Optional.ofNullable(data.get("info")).map(Object::toString).orElse(""));
            statement.setString(3, Optional.ofNullable(data.get("activities")).map(Object::toString).orElse(""));
            statement.setString(4, Optional.ofNullable(data.get("authorities")).map(Object::toString).orElse(""));
            statement.setString(5, Optional.ofNullable(data.get("heads")).map(Object::toString).orElse(""));
            statement.setString(6, Optional.ofNullable(data.get("facialAccounts")).map(Object::toString).orElse("")); 
            statement.setString(7, Optional.ofNullable(data.get("foAccounts")).map(Object::toString).orElse(""));
            statement.setString(8, Optional.ofNullable(data.get("contracts")).map(Object::toString).orElse(""));
            statement.setString(9, Optional.ofNullable(data.get("participantPermissions")).map(Object::toString).orElse("")); 
            statement.setString(10, Optional.ofNullable(data.get("nonParticipantPermissions")).map(Object::toString).orElse("")); 
            statement.setString(11, Optional.ofNullable(data.get("procurementPermissions")).map(Object::toString).orElse(""));
            statement.setString(12, Optional.ofNullable(data.get("acceptAuths")).map(Object::toString).orElse(""));
            statement.setString(13, Optional.ofNullable(data.get("transfauth")).map(Object::toString).orElse(""));
            statement.setString(14, Optional.ofNullable(data.get("ubptransfauthbp")).map(Object::toString).orElse("")); 
            statement.setString(15, Optional.ofNullable(data.get("successions")).map(Object::toString).orElse(""));
            statement.setString(16, Optional.ofNullable(data.get("contacts")).map(Object::toString).orElse(""));
            statement.setString(17, Optional.ofNullable(data.get("ubpfinfku")).map(Object::toString).orElse(""));
            statement.setString(18, Optional.ofNullable(data.get("ubpfin")).map(Object::toString).orElse(""));
            statement.setString(19, Optional.ofNullable(data.get("ubptransfauthbu")).map(Object::toString).orElse("")); 
            statement.setString(20, Optional.ofNullable(data.get("ksaccounts")).map(Object::toString).orElse(""));

            statement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // public void createTable()
    // {
    //     try (Connection con = DriverManager.getConnection(url, user, password);
    //          Statement statement = con.createStatement())
    //     {
    //         String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
    //                      "  id SERIAL PRIMARY KEY,\n" +
    //                      "  username VARCHAR(50),\n" +
    //                      "  email VARCHAR(100)\n);";
    //         statement.executeUpdate(sql);
    //     }
    //     catch (Exception e)
    //     {
    //         e.printStackTrace();
    //     }
    // }

    // public void insertRecord(String username, String email)
    // {
    //     try (Connection con = DriverManager.getConnection(url, user, password);
    //          PreparedStatement statement = con.prepareStatement(
    //             "INSERT INTO users(username, email) VALUES(?, ?)"
    //          ))
    //     {
    //         statement.setString(1, username);
    //         statement.setString(2, email);
    //         statement.executeUpdate();
    //     }
    //     catch (Exception e)
    //     {
    //         e.printStackTrace();
    //     }
    // }

    // public void selectTable()
    // {
    //     try (Connection con = DriverManager.getConnection(url, user, password);
    //          Statement statement = con.createStatement();
    //          ResultSet resultSet = statement.executeQuery("SELECT * FROM users"))
    //     {
    //         while (resultSet.next())
    //         {
    //             int id = resultSet.getInt("id");
    //             String username = resultSet.getString("username");
    //             String email = resultSet.getString("email");
    //             System.out.println("ID: " + id + ", Username: " + username + ", Email: " + email);
    //             System.out.println("============================================================");
    //         }
    //     }
    //     catch (Exception e)
    //     {
    //         e.printStackTrace();
    //     }
    // }

    // public void addOrUpdateRecord(String key, String value)
    // {

    // }
}