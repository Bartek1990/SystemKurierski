package servermanager;



import people.JDBCConnection;

import java.sql.*;
import javax.swing.*;
import java.util.*;
public class TableDisplay {
    private JDBCConnection dataBaseConnector = new JDBCConnection();

    private JTable table;
    private String tableName = null;
    Vector columnHeads = new Vector();
    Vector rows = new Vector();

    public TableDisplay(String tableName)
    {
        this.tableName = tableName;
        getTable();
        //show();
    }
    public Vector getColumnHeads(){
        return columnHeads;
    }
    public Vector getRows(){
        return rows;
    }
    public JTable getTableP() {
        return table;
    }

    void getTable()
    {
        ResultSet resultSet;
        String query = "Select * FROM " + tableName;
        try {
            resultSet = dataBaseConnector.readDataBase(query);
            displayResultSet( resultSet );
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }




    }
    private void displayResultSet( ResultSet rs )
            throws SQLException
    {
// pozycja pierwszego rekordu
        boolean moreRecords = rs.next();
// jeśli nie ma wyświetl wiadomość
        if ( ! moreRecords ) {
        }

        try {
// uzyskaj nazwy kolumn
            ResultSetMetaData rsmd = rs.getMetaData();

            for ( int i = 1; i <= rsmd.getColumnCount(); ++i )
                columnHeads.addElement(rsmd.getColumnName(i));
            do {
                rows.addElement(getNextRow(rs, rsmd));
            } while ( rs.next() );

            // table = new JTable(Collections.copy(new Vector(), rows), Collections.copy(new Vector(), columnHeads));      // Tu nie działa
            table = new JTable(new Vector(rows), new Vector(columnHeads));   //A to działa a nie powinno!

        }
        catch ( SQLException sqlex ) {
            sqlex.printStackTrace();
        }
    }
    private Vector getNextRow( ResultSet rs,
                               ResultSetMetaData rsmd )
            throws SQLException
    {
        Vector currentRow = new Vector();

        for ( int i = 1; i <= rsmd.getColumnCount(); ++i )
            switch( rsmd.getColumnType( i ) ) {
                case Types.VARCHAR:
                    currentRow.addElement(rs.getString(i));
                    break;
                case Types.INTEGER:
                    currentRow.addElement(rs.getLong(i));
                    break;
                default:
                    System.out.println( "Type was: " +
                            rsmd.getColumnTypeName( i ) );
            }

        return currentRow;
    }




}
