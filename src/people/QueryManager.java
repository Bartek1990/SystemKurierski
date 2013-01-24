package people;
//watek uruchamiany z klasy Server. Obsluguje polaczenie z baza danych
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/*
 * Jak rozumieć QueryManager: klasa służąca do obsługi wszelakiego rodzaju zapytań przychodzących od klientów, 
 * włącznie z zapytaniami o aktualne koordynaty gps.
 */
public class QueryManager implements Runnable
{
    private Socket socket;
    //private Courier courier;
    //private Transaction trans;
    public QueryManager(Socket socket) throws IOException
    {
        System.out.println("Wątek uruchomiony");
        this.socket = socket;
        //this.trans = trans;
        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void run()
    {

        Object czytaj = null;
        try
        {
            //odczyt
            ObjectInputStream socketReader = new ObjectInputStream(socket.getInputStream()); //użyłem ObjectInput bo jest wygodniejszy
            ObjectOutputStream write = new ObjectOutputStream(socket.getOutputStream());
            czytaj = socketReader.readObject();
            System.out.println("Otrzymano wiadomość: " + czytaj + " ... Testuję czy obsłużyć zapytanie SQL czy coordy kuriera");
            //przerabiam w celu zidentyfikowania czy to klient czy kurier
            /*
            if((czytaj instanceof Courier))
            {
                System.out.println("Kurier odpowiada...");
                //część inicjacji dla kuriera
                courier = (Courier) czytaj;
                int tempC = trans.courierCounter;
                trans.couriers[trans.courierCounter] = courier;
                trans.courierCounter++;
                int flag;

                do
                {
                    flag = trans.getFlag();
                    czytaj = socketReader.readObject();
                    trans.couriers[tempC] = (Courier) czytaj;
                    if(flag == courier.getIdNumber())
                    {
                        write.writeObject(courier);
                    }
                    System.out.println("KOORDY: " + trans.couriers[tempC].getCoords());
                } while(czytaj != null);


            } */
            if (czytaj instanceof String)
            {
                JDBCConnection query = new JDBCConnection();
                //część inicjacji dla klienta
                String klient = (String) czytaj;
                //odpowiedź
                if(klient.equals("coords"))
                {
                    System.out.println("Jest to wiadomość dla kuriera");
                    //trans.setFlag(1);
                   // System.out.println(trans.readCoords(1));
                    //write.writeObject(trans.readCoords(1));
                } else
                {
                    System.out.println("Jest to wiadomość dla bazy danych");
                    if(klient.substring(0,6).equals("select"))
                    {
                        write.writeObject(query.readDataBase(klient));
                    }
                    else
                    {
                        write.writeObject(query.executeDataBase(klient));
                    }

                }
            }

            socketReader.close();
            write.close();
            socket.close();
            System.out.println("Czekam na wiadomosc...");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}