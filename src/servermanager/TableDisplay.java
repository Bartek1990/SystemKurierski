package servermanager;

import people.Client;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableDisplay {
    private JTable table;
    private JPanel panel;
    ListTableModel model;
    private JFrame frame;
    public TableDisplay(String tableName){
        ResultSet resultSet = Client.request("SELECT * FROM " + tableName);
        try {
            model = ListTableModel.createModelFromResultSet(resultSet);
            table = new JTable(model);
            //createPanel();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public JTable getTable(){
        return table;
    }
    public JPanel createPanel(){

        panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane( table );
        panel.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }
    public JFrame createFrame(){
        frame = new JFrame();
        JScrollPane scrollPane = new JScrollPane( table );
        frame.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);
        return frame;
    }

    public static void main(String[] args){
        TableDisplay tD = new TableDisplay("user");
        JFrame frame = new JFrame("Menadżer Serwera");
        JScrollPane scrollPane = new JScrollPane( tD.getTable() );
        frame.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    public ListTableModel getModel(){
        return model;
    }
}

