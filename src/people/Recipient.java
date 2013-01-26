package people;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

public class Recipient extends Person {

    int recipentId;
    public Recipient(String name, String country, String details, String zipCode, String city, String tel, String... mail)
    {
        String krajid = null;
        //sprawdzenie czy podany kraj juz jest wpisany
        CachedRowSetImpl tmp = Client.request("SELECT countryid FROM country WHERE name = '" + country + "' LIMIT 1");
        try {
            if(tmp.first())
            {
                krajid = tmp.getString(1);
            }
            else
            {
                //rzuć brak takiego kraju w bazie
            }

            if(mail == null)
            {
                Client.request("INSERT INTO data (name, countryid, details, zipcode, city, tel, mail) " +
                        "VALUES ('" + name +"'," + krajid + ",'" + details + "','"
                        + zipCode + "','" + city + "','" + tel + "',null)");
            }
            else
            {
                Client.request("INSERT INTO data (name, countryid, details, zipcode, city, tel, mail) " +
                        "VALUES ('" + name +"'," + krajid + ",'" + details + "','"
                        + zipCode + "','" + city + "','" + tel + "','" + mail + "')");
            }
            tmp = Client.request("SELECT MAX(dataid) FROM data");
            tmp.first();

            this.recipentId = Integer.parseInt(tmp.getString(1));//tutaj odpowiedź z serwera.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    @Override
    public void followPackage(int packageID) {


    }
    public boolean confirmPickup(Pack pack){
        return false;
    }
    public void logIn(String login, String password){

    }
}
