package servermanager;

import java.util.*;
import java.sql.*;


public class ListTableModel extends RowTableModel<List>
{

    public ListTableModel(List<String> columnNames)
    {
        super(columnNames);
        setRowClass( List.class );
    }


    public Object getValueAt(int row, int column)
    {
        List rowData = getRow( row );
        return rowData.get( column );
    }


    public void setValueAt(Object value, int row, int column)
    {
        List rowData = getRow( row );
        rowData.set(column, value);
        fireTableCellUpdated(row, column);
    }


    @Override
    public void insertRow(int row, List rowData)
    {
        justifyRow( rowData );
        super.insertRow(row, rowData);
    }


    @Override
    public void insertRows(int row, List<List> rowList)
    {
        for (List rowData: rowList)
        {
            justifyRow( rowData );
        }

        super.insertRows(row, rowList);
    }


    private void justifyRow(List rowData)
    {
        for (int i = rowData.size(); i < getColumnCount(); i++)
        {
            rowData.add( null );
        }
    }

    public static ListTableModel createModelFromResultSet(ResultSet resultSet)
            throws SQLException
    {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columns = metaData.getColumnCount();

        //  Create empty model using the column names

        ArrayList<String> columnNames = new ArrayList<String>();

        for (int i = 1; i <= columns; i++)
        {
            String columnName = metaData.getColumnName(i);
            String columnLabel = metaData.getColumnLabel(i);

            if (columnLabel.equals(columnName))
            {

                columnNames.add( formatColumnName(columnName) );

            }
            else
                columnNames.add( columnLabel );
        }

        ListTableModel model = new ListTableModel( columnNames );
        model.setModelEditable( true );

        //  Assign the class of each column

        for (int i = 1; i <= columns; i++)
        {
            try
            {
                String className = metaData.getColumnClassName( i );
                model.setColumnClass(i - 1, Class.forName(className));
            }
            catch ( Exception exception ) {}
        }

        //  Get row data

        ArrayList<List> data = new ArrayList<List>();

        while (resultSet.next())
        {
            ArrayList<Object>row = new ArrayList<Object>(columns);

            for (int i = 1; i <= columns; i++)
            {
                Object o = resultSet.getObject(i);
                row.add( o );
            }

            data.add( row );
        }

        model.insertRows(0, data);

        return model;
    }
}
