import java.io.*;
import java.net.*;
import java.sql.*;
import com.sun.rowset.*;

public class Client
{
    public static final int PORT = 8867;
    public static final String HOST = "localhost";

    //obsługa gps i zapytania insert.
    public String communicationGpsInsert(String arg)
    {
        //nawiazanie polaczenia z serwerem
        try {

            Socket polaczenie = new Socket(HOST, PORT);
            System.out.println("Nawiazalem polaczenie: ");

            ObjectOutputStream write = new ObjectOutputStream(polaczenie.getOutputStream());
            //najzwyczajniej puszczam Stringa i reszta zajmuje sie serwer.
            write.writeObject(arg);

            ObjectInputStream read = new ObjectInputStream(polaczenie.getInputStream());
            CachedRowSetImpl message;
            Object test = read.readObject();
            if(test instanceof String)
            {
                System.out.println(test); //jesli to nie bylo zapytanie select to dostaniemy tylko stringa z potwierdzeniem. Trzeba obsluzyc sprawdzenie tego, zeby nie rzutowac Stringa na CachedRowSetImpl.
                write.close();
                read.close();
                polaczenie.close();
                return (String) test;
            }

            //jedyne zapytanie jakie będzie zwracac zawartosc bazy to takie zaczynajace sie od "select" (substring) jesli chcesz dodac inne to musisz sobie dodac OR w ifie w QueryManager.java.
            write.close();
            read.close();
            polaczenie.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "Błąd...";
    }
    public CachedRowSetImpl communicationMySQL(String arg)
    {
        //nawiazanie polaczenia z serwerem
        try {

            Socket polaczenie = new Socket(HOST, PORT);
            System.out.println("Nawiazalem polaczenie: ");

            ObjectOutputStream write = new ObjectOutputStream(polaczenie.getOutputStream());
            //najzwyczajniej puszczam Stringa i reszta zajmuje sie serwer.
            write.writeObject(arg);

            ObjectInputStream read = new ObjectInputStream(polaczenie.getInputStream());
            CachedRowSetImpl message;
            Object test = read.readObject();

            message = (CachedRowSetImpl) test;
            message.first();
            System.out.println("Wiadomość: " + message.getString(2));   //po stronie klienta pytamy teraz o to co nas interesuje. Dzialaja tu metody te co na obiekcie ResultSet

            //jedyne zapytanie jakie będzie zwracac zawartosc bazy to takie zaczynajace sie od "select" (substring) jesli chcesz dodac inne to musisz sobie dodac OR w ifie w QueryManager.java.
            write.close();
            read.close();
            polaczenie.close();
            return message;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}