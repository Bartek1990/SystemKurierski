package people;

import java.io.*;
import java.net.*;
/*
 * Server jest wielowątkowy. Jednak po odpowiedzi zamyka strumień klienta.
 */
public class Oddzial
{
    private ServerSocket server;
    private int port = 8867;
   // private Transaction trans;

    public Oddzial() //konstruktor który tworzy serwer
    {
        try {

            //this.trans = new Transaction();
            server = new ServerSocket(port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void handleCon()
    {
        System.out.println("Oczekiwanie na połączenie...");
        while(true)
        {
            try {
                Socket socket = server.accept();
                System.out.println("Zaakceptowano połączenie");
                new QueryManager(socket);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] argh)
    {
        Oddzial oddzial = new Oddzial();
        oddzial.handleCon();
    }
}