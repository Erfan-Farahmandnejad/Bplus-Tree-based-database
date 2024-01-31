package com.example.bplustree;

import com.example.bplustree.Implementation.BplusTree.BPlusTree;
import com.example.bplustree.Implementation.Map.UnsortedTableMap;
import javafx.scene.control.TableColumn;

import java.util.ArrayList;
import java.util.List;


public class Table {


    //todo: fill these maps in b+ tree when making table
    private UnsortedTableMap<Integer,String> rows =new UnsortedTableMap<>();//map row names to a specific row
    private UnsortedTableMap<String,Column> columns =new UnsortedTableMap<>();//map column names to a specific column
    private BPlusTree tableTree=new BPlusTree();//this table tree
    private   UnsortedTableMap<String, List<Integer>>IndexesOfKey=new UnsortedTableMap<String, List<Integer>>();
    private String tableName;
    private String tableKey;

    //private List<String>RowsNames;
    private List<Column>columnsList=new ArrayList<>();

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public  UnsortedTableMap<String, List<Integer>> getIndexesOfKey() {
        return this.IndexesOfKey;
    }

    public  void setIndexesOfKey(UnsortedTableMap<String, List<Integer>> indexesOfKey) {
        this.IndexesOfKey = indexesOfKey;
    }

    public BPlusTree getTableTree() {
        return tableTree;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTableTree(BPlusTree tableTree) {
        this.tableTree = tableTree;
    }

    public UnsortedTableMap<String, Column> getColumns() {
        return this.columns;
    }

    public void setColumns(UnsortedTableMap<String, Column> columns) {
        this.columns = columns;
    }

    public UnsortedTableMap<Integer,String> getRows() {
        return this.rows;
    }

    public void setRows(UnsortedTableMap<Integer,String> rows) {
        this.rows = rows;
    }

    public List<Column> getColumnsList() {
        return this.columnsList;
    }

    public void setColumnsList(List<Column> columnsList) {
        this.columnsList = columnsList;
    }

    //-------------------------------------------------------------------
   public static class Column
    {
        private String ColKey;
        private List<Record>ColRecords=new ArrayList<Record>();

        public Column(String colKey) {
            ColKey = colKey;
        }

        public String getColKey() {
            return ColKey;
        }

        public void setColKey(String colKey) {
            ColKey = colKey;
        }

        public List<Record> getColRecords() {
            return ColRecords;
        }

        public void setColRecords(List<Record> colRecords) {
            ColRecords = colRecords;
        }
    }

    //-------------------------------------------------------------------------
   public static class Record extends TableColumn<Record, Object> {

        String value;
        int RowKey;
        String ColumnKey;

        public Record(String value, int rowKey, String columnKey) {
            this.value = value;
            RowKey = rowKey;
            ColumnKey = columnKey;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getRowKey() {
            return RowKey;
        }

        public void setRowKey(int rowKey) {
            RowKey = rowKey;
        }

        public String getColumnKey() {
            return ColumnKey;
        }

        public void setColumnKey(String columnKey) {
            ColumnKey = columnKey;
        }

    }
}
