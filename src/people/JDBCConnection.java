package people;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.Serializable;
import com.sun.rowset.*;

public class JDBCConnection implements Serializable
{
    private Connection connect = null; //dane wymagane do polaczenia sie z baza danych
    private Statement statement = null; //zapytanie do bazy (metoda: executeQuery i executeUpdate)
    private ResultSet resultSet = null; //tutaj zapisywany jest wynik kwerendy
    private String driver = "com.mysql.jdbc.Driver"; //sterownik do JDBC MySQL. Sterownik musi byc zaimportowany do projektu!
    String userName = "root";
    String password = "";
    String serverName = "localhost";
    String portNumber = "3306";
    String databaseName = "PDS";


    public ResultSet readDataBase(String query) throws Exception
    {
        try
        {
            //polaczenie z baza
            Class.forName(driver);
            connect = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + databaseName, userName, password);
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            CachedRowSetImpl crs = new CachedRowSetImpl(); //obiekt dzieki ktoremu bedziemy mogli przesłać resulSet, upewnij się że masz sciagniety rowset.jar z gita
            crs.populate(resultSet); //zamieniamy resultSet na crs, dziala tu te same metody co na resultSet ale jest serializowalny i mozna przeslac strumieniem bitów.
            return crs;
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            close();
        }
    }
    public String executeDataBase(String query) throws Exception
    {
        try
        {
            //polaczenie z baza
            Class.forName(driver);
            connect = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + databaseName, userName, password);
            statement = connect.createStatement();
            int updateQuery = statement.executeUpdate(query);
            String potwierdzenie = null;
            if (updateQuery != 0)
            {
                potwierdzenie = "Baza została zmodyfikowana";
            }
            else if (updateQuery == 0)
            {
                potwierdzenie = "Baza nie została zmodyfikowana, wystąpił błąd";
            }
            return potwierdzenie;
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            close();
        }

    }
    //metoda do zamkniecia polaczenia
    private void close()
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }

            if (statement != null)
            {
                statement.close();
            }

            if (connect != null)
            {
                connect.close();
            }
        }
        catch (Exception e)
        {

        }
    }

} 




