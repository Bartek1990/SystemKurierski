package com.grzesiek.sql;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.sun.rowset.CachedRowSetImpl;

//tutaj mamy statyczna klase wewnetrzna z statycznymi metodami 
//dostosowanymi do odpowiednich przyciskow z gui. 
// funkcje nazywaja sie tak jak przyciski ktore je wywluja
public abstract class MySql
{
	public static class Do
	{		
		public static final int PORT = 8867;
		public static final String HOST = "localhost";
		
		
		//grzesiek
		//sprawdza login i haslo - jezeli zgadza sie wyswietla jeden wiersz
		public static boolean p1b2(String login, String pass)
		{
			CachedRowSetImpl temp = request("select login, password from user where login = '" + 
												login + "' and password = '" + pass + "'");
			
			//checkMessage(temp);
			
			try
			{
				if (temp.first())
					return true;
				else
					return false;
			}
			catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
			
			return false;
		}
		
		//grzesiek
		//sprawdza po id paczki stan paczki
		public static CachedRowSetImpl p2b2(String id)
		{
			CachedRowSetImpl temp = request("select * from shipment where shipmentid = '" + id + "'");
			
			checkMessage(temp);
			
			return temp;
			
			/*try
			{
				if (temp.first())
					return true;
				else
					return false;
			}
			catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
			
			return false;*/
		}
		
		public static boolean p7b2(String login, String pass, String tel, String imie, 
				String nazwisko, String nip, String adres, String kod, 
				String kraj, String miasto, String mail, String corp, String reg)
		{
			if (login.equals("") || pass.equals("") || imie.equals("") ||
					nazwisko.equals("") || nip.equals("") || adres.equals("") ||
					kod.equals("") || miasto.equals("") || kraj.equals("") || tel.equals("") || mail.equals("") || 
					corp.equals("") || reg.equals("") )
			{
				return false;
			}
			else
			{
				try 
				{
					if(request("select * from user where login = '" + login + "'").first())
					{
						return false;
					}
					else
					{
						
						String krajid;
						request("start TRANSACTION");
						//sprawdzenie czy podany kraj juz jest wpisany
						CachedRowSetImpl tmp = request("select countryid from country where name = '" + kraj + "' limit 1");
						if(tmp.first())
						{
							krajid = tmp.getString(1);
						}
						else
						{
							request("insert into country (name) values ('" + kraj + "')");
							tmp = request("select max(countryid) from country");
							tmp.first();
							krajid = tmp.getString(1);
						}
						
						request("insert into data (name, countryid, details, zipcode, city, tel, mail) " +
								"values ('" + imie + " " + nazwisko + "'," + krajid + ",'" + adres + "','" 
								+ kod + "','" + miasto + "','" + tel + "','" + mail + "')");						
						//checkMessage(request("select max(countryid) from country"));
						request("insert into user (login, password, dataid, nip) values ('" + login + "','" + pass + "',(select max(dataid) from data),'" + nip + "')");
						request("insert into person values ((select max(userid) from user),'" + imie + "','" + nazwisko + "')");
						request("insert into corporation values ((select max(userid) from user),'" + corp + "','" + reg + "')");
						request("COMMIT");

						return true;
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				return false;
			}
		}
		
		//funkcja zwracajaca tablice stringow z kontaktami danego uzytkownika
		public static CachedRowSetImpl p6b6(String login)
		{
			CachedRowSetImpl tmp = request("select name from data where dataid in (select dataid from address_book where userid = " +
					"(select userid from user where login = '" + login + "' limit 1))");
			return tmp;
		}
		
		//funkcja do dodawania kontaktow do ksiazki adresowej
		public static boolean p12b1(String login,String tel, String imie, 
				String nazwisko, String adres, String kod, 
				String kraj, String miasto, String mail)
		{
			if (login.equals("") || imie.equals("") || nazwisko.equals("") || adres.equals("") ||
					kod.equals("") || miasto.equals("") || kraj.equals("") || tel.equals("") || mail.equals(""))
			{
				return false;
			}
			else
			{
				try 
				{
					CachedRowSetImpl userid = request("select userid from user where login = '" + login + "'");
					if(!(userid.first()))
					{
						//zle podano login
						return false;
					}
					else
					{
						
						String krajid;
						request("start TRANSACTION");
						//sprawdzenie czy podany kraj juz jest wpisany
						CachedRowSetImpl tmp = request("select countryid from country where name = '" + kraj + "' limit 1");
						if(tmp.first())
						{
							krajid = tmp.getString(1);
						}
						else
						{
							request("insert into country (name) values ('" + kraj + "')");
							tmp = request("select max(countryid) from country");
							tmp.first();
							krajid = tmp.getString(1);
						}
						
						request("insert into data (name, countryid, details, zipcode, city, tel, mail) " +
								"values ('" + imie + " " + nazwisko + "'," + krajid + ",'" + adres + "','" 
								+ kod + "','" + miasto + "','" + tel + "','" + mail + "')");						
						//checkMessage(request("select max(countryid) from country"));
						request("insert into address_book values ('" + userid.getString(1) + 
								"',(select max(dataid) from data),'" + imie + " " + nazwisko + "')");
						request("COMMIT");

						return true;
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				return false;
			}
		}
		
		//funkcja laczaca sie z serwerem, wysylajaca zapytanie i odbierajaca wynik
		private static CachedRowSetImpl request(String kw)
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
		
		//taka funkcja pmocnicza do testow wyswietlajaca co tam w result secie mamy aktualnie 
		private static void checkMessage(CachedRowSetImpl message)
		{
			try
            {
				System.out.println("Wiadomosc:");
	            ResultSetMetaData rsmd = message.getMetaData();
	            while (message.next())
	            {
	            	for(int i = 1; i <= rsmd.getColumnCount(); i++)
	            		System.out.print(message.getString(i) + "\t");
	            	
	            	System.out.println("");
	            }
	            
	            message.first();
            }
			catch (SQLException e) 
            {
                e.printStackTrace();
            }
		}
	}
}
