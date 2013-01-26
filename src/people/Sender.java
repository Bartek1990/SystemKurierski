package people;


import com.sun.rowset.CachedRowSetImpl;
import exceptions.AlreadyInDbException;

import java.sql.SQLException;

public class Sender extends Person {

    Sender(String login, String pass, String tel, String imie,
           String nazwisko, String nip, String adres, String kod,
           String kraj, String miasto, String mail, String corp, String reg) throws AlreadyInDbException
    {
        if (login.equals("") || pass.equals("") || imie.equals("") ||
                nazwisko.equals("") || nip.equals("") || adres.equals("") ||
                kod.equals("") || miasto.equals("") || kraj.equals("") || tel.equals("") || mail.equals("") ||
                corp.equals("") || reg.equals("") )
        {
            //rzuć coś

        }
        else
        {
            try
            {
                if(Client.request("SELECT * FROM user WHERE login = '" + login + "'").first())
                {
                    throw new AlreadyInDbException();
                }
                else
                {
                    String krajid = null;
                    //sprawdzenie czy podany kraj juz jest wpisany
                    CachedRowSetImpl tmp = Client.request("SELECT countryid FROM country WHERE name = '" + kraj + "' LIMIT 1");
                    if(tmp.first())
                    {
                        krajid = tmp.getString(1);
                    }
                    else
                    {
                        //rzuć brak takiego kraju w bazie
                    }

                    Client.request("INSERT INTO data (name, countryid, details, zipcode, city, tel, mail) " +
                            "VALUES ('" + imie + " " + nazwisko + "'," + krajid + ",'" + adres + "','"
                            + kod + "','" + miasto + "','" + tel + "','" + mail + "')");
                    //checkMessage(request("select max(countryid) from country"));
                    Client.request("INSERT INTO user (login, password, dataid, nip) VALUES ('" + login + "','" + pass + "',(SELECT MAX(dataid) FROM data),'" + nip + "')");
                    Client.request("INSERT INTO person VALUES ((SELECT MAX(userid) FROM user),'" + imie + "','" + nazwisko + "')");
                    Client.request("INSERT INTO corporation VALUES ((SELECT MAX(userid) FROM user),'" + corp + "','" + reg + "')");
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void followPackage(int packageID) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    //tymczasowo void żeby nie wyrzucał błędu
    //dane podawane przez tą funkcje to dane odbiorcy
    public void sendPackage(String name, String country, String details, String zipCode, String city, String tel, String... mail)
    {



    }
}
