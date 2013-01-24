package people;


import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

public class Sender extends Person {

    Sender(String login, String pass, String tel, String imie,
           String nazwisko, String nip, String adres, String kod,
           String kraj, String miasto, String mail, String corp, String reg)
    {
        if (login.equals("") || pass.equals("") || imie.equals("") ||
                nazwisko.equals("") || nip.equals("") || adres.equals("") ||
                kod.equals("") || miasto.equals("") || kraj.equals("") || tel.equals("") || mail.equals("") ||
                corp.equals("") || reg.equals("") )
        {
            //rzuć błąd

        }
        else
        {
            try
            {
                if(Client.request("select * from user where login = '" + login + "'").first())
                {
                    //rzuć błąd bo jest już w bazie
                }
                else
                {

                    String krajid = null;
                    //sprawdzenie czy podany kraj juz jest wpisany
                    CachedRowSetImpl tmp = Client.request("select countryid from country where name = '" + kraj + "' limit 1");
                    if(tmp.first())
                    {
                        krajid = tmp.getString(1);
                    }
                    else
                    {
                        //rzuć brak takiego kraju w bazie
                    }

                    Client.request("insert into data (name, countryid, details, zipcode, city, tel, mail) " +
                            "values ('" + imie + " " + nazwisko + "'," + krajid + ",'" + adres + "','"
                            + kod + "','" + miasto + "','" + tel + "','" + mail + "')");
                    //checkMessage(request("select max(countryid) from country"));
                    Client.request("insert into user (login, password, dataid, nip) values ('" + login + "','" + pass + "',(select max(dataid) from data),'" + nip + "')");
                    Client.request("insert into person values ((select max(userid) from user),'" + imie + "','" + nazwisko + "')");
                    Client.request("insert into corporation values ((select max(userid) from user),'" + corp + "','" + reg + "')");


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
 /*   public Pack sendPackage(){
        Pack pack;
        return pack;
    }         */
}
