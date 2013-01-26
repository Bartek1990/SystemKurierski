package people;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import exceptions.AlreadyInDbException;
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
    private JTextField forename = null;
    private JTextField surename = null;
    private JTextField empDate = null;
    private JTextField earnings = null;
    private JTextField workTime = null;
    private JTextField nip = null;
    private JTextField account = null;
    private JButton zarejestruj;
    private JTextField DataName = null;
    private JTextField dataDetails = null;
    private JTextField zipCode = null;
    private JTextField city = null;
    private JTextField tel = null;
    private JTextField mail = null;
    private JTextField baseid = null;
    private JTextField dataCountryId = null;


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
            CachedRowSetImpl zapytanie = klient.request("select * from user");
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
            forename = new JTextField("Imię");
            surename = new JTextField("Nazwisko");
            empDate = new JTextField("Data Zatrudnienia");
            earnings = new JTextField("Zarobki");
            workTime = new JTextField("Czas Pracy");
            nip = new JTextField("NIP");
            account = new JTextField("nr Konta");
            DataName = new JTextField("DataName");
            dataDetails = new JTextField("dataDetails");
            zipCode = new JTextField("Kod Pocztowy");
            city = new JTextField("Miasto");
            tel = new JTextField("Telefon");
            mail = new JTextField("E-mail");
            baseid = new JTextField("baseid");
            dataCountryId =  new JTextField("dataCountryId");

            login.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){login.setText("");}  });
            password.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){password.setText("");}  });
            forename.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){forename.setText("");}  });
            surename.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){surename.setText("");}  });
            empDate.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){empDate.setText("");}  });
            earnings.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){earnings.setText("");}  });
            baseid.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){baseid.setText("");}  });
            workTime.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){workTime.setText("");}  });
            nip.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){nip.setText("");}  });
            account.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){account.setText("");}  });
            DataName.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){DataName.setText("");}  });
            dataCountryId.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){dataCountryId.setText("");}  });
            dataDetails.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){dataDetails.setText("");}  });
            zipCode.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){zipCode.setText("");}  });
            city.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){city.setText("");}  });
            tel.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){tel.setText("");}  });
            mail.addMouseListener(new MouseAdapter(){@Override public void mouseClicked(MouseEvent e){mail.setText("");}  });

            add(login);
            add(password);
            add(forename);
            add(surename);
            add(empDate);
            add(earnings);
            add(baseid);
            add(workTime);
            add(nip);
            add(account);
            add(DataName);
            add(dataCountryId);
            add(dataDetails);
            add(zipCode);
            add(city);
            add(tel);
            add(mail);
            add(zarejestruj);
        }
        else if(e.getSource() == zarejestruj)
        {
            int baseidInt = Integer.parseInt(mail.getText());
            int dataCountryIdInt = Integer.parseInt(dataCountryId.getText());
            int zipCodeInt = Integer.parseInt(zipCode.getText());
            /*
            try {
                new Employee(login.getText(), password.getText(), forename.getText(), surename.getText(), empDate.getText(), earnings.getText(), baseidInt, workTime.getText(), nip.getText(), account.getText(), DataName.getText(), dataCountryIdInt, dataDetails.getText(), zipCodeInt, city.getText(), tel.getText(), mail.getText());
            } catch (AlreadyInDbException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            */
        }

    }
}