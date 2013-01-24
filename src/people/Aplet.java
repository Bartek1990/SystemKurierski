package people;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

import netscape.javascript.JSObject;
import java.lang.*;
import java.sql.SQLException;

import com.sun.rowset.*;

// By uruchomić applet przeczytaj plik readme_applet.txt
public class Aplet extends JApplet implements ActionListener
{
    private JButton callJSButton = null;
    private JButton zapytanko = null;
    private JSObject window = null;
    private JLabel napis = null;
    private JButton rejestracjaPracownika = null;
    //formularz
    private JTextField login = null;
    private JTextField password = null;
    private JTextField forname = null;
    private JTextField surname = null;
    private JTextField empDate = null;
    private JTextField earnings = null;
    private JTextField worktime = null;
    private JTextField nip = null;
    private JTextField account = null;
    private JTextField name = null; //do korespondencji
    private JButton zarejestruj;


    Aplet aplet = this;
    Client klient;
    @Override
    public void init()
    {

        //window = JSObject.getWindow(this);   //potrzebne do uruchomienia na stronie z JS
        setLayout(new FlowLayout());
        rejestracjaPracownika = new JButton("Zarejestruj pracownika");
        callJSButton = new JButton("Gdzie jest kurier?");
        zapytanko = new JButton("wyslij zapytanie?");
        napis = new JLabel("");
        callJSButton.addActionListener(this);
        zapytanko.addActionListener(this);
        rejestracjaPracownika.addActionListener(this);
        zarejestruj = new JButton("zarejestruj");
        zarejestruj.addActionListener(this);
        add(rejestracjaPracownika);
        //add(callJSButton);
        //add(zapytanko);
        add(napis);



    }
    public void destroy()    //dziwnie to działa ale w sumie nie ma się co dziwić :D. Jak ktoś wpadnie na pomysł jak zrobić żeby proces java się zamykał po zamknięciu karty przeglądarki to niech to poprawi
    {
        super.destroy();
    }

    public void actionPerformed(ActionEvent e)
    {

        klient = new Client();
        if(e.getSource() == callJSButton)
        {
            String wspolrzedne = klient.communicationGpsInsert("coords");

            StringTokenizer st = new StringTokenizer(wspolrzedne, " ");
            String wsp[] = new String[2];
            int i=0;
            if (st.hasMoreTokens())
            {
                wsp[i] = st.nextToken();
                i++;
            }

            napis.setText(wsp[0] + " " + wsp[1]);
            window.setMember("wsp1", wsp[0]);
            window.setMember("wsp2", wsp[1]);
            window.eval("mapaStart()");
        }
        else if(e.getSource() == zapytanko)
        {
            CachedRowSetImpl zapytanie = klient.communicationMySQL("select * from user");
            try {
                napis.setText(zapytanie.getString(2));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getSource() == rejestracjaPracownika)
        {
            napis.setText(" ");
            remove(rejestracjaPracownika);
            setLayout(new FlowLayout());
            login = new JTextField("Login");
            password = new JTextField("Haslo");
            forname = new JTextField("Imię");
            surname = new JTextField("Nazwisko");
            empDate = new JTextField("Data Zatrudnienia");
            earnings = new JTextField("Zarobki");
            worktime = new JTextField("Czas Pracy");
            nip = new JTextField("NIP");
            account = new JTextField("nr Konta");
            name = new JTextField("Adres Korespondencyjny");

            add(zarejestruj);
            add(login);
            add(password);
            add(forname);
            add(surname);
            add(empDate);
            add(earnings);
            add(worktime);
            add(nip);
            add(account);
            add(name);

        }
        else if(e.getSource() == zarejestruj)
        {
           // new Pracownik(login, password, forname, surname, empDate, earnings, worktime, nip, account, name); //wg konstruktora
        }

    }
}