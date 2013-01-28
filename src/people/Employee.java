package people;

import com.sun.rowset.CachedRowSetImpl;
import exceptions.AlreadyInDbException;

import java.sql.SQLException;

public class Employee extends Person{

    int employeeId;
    Employee(Employee pracownik) throws AlreadyInDbException
    {
        this.employeeId = pracownik.employeeId;
    }

    //dataCountryId będzie stałe ze względu na to, że obsługujemy tylko jeden kraj!
    //baseid również będzie stałe dlatego że mamy tylko jedną placówkę
    public Employee(String login, String password, String forename, String surename, String earnings, String workTime, String nip,
              String account, String DataName, String dataDetails, String zipCode, String city, String tel, String mail) throws AlreadyInDbException {

        try {
            if(Client.request("SELECT * FROM employee WHERE login = '" + login + "'").first())
            {
                throw new AlreadyInDbException();
            }
            else
            {
                int dataCountryId = 12; // zmienić gdyby w bazie był inny wpis
                int baseid = 1; //stałe dlatego że mamy tylko jedną placówkę
                Client.request("INSERT INTO data VALUES" +
                        "(0,\""+DataName+"\",\""+dataCountryId+"\",\""+dataDetails+"\",\""+zipCode+"\",\""+city+"\",\""+tel+"\",\""+mail+"\")");

                CachedRowSetImpl tmp = Client.request("SELECT MAX(dataid) FROM data");
                tmp.first();
                int dataId = Integer.parseInt(tmp.getString(1));

                Client.request("INSERT INTO employee VALUES " +
                        "(0,\""+login+"\",\""+password+"\",\""+forename+"\",\""+surename+"\",\""+dataId+"\",CURRENT_TIMESTAMP,\""+earnings+"\"" +
                        ",\""+baseid+"\",\""+workTime+"\",\"0\",\""+nip+"\",\""+account+"\")");

                tmp = Client.request("SELECT MAX(employeeid) FROM employee");


                this.employeeId = Integer.parseInt(tmp.getString(1));//tutaj odpowiedź z serwera.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //pracownik sprawdza paczkę zanim dotrze do kuriera  zwraca 0 jeżeli nie przeszła testu. Zwraca cenę jeżeli przeszła
    public int checkPackage(Pack pack)
    {
        int price = 0;
        //daj mi do 2 zmiennych poniższe parametry z pól tekstowych
        int weight = 0;
        int status = 0;
        //weight: parsuj mi tu inta z pierwszego pola formularza
        //status : pole rozwijane z wszystkimi możliwymi opcjami statusu, zwróć mi do tego id statusu.


        CachedRowSetImpl tmp = Client.request("SELECT * FROM weight WHERE intervalf <= \'"+weight+"\' AND intervalt > \'"+weight+"\'");
        try {

            price = Integer.parseInt(tmp.getString("price"));
            tmp = Client.request("SELECT * FROM shipment WHERE shipmentid=\'"+pack.shipmentId+"\'");


            int serviceId = Integer.parseInt(tmp.getString("serviceid"));
            int paymentId = Integer.parseInt(tmp.getString("paymentid"));

            tmp = Client.request("SELECT * FROM payment WHERE paymentid=\'"+paymentId+"\'");
            int payToPrice = Integer.parseInt(tmp.getString("price"));
            price = price+(price*payToPrice);

            tmp = Client.request("SELECT * FROM service WHERE serviceid=\'"+serviceId+"\'");
            int procToPrice = Integer.parseInt(tmp.getString("price"));
            price = price+(price*procToPrice);
            //to już ostateczna cena

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return price;
    }

}