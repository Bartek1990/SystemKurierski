package people;


import com.sun.rowset.CachedRowSetImpl;
import exceptions.AlreadyInDbException;
import exceptions.NoCountryException;

import java.sql.SQLException;

public class Sender extends Person {
    int senderId;

    public Sender(){

    }

    public Sender(String login, String pass, String tel, String imie,
           String nazwisko, String nip, String adres, String kod,
           String kraj, String miasto, String mail, String corp, String reg) throws AlreadyInDbException, NoCountryException
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
                        throw new NoCountryException();
                    }

                    Client.request("INSERT INTO data (name, countryid, details, zipcode, city, tel, mail) " +
                            "VALUES ('" + imie + " " + nazwisko + "'," + krajid + ",'" + adres + "','"
                            + kod + "','" + miasto + "','" + tel + "','" + mail + "')");
                    //checkMessage(request("select max(countryid) from country"));
                    Client.request("INSERT INTO user (login, password, dataid, nip) VALUES ('" + login + "','" + pass + "',(SELECT MAX(dataid) FROM data),'" + nip + "')");
                    Client.request("INSERT INTO person VALUES ((SELECT MAX(userid) FROM user),'" + imie + "','" + nazwisko + "')");
                    Client.request("INSERT INTO corporation VALUES ((SELECT MAX(userid) FROM user),'" + corp + "','" + reg + "')");

                    tmp = Client.request("SELECT MAX(userid) FROM user");
                    tmp.first();

                    this.senderId = Integer.parseInt(tmp.getString(1));
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


    public Pack sendPackage(String name, String country, String details, String zipCode, String city, String tel, //dotąd dane odbiorcy
                       int serviceId, int weightId, int amount, int paymentId, int statusId, int paid, //dane dla paczki
                            String... mail) throws NoCountryException //pole opcjonalne mail
    {
        //source id to adres klienta czyli user_id
        //return address też jest user_id
        Recipient nowy = null;
        if(mail == null)
        {
            nowy =  new Recipient(name, country, details, zipCode, city, tel);
        }
        else
        {
            nowy = new Recipient(name, country, details, zipCode, city, tel, mail);
        }
        Pack nowa = new Pack(nowy.recipentId, this.senderId, serviceId, weightId, amount, paymentId, statusId, paid);
        return nowa;
    }
}
