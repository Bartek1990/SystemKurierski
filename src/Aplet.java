
import java.awt.FlowLayout;
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
    private JButton test = null;
    Aplet aplet = this;
    Client klient;
    @Override
    public void init()
    {

        window = JSObject.getWindow(this);
        setLayout(new FlowLayout());
        test = new JButton("button testowy");
        callJSButton = new JButton("Gdzie jest kurier?");
        zapytanko = new JButton("wyslij zapytanie?");
        napis = new JLabel("");
        callJSButton.addActionListener(Aplet.this);
        zapytanko.addActionListener(Aplet.this);

        add(test);
        add(callJSButton);
        add(zapytanko);
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
        else if(e.getSource() == test)
        {

        }
    }
}