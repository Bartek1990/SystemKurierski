package people;

import com.sun.rowset.CachedRowSetImpl;
import exceptions.NoCountryException;

import java.security.PublicKey;
import java.sql.SQLException;

public class Recipient {

    int recipentId;
    Pack recivedPack;
    public Recipient(String name, String country, String details, String zipCode, String city, String tel, String... mail) throws NoCountryException
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
                throw new NoCountryException();
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
    public Recipient(int recipentId)
    {
        this.recipentId = recipentId;
    }

    public boolean confirmPickup(Courier courier)
    {
        this.recivedPack = courier.givePackToRecipent();
        return true;
    }

}
