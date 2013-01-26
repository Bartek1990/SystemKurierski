package people;

import java.io.*;
import java.net.*;
import java.sql.*;
import com.sun.rowset.*;
// Komentarz!
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
    public static CachedRowSetImpl request(String kw)
    {
        CachedRowSetImpl message = null;

        try
        {
            Socket polaczenie = new Socket(HOST, PORT);
            System.out.println("Nawiazalem polaczenie: ");

            ObjectOutputStream write = new ObjectOutputStream(polaczenie.getOutputStream());
            ObjectInputStream read = new ObjectInputStream(polaczenie.getInputStream());


            //wysylanie zapytania
            write.writeObject(kw);
            //odbior odpowiedzi
            Object test = read.readObject();

            if(test instanceof String)
            {
                System.out.println(test); //jesli to nie bylo zapytanie select to dostaniemy tylko stringa z potwierdzeniem. Trzeba obsluzyc sprawdzenie tego, zeby nie rzutowac Stringa na CachedRowSetImpl.
            }
            else
            {
                message = (CachedRowSetImpl) test;
            }

            write.close();
            read.close();
            polaczenie.close();

        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
			/*catch (SQLException e)
            {
                e.printStackTrace();
            }*/

        return message;
    }
}