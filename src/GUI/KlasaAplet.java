package GUI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*; 
import java.awt.Font;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.grzesiek.sql.*;
import com.sun.rowset.CachedRowSetImpl;

/*NIE WIEM CZY TEN DUZY KOMENT NA DOLE JEST AKTUALNY BO SPORO POZMIENIALEM
 * LEPIEJ ZWRACAC UWAGE NA MA�E KOMENTARZE UMIESZCZONE W KODZIE - Brajan
 * 
 * na samym poczatku znajduje sie zmienna log
 * mowi ona apletowi czy uzytkownik jest zalogowany czy nie
 * dzieki temu aplet wie jakie okienka wyswietlac
 * 
 * narazie dziala tylko wylogowywanie bo logowania nie dalo sie 
 * fizycznie zrobic bo nie ma jeszcze bazy danych z ktorej bedziemy sciagac
 * loginy i hasla
 * 
 * jesli chcesz testowac aplikacje na uzytkowniku zalogowanym zmien wartosc 
 * zmiennej na true
 * 
 * i w funkcji swingGUI() jako pierwszy wyswietlany panel ustaw na p6
 * (funkcja add(p0) na samym pocz�tku swingGUI)
 * 
 *  dzialanie programu:
 *  najpierw tworzymy wszystkie oniekna - kazde jest opisane
 *  potem tworzymy wszystkie skladniki okienek - kazde jest opisane
 *  konstruktor dodaje odpowiednie skladniki do odpowiednich okienek
 *  
 *  okienka posiadaja nazwy od p0 - p15
 *  
 *  kazdy skladnik okienka zaczyna sie od prefixu oznaczajacego okienko
 *  np p0b1 - button pierwszy okienka p0
 *  
 *  oznaczenia skladnikow:
 *  b - buttony np p0b1, p4b2
 *  tf - textfield np p8tf1, p8tf2
 *  l - label np p0l1, p5l3
 *  
 *  przypozadkowujemy textfieldy do labelow o tym samym numerze
 *  np p0l1 to label pierwszy w okienku p0 przypozadkowany 
 *  do p0tf1 - textfielda pierwszego w okienku p0
 *  
 *  w funkcji swingGUI() znajduja sie tylko actionlistnery
 *  kazdy przycisk ma swoj actionlistner powodujacy wykonanie jakiejs akcji,
 *  zamkniecie aktualnego okienka i otworzenie nastepnego
 *  narazie przyciski nie wykonuja zadnych akcji z wyjatkiem przechodzenia
 *  pomiedzy okienkami
 *  
 *  w tej klasie piszemy tylko grafike i obsluge przyciskow
 *  wsystkie inne funkcjie tworzymy w innych klasach a najlepiej tez w innych
 *  paczkach tak aby za kazda funkcje programu odpowiadala osobna paczka
 *  np komunikacja z serwerem znajduje sie w paczce 'serwer' i tam znajduja sie
 *  wszystkie klasy do tego potrzebne
 *  
 *  niektore przyciski sa nie obsluzone (nie robia nic) z powodu braku serwer
 *  
 *  jesli ktos cos dodaje niech opisuje to najlepiej jak potrafi aby uniknac
 *  bledow, najlepiej opisywac wszystko w podobny sposob do istniejacego
 *  
 */
public class KlasaAplet extends JApplet{
	private static final long serialVersionUID = 1L;

	private String login = null;
	boolean log = false;
	boolean kasowanie=false;
	Color kolortla = new Color(13,9,10);
   //@@@@@@@@@ Grzesiek ciebie iteresuje tylko ta klasa, w innych nie wprowadzaj �adnych zmian
   //@@@@@@@@@ jak si� to posypie to nie wiem czy si� po�ape z�orzyc to spowrotem
	 /*  oznaczenia skladnikow:
	  *  p- panele np p0, p8
	  *  b - buttony np p0b1, p4b2
	  *  tf - textfield np p8tf1, p8tf2
	  *  l - label np p0l1, p5l3
	  *  cb - checkbox np p15cb1
	*/
	MyPanel p0 = new MyPanel(false);	//okno glowne dla niezalogowanych
	MyPanel p1 = new MyPanel(false);	//okno logowania
	MyPanel p2 = new MyPanel(false);	//okno wpisywania id paczki w celu wyszukania niezalogowany
	MyPanel p3 = new MyPanel(false);	//okno wysylania paczki przez niezarejestrowanego
	MyPanel p4 = new MyPanel(false);	//okno wyswietlaj�ce stan paczki dla niezarejestrowanego
	MyPanel p5 = new MyPanel(false);	//okno wyslanej paczki niezalogowany
	MyPanel p6 = new MyPanel(true);	    //okno glowne dla zalogowanych
	MyPanel p7 = new MyPanel(false);	//okno rejestracji
	MyPanel p8 = new MyPanel(true);  	//okno wyslanej paczki zalogowany
	MyPanel p9 = new MyPanel(true);	    //okno wysylania paczki dla uzytkownikow zarejestrowanych
	MyPanel p10 = new MyPanel(true);	//okno historii
	MyPanel p11 = new MyPanel(true);	//okno kontaktow
	MyPanel p12 = new MyPanel(true);	//okno dodawania kontaktu
	MyPanel p13 = new MyPanel(true);	//okno wpisywania id paczki w celu wyszukania zalogowany
	MyPanel p14 = new MyPanel(true);	//okno wyswietlaj�ce stan paczki dla zarejestrowanego
	MyPanel p15 = new MyPanel(false);	//okno usuniecia konta 
	
	/************** UTWORZENIE SKLADNIKOW OKNA GLOWNEGO **************/
	
	MyButton p0b1 = new MyButton("Lokalizuj paczk�",50,180,200,40,p0);
	MyButton p0b2 = new MyButton("Zaloguj",50,240,200,40,p0);
	MyButton p0b3 = new MyButton("Zarejestruj si� w systemie",50,300,200,40,p0);
	MyButton p0b4 = new MyButton("Wy�lij paczk�",50,360,200,40,p0);
	MyLabel p0l = new MyLabel("<html>Chcesz wys�a� towar wymagaj�cy specjalnego traktowania lub dor�czy� przesy�k� na jutro, a przy tym mie� mo�liwo�� �ledzenia jej los�w? Fast Pack dostarczy przesy�k� lub dokument w ka�de miejsce na �wiecie.<br> Sprawd� nas.<br><br>Fast Pack koncentruje si� na wybranych sektorach przemys�u, by nasi klienci mogli korzysta� nie tylko z do�wiadczenia specjalist�w w zakresie logistyki, ale tak�e z ich wiedzy dotycz�cej rynk�w. Opracowane przez nas, znakomite rozwi�zania przeznaczone dla poszczeg�lnych sektor�w przemys�u daj� naszym klientom istotn� przewag� konkurencyjn�.</html>",320,200,400,250,p0);
	
	/************** UTWORZENIE SKLADNIKOW OKNA LOGOWANIA **************/
	
	MyTextField p1tf1 = new MyTextField(20,420,200,150,20,p1);
	MyTextField p1tf2 = new MyTextField(20,420,250,150,20,p1);
	MyLabel p1l1 = new MyLabel("Login: ",320,200,100,20,p1);
	MyLabel p1l2 = new MyLabel("Haslo: ",320,250,100,20,p1);
	MyLabel p1l3 = new MyLabel("Zalogowano!","GREEN");
	MyLabel p1l4 = new MyLabel("Bl�d logowania!","RED");
	MyLabel p1l5 = new MyLabel("Wprowadzono niepoprawny login lub has�o",420,330,340,20);
	MyButton p1b1 = new MyButton("Wr��",50,180,200,40,p1);
	MyButton p1b2 = new MyButton("OK",750,400,100,40,p1);
	
	/************** UTWORZENIE SKLADNIKOW OKNA LOKALIZOWANIA PACZKI DLA NIEZAREJESTROWANEGO **************/
	
	MyTextField p2tf= new MyTextField(20,420,200,100,20,p2);
	MyLabel p2l = new MyLabel("ID paczki",320,200,100,20,p2);
	MyLabel p2l2 = new MyLabel("Brak paczki o podanym numerze ID!","RED");
	MyButton p2b1 = new MyButton("Anuluj",50,180,200,40,p2);
	MyButton p2b2 = new MyButton("OK",750,400,100,40,p2);
	
	
	/************** UTWORZENIE SKLADNIKOW OKNA WYSYLANIA DLA NIEZAREJESTROWANEGO **************/
	
	JTextField p3tf1 = new MyTextField(20,470,200,150,20,p3);
	JTextField p3tf2 = new MyTextField(20,470,230,150,20,p3);
	JTextField p3tf3 = new MyTextField(30,470,260,200,20,p3);
	JTextField p3tf4 = new MyTextField(20,470,310,150,20,p3);
	JTextField p3tf5 = new MyTextField(20,470,340,150,20,p3);
	JTextField p3tf6 = new MyTextField(30,470,370,200,20,p3);
	MyLabel p3l1 = new MyLabel("Imie odbiorcy: ",320,200,150,20,p3);
	MyLabel p3l2 = new MyLabel("Nazwisko odbiorcy: ",320,230,150,20,p3);
	MyLabel p3l3 = new MyLabel("Adres odbiorcy: ",320,260,150,20,p3);
	
	MyLabel p3l4 = new MyLabel("Imie nadawcy: ",320,310,150,20,p3);
	MyLabel p3l5 = new MyLabel("Nazwisko nadawcy: ",320,340,150,20,p3);
	MyLabel p3l6 = new MyLabel("Adres nadawcy: ",320,370,150,20,p3);
	
	MyLabel p3l7 = new MyLabel("B��d wysy�ki","RED");
	MyLabel p3l8 = new MyLabel("Nie wype�niono wszystkich p�l.",325,440,340,20);
	MyButton p3b1 = new MyButton("Anuluj",50,180,200,40,p3);
	MyButton p3b2 = new MyButton("Wy�lij",750,390,100,40,p3);
	
	/************** UTWORZENIE SKLADNIKOW OKNA LOKALIZACJI PO ZNALEZIENIU PACZKI DLA NIEZALOGOWANEGO**************/
	MyButton p4b1 = new MyButton("Odswie�",750,400,100,40,p4);
	MyButton p4b2 = new MyButton("Wr��",50,180,200,40,p4);
	//MyLabel p4l = new MyLabel("tutaj bedzie wyswietlana lokazizacja paczki ale jeszcze nie wiem jak :P",320,200,400,20,p4);
	//MyLabel p4l = new MyLabel("Data wys�ania:",320,200,400,20,p4);
	//MyLabel p42 = new MyLabel("Data dostarczenia:",320,230,400,20,p4);
	
	/************** UTWORZENIE SKLADNIKOW OKNA WYSLANO DLA NIEZALOGOWANEGO**************/
	
	MyButton p5b1 = new MyButton("Wyslij kolejna",50,180,200,40,p5);
	MyButton p5b2 = new MyButton("Menu g��wne",50,240,200,40,p5);
	MyLabel p5l = new MyLabel("Zlecenie zostalo wys�ane","GREEN",p5);
	
	
	/************** UTWORZENIE SKLADNIKOW OKNA GLOWNEGO ZALOGOWANEGO **************/
	
	MyButton p6b1 = new MyButton("Lokalizuj paczk�",50,170,200,40,p6);
	MyButton p6b4 = new MyButton("Wy�lij paczk�",50,220,200,40,p6);
	MyButton p6b5 = new MyButton("Historia",50,270,200,40,p6);
	MyButton p6b6 = new MyButton("Kontakty",50,320,200,40,p6);
	MyButton p6b2 = new MyButton("Wyloguj",50,370,200,40,p6);
	MyButton p6b3 = new MyButton("Wyrejestruj si� z systemu",50,420,200,40,p6);
	MyLabel p6l = new MyLabel("<html>Chcesz wys�a� towar wymagaj�cy specjalnego traktowania lub dor�czy� przesy�k� na jutro, a przy tym mie� mo�liwo�� �ledzenia jej los�w? Fast Pack dostarczy przesy�k� lub dokument w ka�de miejsce na �wiecie.<br> Sprawd� nas.<br><br>Fast Pack koncentruje si� na wybranych sektorach przemys�u, by nasi klienci mogli korzysta� nie tylko z do�wiadczenia specjalist�w w zakresie logistyki, ale tak�e z ich wiedzy dotycz�cej rynk�w. Opracowane przez nas, znakomite rozwi�zania przeznaczone dla poszczeg�lnych sektor�w przemys�u daj� naszym klientom istotn� przewag� konkurencyjn�.</html>",320,200,400,250,p6);
	
	/************** UTWORZENIE SKLADNIKOW OKNA REJESTRACJI **************/
	
	MyButton p7b1 = new MyButton("Anuluj",50,180,200,40,p7);
	MyButton p7b2 = new MyButton("Zarejestruj",750,390,100,40,p7);
	JTextField p7tf1 = new MyTextField(20,470,200,150,20,p7);
	JTextField p7tf2 = new MyTextField(20,470,230,150,20,p7);
	JTextField p7tf3 = new MyTextField(20,470,260,150,20,p7);
	JTextField p7tf4 = new MyTextField(20,470,290,150,20,p7);
	JTextField p7tf5 = new MyTextField(20,470,320,150,20,p7);
	JTextField p7tf6 = new MyTextField(20,470,350,150,20,p7);
	JTextField p7tf10 = new MyTextField(20,790,200,150,20,p7);
	JTextField p7tf11 = new MyTextField(20,790,230,150,20,p7);
	JTextField p7tf12 = new MyTextField(20,790,260,150,20,p7);
	JTextField p7tf13 = new MyTextField(20,790,290,150,20,p7);
	JTextField p7tf14 = new MyTextField(20,790,320,150,20,p7);
	JTextField p7tf15 = new MyTextField(20,790,350,150,20,p7);
	JTextField p7tf16 = new MyTextField(20,470,380,150,20,p7);
	
	MyLabel p7l1 = new MyLabel("Login: ",320,200,150,20,p7);
	MyLabel p7l2 = new MyLabel("Haslo: ",320,230,150,20,p7);
	MyLabel p7l3 = new MyLabel("Telefon: ",320,260,150,20,p7);	
	MyLabel p7l4 = new MyLabel("Imie: ",320,290,310,20,p7);
	MyLabel p7l5 = new MyLabel("Nazwisko: ",320,320,150,20,p7);
	MyLabel p7l6 = new MyLabel("Email: ",320,350,150,20,p7);
	MyLabel p7l10 = new MyLabel("Adres: ",640,200,150,20,p7);
	MyLabel p7l11 = new MyLabel("Kod: ",640,230,150,20,p7);
	MyLabel p7l12 = new MyLabel("Miasto: ",640,260,150,20,p7);
	MyLabel p7l13 = new MyLabel("Kraj: ",640,290,150,20,p7);
	MyLabel p7l14 = new MyLabel("Korpoacja: ",640,320,150,20,p7);
	MyLabel p7l15 = new MyLabel("REGON: ",640,350,150,20,p7);
	MyLabel p7l16 = new MyLabel("NIP: ",320,380,150,20,p7);
	
	
	MyLabel p7l7 = new MyLabel("Rejestracja przebieg�a pomy�lnie!","GREEN");
	MyLabel p7l8 = new MyLabel("B��d podczas rejestracji!","RED");
	MyLabel p7l9 = new MyLabel("Login nie jest unikalny lub nie wype�niono wszystkich p�l.",325,450,340,20);
	
	/************** UTWORZENIE SKLADNIKOW OKNA WYSLANO DLA ZALOGOWANEGO **************/
	
	MyButton p8b1 = new MyButton("Wyslij kojena",50,180,200,40,p8);
	MyButton p8b2 = new MyButton("Menu g��wne",50,240,200,40,p8);
	MyLabel p8l = new MyLabel("Zlecenie zostalo wys�ane","GREEN",p8);
	
	
	
	/************** UTWORZENIE SKLADNIKOW OKNA WYSLIJ PACZKE DLA ZAREJESTROWANEGO USERA **************/
	
	MyLabel p9l2 = new MyLabel("imie odbiorcy: ",320,200,150,20,p9);
	MyLabel p9l3 = new MyLabel("nazwisko odbiorcy: ",320,240,150,20,p9);
	MyLabel p9l1 = new MyLabel("adres odbiorcy: ",320,280,150,20,p9);
	MyLabel p9l4 = new MyLabel("B��d wysy�ki","RED");
	MyLabel p9l5 = new MyLabel("Nie wype�niono wszystkich p�l.",325,440,340,20);
	MyTextField p9tf1 =  new MyTextField(20,470,200,150,20,p9);
	MyTextField p9tf2 =  new MyTextField(20,470,240,150,20,p9);
	MyTextField p9tf3 =  new MyTextField(30,470,280,200,20,p9);
	MyButton p9b1 = new MyButton("Anuluj",50,180,200,40,p9);
	MyButton p9b2 = new MyButton("Wy�lij",750,390,100,40,p9);
	
	
	/************** UTWORZENIE SKLADNIKOW OKNA HISTORII **************/
	
	
	MyListModel modellisty1 = new MyListModel();
	MyList lista1 = new MyList(modellisty1);
    MyScroll scroll1 = new MyScroll(lista1,p10);

	MyButton p10b = new MyButton("Wr��",50,180,200,40,p10);
	MyButton p10b2 = new MyButton("Usu�",50,230,200,40,p10);
	MyButton p10b3 = new MyButton("Usu� Wszystko",50,280,200,40,p10);
	
	/************** UTWORZENIE SKLADNIKOW OKNA KONTAKTOW **************/
	
	MyListModel modellisty2 = new MyListModel();
	MyList lista2 = new MyList(modellisty2);
    MyScroll scroll2 = new MyScroll(lista2,p11);
	
    MyLabel p11l1 = new MyLabel("Czy napewno chcesz usunac kontakt??",325,440,300,20);
    MyLabel p11l2 = new MyLabel("Kontakt usuniety!","GREEN");
    MyLabel p11l3 = new MyLabel("Pomy�lnie dodano nowy kontakt!","GREEN");
	MyButton p11b1 = new MyButton("Wr��",50,180,200,40,p11);
	MyButton p11b2 = new MyButton("Dodaj kontakt",50,240,200,40,p11);
	MyButton p11b3 = new MyButton("Usu� kontakt",50,300,200,40,p11);
	MyButton p11b4 = new MyButton("TAK",730,430,80,40,p11);
	MyButton p11b5 = new MyButton("NIE",830,430,80,40,p11);
	
	/************** UTWORZENIE SKLADNIKOW OKNA DODAWANIA KONTAKTU **************/
	MyLabel p12l01 = new MyLabel("B��d podczas dodawania kontaktu!","RED");
	MyLabel p12l02 = new MyLabel("Nie wype�niono wszystkich p�l.",325,450,340,20);

	MyButton p12b1 = new MyButton("Dodaj",750,390,100,40,p12);
	MyButton p12b2 = new MyButton("Wr��",50,180,200,40,p12);
	
	JTextField p12tf1 = new MyTextField(20,470,200,150,20,p12);
	JTextField p12tf2 = new MyTextField(20,470,230,150,20,p12);
	JTextField p12tf3 = new MyTextField(20,470,260,150,20,p12);
	JTextField p12tf4 = new MyTextField(20,470,290,150,20,p12);
	JTextField p12tf5 = new MyTextField(20,790,200,150,20,p12);
	JTextField p12tf6 = new MyTextField(20,790,230,150,20,p12);
	JTextField p12tf7 = new MyTextField(20,790,260,150,20,p12);
	JTextField p12tf8 = new MyTextField(20,790,290,150,20,p12);
	
	MyLabel p12l1 = new MyLabel("Imie: ",320,200,150,20,p12);	
	MyLabel p12l2 = new MyLabel("Nazwisko: ",320,230,150,20,p12);
	MyLabel p12l3 = new MyLabel("Kraj: ",320,260,150,20,p12);
	MyLabel p12l4 = new MyLabel("Adres: ",320,290,150,20,p12);
	MyLabel p12l5 = new MyLabel("Kod: ",640,200,150,20,p12);
	MyLabel p12l6 = new MyLabel("Miasto: ",640,230,150,20,p12);
	MyLabel p12l7 = new MyLabel("Tel: ",640,260,150,20,p12);
	MyLabel p12l8 = new MyLabel("Email: ",640,290,150,20,p12);
	
	
	/************** UTWORZENIE SKLADNIKOW OKNA LOKALIZOWANIA PACZKI DLA ZAREJESTROWANEGO **************/
	
	MyTextField p13tf= new MyTextField(20,420,200,100,20,p13);
	MyLabel p13l = new MyLabel("ID paczki",320,200,100,20,p13);
	MyLabel p13l2 = new MyLabel("Brak paczki o podanym numerze ID!","RED");
	MyButton p13b1 = new MyButton("Anuluj",50,180,200,40,p13);
	MyButton p13b2 = new MyButton("OK",750,400,100,40,p13);
	

	
	/************** UTWORZENIE SKLADNIKOW OKNA LOKALIZACJI PO ZNALEZIENIU PACZKI DLA ZAREJESTROWANEGO **************/
	
	MyButton p14b1 = new MyButton("Odswie�",750,400,100,40,p14);
	MyButton p14b2 = new MyButton("Wr��",50,180,200,40,p14);
	MyLabel p14l = new MyLabel("tutaj bedzie wyswietlana lokazizacja paczki ale jeszcze nie wiem jak :P",320,200,400,20,p14);
	
	/************** UTWORZENIE SKLADNIKOW OKNA WYREJESTROWANIA ZAREJESTROWANEGO **************/
	
	MyLabel p15l1 = new MyLabel("USUNI�CIE KONTA","GREEN",p15);
	MyLabel p15l2 = new MyLabel("KONTO ZOSTA�O USUNIETE","RED");
	MyLabel p15l3 = new MyLabel("<html>Pami�taj �e usuni�cie konta spowoduj� utrat� twoich danych takich jak kontakty oraz historia.</html>",300,230,400,50,p15);
	MyButton p15b1 = new MyButton("Akceptuje",345,340,200,40,p15);
	MyButton p15b2 = new MyButton("Wr��",50,180,200,40,p15);
	JCheckBox p15cb1 = new JCheckBox("Chc� usun�� swoje konto.");
	
		public KlasaAplet()
		{
			/********************** DODANIE SKLADNIKOW DO OKNA GLOWNEGO **********************/
			//// dodanie do list  na potrzeby testowania
		    modellisty1.Add("12-03-12 : Dong Xuan 31-516 Krak�w, Mogilska 18 ");
		    modellisty1.Add("15-06-12 : Chi�ski Pa�ac Krak�w, Miko�ajska");
		    modellisty1.Add("08-10-12 : Dong Hung 31-146 Krak�w, D�uga 50");
		    modellisty1.Add("3 opcja");
		    modellisty1.Add("3 opcja");
		    modellisty1.Add("3 opcja");
		    modellisty1.Add("3 opcja");
		    modellisty1.Add("3 opcja");
		    modellisty1.Add("3 opcja");
		    modellisty1.Add("3 opcja");
		    modellisty1.Add("3 opcja");
		    /*modellisty2.Add("Grzesiek Chyb");
		    modellisty2.Add("Chytra Baba Z Radomia");
		    modellisty2.Add("Gracjan Z Krakowa");
		    modellisty2.Add("Bracia Figo Fagot");
		    */
		    
		    // UNIKALNE CECHY NIEKTORYCH KOMPONENT�W NIE KASOWAC !!!!!!!!!!!
		    p15.add(p15cb1);
		    p11.remove(p11b4);
		    p11.remove(p11b5);
		    p15cb1.setBounds(350,320,300,20);
		    p15l1.setBounds(380,200,300,20);
		    p15l2.setBounds(330,400,300,20);
			p12l01.setBounds(325,430,300,20);
		    p7l8.setBounds(325,420,300,20);
			p7l7.setBounds(325,440,300,20);
			p3l7.setBounds(325,420,300,20);
			p9l4.setBounds(325,420,300,20); 
			p15cb1.setBackground(kolortla);
		}
		public void init(){
			try{
				SwingUtilities.invokeAndWait(new Runnable(){
					public void run(){
						swingGUI();
						}
					});
				}
			catch(Exception e){
			System.err.print(e);
			}
		}		
		
		private void swingGUI(){					
			
			setSize(995,502);
			add(p0);
			//testowo jako niezalogowany
			//add(p6);
			
			 //dodaje caly panel
				
			/********** OBSLUGA PRZYCISKOW OKNA P0 **********/
			
			p0b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
	
				    ////NIE RUSZAC///
					p2tf.setText("");
					remove(p0);
					add(p2);
					repaint();
					validate();
				}
			});
			
			p0b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					
				    ////NIE RUSZAC///
					p1tf1.setText("");
					p1tf2.setText("");
					p1.remove(p1l3);
				    p1.remove(p1l4);
				    p1.remove(p1l5);
					remove(p0);
					add(p1);
					repaint();
					validate();
				}
			});
			
			p0b3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					
				    ////NIE RUSZAC///
					p7tf1.setText("");
					p7tf2.setText("");
					p7tf3.setText("");
					p7tf4.setText("");
					p7tf5.setText("");
					p7tf6.setText("");
					remove(p0);
					add(p7);
					repaint();
					validate();
				}
			});
			
			p0b4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					
				    ////NIE RUSZAC///
					p3tf1.setText("");
					p3tf2.setText("");
					p3tf3.setText("");
					p3tf4.setText("");
					p3tf5.setText("");
					p3tf6.setText("");
					remove(p0);
					add(p3);
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P1 **********/
			
			p1b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				
				 ////NIE RUSZAC///
					remove(p1);
					if(log) add(p6);
					else add(p0);
					repaint();
					validate();
				}
			});
			
			p1b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					
					//@@@@@@@@@ METODA SPRAWDZAJ�CA LOGIN I HASLO JESLI POPRAWNE USTAWIA ZMIENA "log" NA TRUE
			        
				    //log=true;
					log = MySql.Do.p1b2(p1tf1.getText(), p1tf2.getText());
					
			        
			        if(log)
			        {
			        	login = p1tf1.getText();
			        	p1.add(p1l3);
			        }
					else
					{
						p1.add(p1l4);
						p1.add(p1l5);
					}
			        repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P2 **********/
			
			p2b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					
					///NIE RUSZAC///
					p2.remove(p2l2);
					remove(p2);
					add(p0);
					repaint();
					validate();
				}
			});
			
			p2b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					boolean znaleziono = false;
					
					//@@@@@@@@@ METODA SPRAWDZAJ�CA ID PACZKI, JESLI ISTNIEJE TO WYSWIETLA INFORMACJE O NIEJ NA PANELU "p4"
					//@@@@@@@@@ I USTAWIA ZMIEN� "znaleziono" NA TRUE
					
					CachedRowSetImpl crsi = MySql.Do.p2b2(p2tf.getText());
					
					try
					{
						if (crsi.first())
							znaleziono = true;
						else
							znaleziono = false;
					
					
						if(znaleziono)
						{
							remove(p2);
							add(p4);
							
							//dodalem
							MyLabel p4l = new MyLabel(("Data wys�ania : " + crsi.getString("sdate")),320,200,400,20,p4);
							MyLabel p42 = new MyLabel(("Data dostarczenia : " + crsi.getString("ddate")),320,230,400,20,p4);
						}
						else
						{
							p2.add(p2l2);
						}
					
					}
					catch (SQLException sqle) 
			        {
			            sqle.printStackTrace();
			        }
					
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P3 **********/
			
			p3b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				   ////NIE RUSZAC///
					p3.remove(p3l8);
					p3.remove(p3l7);
					remove(p3);
					add(p0);
					repaint();
					validate();
				}
			});
			
			p3b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					boolean wyslij = true;
								
					//@@@@@@@@@ METODA WYSY�AJ�CA NOW� PACZKE
					//@@@@@@@@@ JEZELI NIE WYPE�NIONO WSZYSTKICH P�L ZWRACA DLA ZMNIENIEJ
					//@@@@@@@@@ "wyslij" FALSE, JE�ELI WSZYSTKO OK TO TRUE
					
					if(wyslij)
					{
						remove(p3);
						add(p5);
					}
					else 
					{
						p3.add(p3l8);
						p3.add(p3l7);
					}
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P4 **********/
			
			p4b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					/******** NIE OBSLUZONE ********/
					
					//@@@@@@@@@ METODA ODSWIERZAJ�CA STAN PACZKI NA PANELU P4
					//grzesiek - trzeba by to jakos obsluzyc zeby nie nakladalo napisow na siebie
					//na razie nie wiem jak
					
					/*CachedRowSetImpl crsi = MySql.Do.p2b2(p2tf.getText());
					boolean znaleziono = false;
					
					try
					{
						if (crsi.first())
							znaleziono = true;
						else
							znaleziono = false;
					
					
						if(znaleziono)
						{
							//add(p4);
							
							MyLabel p4l = new MyLabel(("Data wys�ania : " + crsi.getString("sdate")),320,200,400,20,p4);
							MyLabel p42 = new MyLabel(("Data dostarczenia : " + crsi.getString("ddate")),320,230,400,20,p4);
						}
						else
						{
							p4.add(p2l2);
						}
					
					}
					catch (SQLException sqle) 
			        {
			            sqle.printStackTrace();
			        }*/
					
					repaint();
					validate();
				}
			});
			
			p4b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				 ////NIE RUSZAC///
					remove(p4);
					add(p0);
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P5 **********/
			
			p5b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
			    	////NIE RUSZAC///
					p3tf1.setText("");
					p3tf2.setText("");
					p3tf3.setText("");
					p3tf4.setText("");
					p3tf5.setText("");
					p3tf6.setText("");
					remove(p5);
					add(p3);
					repaint();
					validate();
				}
			});
			
			p5b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				 ////NIE RUSZAC///
					remove(p5);
					add(p0);
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P6 **********/
			
			p6b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				    ////NIE RUSZAC///
					p13tf.setText("");
					remove(p6);
					add(p13);
					repaint();
					validate();
					
				}
			});
			
			p6b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				 
				 ////NIE RUSZAC///
					log=false; // ZMIENA SYMULUJ�CA WYLOGOWANIE
					login = null;
					remove(p6);
					add(p0);
					repaint();
					validate();
				}
			});
			
			p6b3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				    ////NIE RUSZAC///
					p15.remove(p15l2);
					remove(p6);
					add(p15);
					repaint();
					validate();
				}
			});
			
			p6b4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				 ////NIE RUSZAC///
					remove(p6);
					add(p9);
					repaint();
					validate();
				}
			});
			
			p6b5.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	


					//@@@@@@@@@ METODA WY�WIETLAJ�CA CAL� HISTORIE NA PANELU P10
					
					// pojedynczy element histori dodasz przez funkcje modellisty1.Add(String);
					
					remove(p6);
					add(p10);
					repaint();
					validate();
				}
			});
			
			p6b6.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	


				    //@@@@@@@@@ METODA WY�WIETLAJ�CA CAL� HISTORIE NA PANELU P11
					
					// pojedynczy element histori dodasz przez funkcje modellisty2.Add(String);
					CachedRowSetImpl tmp = MySql.Do.p6b6(login);
					
					try
					{
						tmp.first();

						while(tmp.next())
						{
							modellisty2.Add(tmp.getString(1));
						}
					}
					catch(SQLException sqle)
					{
						sqle.printStackTrace();
					}
					
					remove(p6);
					add(p11);
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P7 **********/
			
			p7b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				   ////NIE RUSZAC///
					p7.remove(p7l7);
					p7.remove(p7l8);
					p7.remove(p7l9);
					remove(p7);
					add(p0);
					repaint();
					validate();
					
				}
			});
			
			p7b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					boolean zarejestrowano = false;
					
					//@@@@@@@@@ METODA REJESTRUJ�CA NOWEGO UZYTKOWNIKA
					//@@@@@@@@@ JEZELI LOGIN NIE JEST UNIKANY LUB NIE WYPE�NIONO WSZYSTKICH P�L ZWRACA DLA ZMNIENIEJ
					//@@@@@@@@@ "zarejestrowano" FALSE, JE�ELI WSZYSTKO OK TO TRUE
					
					//zarejestrowano = MySql.Do.p7b2(p7tf1.getText(), p7tf2.getText(), p7tf3.getText(), p7tf4.getText(), p7tf5.getText(), p7tf6.getText());
					zarejestrowano = MySql.Do.p7b2(p7tf1.getText(), p7tf2.getText(), p7tf3.getText(), p7tf4.getText(), 
							p7tf5.getText(), p7tf16.getText(), p7tf10.getText(), p7tf11.getText(), p7tf13.getText(), p7tf12.getText(), //String nip, String adres, String kod,String miasto, String kraj, String tel, 
							p7tf6.getText(), p7tf14.getText(), p7tf15.getText()//String corp, String reg
							);
					
					if(zarejestrowano) p7.add(p7l7);
					else 
					{
						p7.add(p7l8);
						p7.add(p7l9);
					}
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P8 **********/
			p8b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
			     	////NIE RUSZAC///
					p9tf1.setText("");
					p9tf2.setText("");
					p9tf3.setText("");
					p9.remove(p9l4);
					p9.remove(p9l5);
					remove(p8);
					add(p9);
					repaint();
					validate();
				}
			});
			
			
			p8b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				////NIE RUSZAC///
					remove(p8);
					add(p6);
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P9 **********/
			
			p9b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				   ////NIE RUSZAC///
					p9.remove(p9l4);
					p9.remove(p9l5);
					remove(p9);
					add(p6);
					repaint();
					validate();
				}
			});
			
			p9b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				    boolean wyslij = true;
					
					//@@@@@@@@@ METODA WYSY�AJ�CA NOW� PACZKE DLA ZALOGOWANEGO
					//@@@@@@@@@ JEZELI NIE WYPE�NIONO WSZYSTKICH P�L ZWRACA DLA ZMNIENIEJ
					//@@@@@@@@@ "WYSLIJ" FALSE, JE�ELI WSZYSTKO OK TO TRUE
					
					if(wyslij)
					{
						remove(p9);
						add(p8);
					}
					else 
					{
						p9.add(p9l4);
						p9.add(p9l5);
					}
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P10 **********/
			
			p10b.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
				 ////NIE RUSZAC///
					remove(p10);
					add(p6);
					repaint();
					validate();
				}
			});
			
			p10b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					modellisty1.remove(lista1);
					
					
					//@@@@@@@@@ METODA USUWAJ�CA ZAZNACZONY ELEMENT Z LISTY HISTORI ORAZ Z BAZY DANYCH

				}
			});
			
			p10b3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					modellisty1.removeall();
					
					//@@@@@@@@@ METODA USUWAJ�CA CA�� HISTORE Z LISTY ORAZ Z BAZY DANYCH
				}
			});
			
			
			/********** OBSLUGA PRZYCISKOW OKNA P11 **********/
			
			p11b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					///NIE RUSZAC///
					modellisty2.removeAllElements();
					remove(p11);
					add(p6);
					repaint();
					validate();
					p11.remove(p11l2);
					p11.remove(p11l3);
				}
			});
			
			p11b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					/// NIE RUSZAC///
					remove(p11);
					add(p12);
					repaint();
					validate();
					p11.remove(p11l2);
					p11.remove(p11l3);
				}
			});
			
			p11b3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					///NIE RUSZAC///
				    p11.add(p11b4);
				    p11.add(p11b5);
				    p11.add(p11l1);
				    p11.remove(p11l2);
				    p11.remove(p11l3);
					repaint();
					validate();
				}
			});
			
			p11b4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					//@@@@@@@@@ METODA USUWAJ�CA WYBRANY KONTAKT Z LISTY ORAZ Z BAZY DANYCH
					p11.remove(p11b4);
					p11.remove(p11b5);
					p11.remove(p11l1);
					p11.remove(p11l3);
					p11.add(p11l2);
					p11l2.setBounds(325,440,300,20);
					
					modellisty2.remove(lista2);
					repaint();
					validate();
				}
			});
			
			p11b5.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					///NIE RUSZAC///
					p11.remove(p11b4);
					p11.remove(p11b5);
					p11.remove(p11l1);
					repaint();
					validate();
				}
			});
			
			
			/********** OBSLUGA PRZYCISKOW OKNA P12 **********/
			
			p12b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					//@@@@@@@@@ METODA DODAJ�CA DO LISTY I BAZY DANYCH NOWY KONTAKT
					
					boolean dodano = false;
					
					dodano = MySql.Do.p12b1(login, p12tf7.getText(), p12tf1.getText(), p12tf2.getText(),
							p12tf4.getText(), p12tf5.getText(), p12tf3.getText(), p12tf6.getText(), p12tf8.getText());
					/*	(String login,String tel, String imie, 
						String nazwisko, String adres, String kod, 
						String kraj, String miasto, String mail)*/
					
					/*String pobranyKontakt;
					pobranyKontakt = p12tf1.getText()+" "+p12tf2.getText()+" "+p12tf3.getText();
					modellisty2.Add(pobranyKontakt);*/
					
					modellisty2.removeAllElements();
					p6b6.doClick();
					
					
					//////////////////////////////////////////////////
					if(dodano)
					{				
						remove(p12);
						add(p11);
						p11.add(p11l3);
					}
					else
					{
						p12.add(p12l01);
						p12.add(p12l02);
					}
					repaint();
					validate();
				}
			});
			
			p12b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					///NIE RUSZAC//
					remove(p12);
					add(p11);

					p12.remove(p12l01);
					p12.remove(p12l02);
					
					p12tf1.setText("");
					p12tf2.setText("");
					p12tf3.setText("");
					p12tf4.setText("");
					p12tf5.setText("");
					p12tf6.setText("");
					p12tf7.setText("");
					p12tf8.setText("");
					
					repaint();
					validate();
				}
			});
			
			/********** OBSLUGA PRZYCISKOW OKNA P13 **********/
			p13b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					///NIE RUSZAC///
					p13.remove(p13l2);
					remove(p13);
					add(p6);
					repaint();
					validate();
				}
			});
			
			p13b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					boolean znaleziono = false;
					
					//@@@@@@@@@ METODA SPRAWDZAJ�CA ID PACZKI, JESLI ISTNIEJE TO WYSWIETLA INFORMACJE O NIEJ NA PANELU "p13"
					//@@@@@@@@@ I USTAWIA ZMIEN� "znaleziono" NA TRUE
					
					if(znaleziono)
					{
						remove(p13);
						add(p14);	
					}
					else p13.add(p13l2);
					
					

					repaint();
					validate();
				}
			});
			/********** OBSLUGA PRZYCISKOW OKNA P14 **********/
			
			p14b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					/******** NIE OBSLUZONE ********/
					
					//@@@@@@@@@ METODA ODSWIERZAJ�CA STAN PACZKI NA PANELU P13
				}
			});
			
			p14b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					///NIE RUSZAC///
					remove(p14);
					add(p6);
					repaint();
					validate();
				}
			});
			/********** OBSLUGA PRZYCISKOW OKNA P15 **********/
			p15b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	


					if (p15cb1.isSelected()) kasowanie =true;
					if(kasowanie)
					{
						
						//@@@@@@@@@ METODA KASUJ�CA KONTO
						p15.add(p15l2);
					}
					
					repaint();
					validate();
				}
			});
			
			p15b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){	
					///NIE RUSZAC///
					remove(p15);
					if(kasowanie) 
					{
						add(p0);
						kasowanie = false;
					}
					else add(p6);
					repaint();
					validate();
				}
			});
			
			
			
			
		}
		
}