package servermanager;

import java.lang.reflect.*;
import java.util.*;
import javax.swing.table.*;


abstract class RowTableModel<T> extends AbstractTableModel
{
    protected List<T> modelData;
    protected List<String> columnNames;
    protected Class[] columnClasses;
    protected Boolean[] isColumnEditable;
    private Class rowClass = Object.class;
    private boolean isModelEditable = true;

    protected RowTableModel(List<String> columnNames)
    {
        this(new ArrayList<T>(), columnNames);
    }

    protected RowTableModel(List<T> modelData, List<String> columnNames)
    {
        setDataAndColumnNames(modelData, columnNames);
    }

    protected void setDataAndColumnNames(List<T> modelData, List<String> columnNames)
    {
        this.modelData = modelData;
        this.columnNames = columnNames;
        columnClasses = new Class[getColumnCount()];
        isColumnEditable = new Boolean[getColumnCount()];
        fireTableStructureChanged();
    }

    protected void setRowClass(Class rowClass)
    {
        this.rowClass = rowClass;
    }

    public Class getColumnClass(int column)
    {
        Class columnClass = null;



        if (column < columnClasses.length)
            columnClass = columnClasses[column];



        if (columnClass == null)
            columnClass = super.getColumnClass(column);

        return columnClass;
    }


    public int getColumnCount()
    {
        return columnNames.size();
    }


    public String getColumnName(int column)
    {
        Object columnName = null;

        if (column < columnNames.size())
        {
            columnName = columnNames.get( column );
        }

        return (columnName == null) ? super.getColumnName( column ) : columnName.toString();
    }


    public int getRowCount()
    {
        return modelData.size();
    }


    public boolean isCellEditable(int row, int column)
    {
        Boolean isEditable = null;

        //  Check is column editability has been set

        if (column < isColumnEditable.length)
            isEditable = isColumnEditable[column];

        return (isEditable == null) ? isModelEditable : isEditable.booleanValue();
    }


    public T getRow(int row)
    {
        return modelData.get( row );
    }

    public void insertRow(int row, T rowData)
    {
        modelData.add(row, rowData);
        fireTableRowsInserted(row, row);
    }


    public void insertRows(int row, List<T> rowList)
    {
        modelData.addAll(row, rowList);
        fireTableRowsInserted(row, row + rowList.size() - 1);
    }

    public void insertRows(int row, T[] rowArray)
    {
        List<T> rowList = new ArrayList<T>(rowArray.length);

        for (int i = 0; i < rowArray.length; i++)
        {
            rowList.add( rowArray[i] );
        }

        insertRows(row, rowList);
    }
    public void setColumnClass(int column, Class columnClass)
    {
        columnClasses[column] = columnClass;
        fireTableRowsUpdated(0, getRowCount() - 1);
    }

    public void setModelEditable(boolean isModelEditable)
    {
        this.isModelEditable = isModelEditable;
    }

    public static String formatColumnName(String columnName)
    {
        if (columnName.length() < 3) return columnName;

        StringBuffer buffer = new StringBuffer( columnName );
        boolean isPreviousLowerCase = false;

        for (int i = 1; i < buffer.length(); i++)
        {
            boolean isCurrentUpperCase = Character.isUpperCase( buffer.charAt(i) );

            if (isCurrentUpperCase && isPreviousLowerCase)
            {
                buffer.insert(i, " ");
                i++;
            }

            isPreviousLowerCase = ! isCurrentUpperCase;
        }

        return buffer.toString().replaceAll("_", " ");
    }
}
