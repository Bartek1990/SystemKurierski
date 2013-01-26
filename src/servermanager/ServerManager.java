package servermanager;


import javax.swing.*;
import java.awt.*;

public class ServerManager extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;
    private JPanel panel9;
    private JPanel panel0;
    private JPanel panel11;
    private JPanel panel12;



    public ServerManager(){

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(ClassNotFoundException e){

        }catch(InstantiationException e){

        }catch (IllegalAccessException e){

        }catch (UnsupportedLookAndFeelException e){
        }

        setTitle("Menadżer Serwera");
        setSize(900,600);
        setBackground(Color.gray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout());
        getContentPane().add(topPanel);


        createPage1();
        createPage2();
        //createPage3();
        //createPage4();
        //createPage5();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Klienci", panel1);
        tabbedPane.addTab("Kurierzy", panel2 );
        tabbedPane.addTab("Operatorzy", panel3);
        topPanel.add(tabbedPane);

    }
    public void createPage1()
    {
        TableDisplay table1 = new TableDisplay("user");
        panel1 = new JPanel();
        panel1.add(table1.getTableP().getTableHeader());
        JTable mTable = new JTable(table1.getRows(), table1.getColumnHeads());

        mTable.setSize(900, 600);
        panel1.add(mTable);
        panel1.setLayout(null);





    }
    public void createPage2()
    {
        TableDisplay table2 = new TableDisplay("employee");
        panel2 = new JPanel();
        panel2.add(table2.getTableP().getTableHeader());
        JTable mTable = new JTable(table2.getRows(), table2.getColumnHeads());
        mTable.setSize(900,600);
        panel2.add(mTable);
        panel2.setLayout( new BorderLayout() );

    }
    /*
    public void createPage3()
    {
        TableDisplay table3 = new TableDisplay("operator");
        panel3 = new JPanel();
        panel3.add(table3.getTableP().getTableHeader());
        JTable mTable = new JTable(table3.getRows(), table3.getColumnHeads());
        mTable.setSize(600,600);
        panel3.add(mTable);
        panel3.setLayout( new BorderLayout() );

    }
    public void createPage4()
    {
        TableDisplay table4 = new TableDisplay("employee");
        panel4 = new JPanel();
        panel4.add(table4.getTableP().getTableHeader());
        JTable mTable = new JTable(table4.getRows(), table4.getColumnHeads());
        mTable.setSize(600,600);
        panel4.add(mTable);
        panel4.setLayout( new BorderLayout() );

    }
    public void createPage5()
    {
        TableDisplay table5 = new TableDisplay("shipment");
        panel5 = new JPanel();
        panel5.add(table5.getTableP().getTableHeader());
        JTable mTable = new JTable(table5.getRows(), table5.getColumnHeads());
        mTable.setSize(600,600);
        panel5.add(mTable);
        panel5.setLayout( new BorderLayout() );

    } */
    public static void main(String[] args){
        ServerManager serverManager = new ServerManager();
        serverManager.setVisible(true);
    }
}

