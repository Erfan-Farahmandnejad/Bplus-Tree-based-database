package com.example.bplustree;
import com.example.bplustree.Implementation.Map.UnsortedTableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Query implements Initializable {

    @FXML
    private AnchorPane anch_Query;

    @FXML
    private Button btn_Back;

    @FXML
    private Button btn_Select;

    @FXML
    private TextField textField_Query;

    @FXML
    private TreeView<String> trView_Tables;

    private TreeItem<String>root=new TreeItem<String>();

    private Table currentTable;

    @FXML
    void SelectTableOrQuery(ActionEvent event) {

        //باید جدول مورد نظر را انتخاب کند
        if (textField_Query.getPromptText().equals("Type a Table Name"))
        {
            for (Table table:MakeTable.allTables)
            {
                if (table.getTableName().equals(textField_Query.getText()))
                {
                    root.getChildren().clear();
                    root.setValue(table.getTableName());
                    for (int i=0 ; i<table.getRows().size(); i++)
                    {
                        root.getChildren().add(new TreeItem<>(table.getRows().get(i)));
                    }
                    trView_Tables.refresh();
                    textField_Query.setPromptText("Query+(-)+etc");
                    currentTable=table;
                }

            }
            if (textField_Query.getPromptText().equals("Type a Table Name"))
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Table Not Found!!");
                alert.setContentText("The table name you have entered is not exists!!");
                alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                alert.setHeight(300);
                alert.setWidth(300);
                alert.initStyle(StageStyle.TRANSPARENT);
                alert.show();
            }
        }
        //todo: Queriessssssssss
        else
        {
            String []temp=textField_Query.getText().split("-");

            //insert----------------------------------------------------------------
            if (temp[0].equals("Insert")){
                insert(textField_Query.getText().substring(7),currentTable);
            }
            //delete-----------------------------------------------------------------
            else if (temp[0].equals("Delete"))
                {
                    String result=delete(textField_Query.getText().substring(7),currentTable);

                    if (!result.equals("Not found!!"))
                    {
                        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Delete Operation");
                        alert.setContentText("This/These is/are removed Information:\n"+result);
                        System.out.println("This/These is/are removed Information:\n"+result);
                        alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                        alert.setHeight(300);
                        alert.setWidth(300);
                        alert.initStyle(StageStyle.TRANSPARENT);
                        alert.show();
                    }
                    else
                    {
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not found!!");
                        alert.setContentText("There is no "+currentTable.getTableKey()+" found that matches "+textField_Query.getText().substring(7)+" in this table!!!");
                        alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                        alert.setHeight(300);
                        alert.setWidth(300);
                        alert.initStyle(StageStyle.TRANSPARENT);
                        alert.show();
                    }
                }
            //Search-------------------------------------------------------------------
                else if (temp[0].equals("Search"))
                {
                    String result=search(textField_Query.getText().substring(7),currentTable);
                    if (!result.equals("Not found!!"))
                    {
                        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Search Operation");
                        alert.setContentText("This/These is/are Information that you have searched for:\n"+result);
                        System.out.println("This/These is/are Information that you have searched for:\n"+result);
                        alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                        alert.setHeight(300);
                        alert.setWidth(300);
                        alert.initStyle(StageStyle.TRANSPARENT);
                        alert.show();
                    }
                    else
                    {
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not found!!");
                        alert.setContentText("There is no "+currentTable.getTableKey()+" found that matches "+textField_Query.getText().substring(7)+" in this table!!!");
                        alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                        alert.setHeight(300);
                        alert.setWidth(300);
                        alert.initStyle(StageStyle.TRANSPARENT);
                        alert.show();
                    }
                }
                //Count-----------------------------------------------------------------------------
                else if(temp[0].equals("Count"))
            {
                int result=count(textField_Query.getText().substring(6),currentTable);
                if (result==0){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Not found!!");
                    alert.setContentText("There is no "+currentTable.getTableKey()+" found that matches "+textField_Query.getText().substring(6)+" in this table!!!");
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                    alert.setHeight(300);
                    alert.setWidth(300);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.show();
                }
                else
                {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Count Operation");
                    alert.setContentText("There is/are "+result+" Number of "+textField_Query.getText().substring(6)+" found in the "+currentTable.getTableName());
                    System.out.println("There is/are "+result+" Number of "+textField_Query.getText().substring(6)+" found in the "+currentTable.getTableName());
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                    alert.setHeight(300);
                    alert.setWidth(300);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.show();
                }
            }

                //Average-----------------------------------------------------------------------------------
            else if(temp[0].equals("Average"))
            {
                double result=average(textField_Query.getText().substring(8),currentTable);
                if (result==-1000000000){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Column!!");
                    alert.setContentText("The Column Name you have entered is not a valid column");
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                    alert.setHeight(300);
                    alert.setWidth(300);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.show();
                }
                else
                {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Average Operation");
                    alert.setContentText("The average of the "+textField_Query.getText().substring(8)+" Column is: "+result);
                    System.out.println("The average of the "+textField_Query.getText().substring(8)+" Column is: "+result);
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                    alert.setHeight(300);
                    alert.setWidth(300);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.show();
                }
            }

            //Group By--------------------------------------------------------------------

            else if (temp[0].equals("GroupBy"))
            {
                String result=groupBy(textField_Query.getText().substring(8),currentTable);
                if (!result.equals("Wrong Column!!"))
                {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("GroupBy Operation");
                    alert.setContentText("This/These is/are The Groups based on Same "+textField_Query.getText().substring(8)+"\n"+result);
                    System.out.println("This/These is/are The Groups based on Same "+textField_Query.getText().substring(8)+"\n"+result);
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                    alert.setHeight(300);
                    alert.setWidth(300);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.show();
                }
                else
                {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong Column!!");
                    alert.setContentText("There is no Column with Name"+textField_Query.getText().substring(8)+" in the Table "+currentTable.getTableName());
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                    alert.setHeight(300);
                    alert.setWidth(300);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.show();
                }
            }
            //sum-----------------------------------------------------------------------------------------
            else if(temp[0].equals("Sum"))
            {
                double result=sum(textField_Query.getText().substring(4),currentTable);
                if (result==-1000000000){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Column!!");
                    alert.setContentText("The Column Name you have entered is not a valid column");
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                    alert.setHeight(300);
                    alert.setWidth(300);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.show();
                }
                else
                {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Average Operation");
                    alert.setContentText("The Summation of the "+textField_Query.getText().substring(8)+" Column is: "+result);
                    System.out.println("The Summation of the "+textField_Query.getText().substring(8)+" Column is: "+result);
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
                    alert.setHeight(300);
                    alert.setWidth(300);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.show();
                }
            }
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        HelloApplication.mainStage.setTitle("Main Page");
        scene.setFill(Color.TRANSPARENT);
        HelloApplication.mainStage.setScene(scene);
        HelloApplication.mainStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_Back.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Select.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        textField_Query.getStylesheets().add(getClass().getResource("TextFieldStyle.css").toExternalForm());
        textField_Query.setPromptText("Type a Table Name");
        trView_Tables.setRoot(root);
        root.setValue("Tables:");
        for (Table table:MakeTable.allTables)
        {
            TreeItem<String>temp=new TreeItem<>(table.getTableName());
            root.getChildren().add(temp);
        }
        trView_Tables.refresh();
    }

    //insert new record into table and B+ Tree--------------------------------------------------------------------
    public void insert(String input,Table table)
    {
        String[] parts=input.split(" ");
        StringBuilder temp =new StringBuilder();
        for (int i=0; i<parts.length; i++)
        {
            temp.append(parts[i]);
            if (i!=parts.length-1)
            {
                temp.append('\n');
            }
        }
        table.getRows().put(table.getRows().size(),temp.toString());
        //refreshing the tree view
        root.getChildren().clear();
        root.setValue(table.getTableName());
        for (int i=0 ; i<table.getRows().size(); i++)
        {
            root.getChildren().add(new TreeItem<>(table.getRows().get(i)));
        }
        trView_Tables.refresh();

        //ذخیره نود جدید در انواع ستون ها
        List<String>entered = new ArrayList<String>();
        entered.add("");
        for (Table.Column column: table.getColumnsList())
        {
            for (int i = 0; i <parts.length ; i++) {
                if (!entered.contains(parts[i]))
                {
                    String temp1[]=parts[i].split(":");
                    if (temp1[0].equals(column.getColKey()))
                    {
                        column.getColRecords().add(new Table.Record(temp1[1],table.getRows().size()-1,temp1[0]));
                        entered.add(parts[i]);
                        if (temp1[0].equals(table.getTableKey()))
                        {
                            if (table.getIndexesOfKey().get(temp1[1])!=null)
                            {
                                table.getIndexesOfKey().get(temp1[1]).add(table.getRows().size()-1);
                                table.getTableTree().insert(table.getRows().size()-1,temp.toString());
                            }
                            else
                            {
                                ArrayList<Integer>t=new ArrayList<Integer>();
                                t.add(table.getRows().size()-1);
                                table.getIndexesOfKey().put(temp1[1],t);
                            }
                        }
                    }
                }
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------
    public String delete(String key,Table table)
    {
        //پیدا شده که بخوایم حذف کنیم
        if (table.getIndexesOfKey().get(key) != null)
        {
            StringBuilder removed=new StringBuilder();
            //اول حذف از ستون ها
            for (Integer index:table.getIndexesOfKey().get(key))
            {
                for (Table.Column column: table.getColumnsList())
                {
                    column.getColRecords().remove(index);
                }
                //حذف از ردیف ها
                table.getRows().remove(index);
                removed.append(table.getTableTree().search(index)).append("\n").append("---------------------------------------------");
                table.getTableTree().delete(index);
            }
            table.getIndexesOfKey().remove(key);
            //refreshing the tree view
            root.getChildren().clear();
            root.setValue(table.getTableName());
            for (int i=0 ; i<table.getRows().size(); i++)
            {
                root.getChildren().add(new TreeItem<>(table.getRows().get(i)));
            }
            trView_Tables.refresh();
            return removed.toString();
        }
        //اصلا نیست
        return "Not found!!";
    }
    //search function---------------------------------------------------------------------------------------
    public String search(String key,Table table)
    {
        //پیدا شده
        if (table.getIndexesOfKey().get(key)!=null)
        {
            StringBuilder founded=new StringBuilder();
            for (Integer index:table.getIndexesOfKey().get(key))
            {
                founded.append(table.getTableTree().search(index)).append("\n").append("----------------------------\n");
            }
            return founded.toString();
        }
        //پیدا نشده
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not found!!");
            alert.setContentText("There is no "+table.getTableKey()+" found that matches "+key+" in this table!!!");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
            alert.setHeight(300);
            alert.setWidth(300);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.show();
            return "Not found!!";
    }

    //-------count function----------------------------------------------------------------
    public int count(String key,Table table)
    {

        if (table.getIndexesOfKey().get(key)!=null)
        {
            return table.getIndexesOfKey().get(key).size();
        }
        else
            return 0;
    }
    //average ------------------------------------------------------------------------------
    public double average(String ColumnName,Table table)
    {
        double sum=0;
        int count=0;
        for (Table.Column column: table.getColumnsList())
        {
            if (column.getColKey().equals(ColumnName))
            {
                for (Table.Record record: column.getColRecords())
                {
                    sum+=Double.parseDouble(record.getValue());
                    count++;
                }
                return sum/count;
            }
        }
        return -1000000000;
    }
    //group by----------------------------------------------------------------------------------
    public String groupBy(String key,Table table)
    {
        UnsortedTableMap<String,List<Integer>>indexOfThisKey=new UnsortedTableMap<String,List<Integer>>();

        for (Table.Column column: table.getColumnsList())
        {
            if (column.getColKey().equals(key))
            {
                for (Table.Record record :column.getColRecords())
                {
                    if (indexOfThisKey.get(record.getValue())!=null)
                    {
                        List<Integer>temp=indexOfThisKey.get(record.getValue());
                        temp.add(record.getRowKey());
                        indexOfThisKey.put(record.getValue(), temp);
                    }
                    else
                    {
                        List<Integer>temp=new ArrayList<Integer>();
                        temp.add(record.getRowKey());
                        indexOfThisKey.put(record.getValue(), temp);
                    }
                }
            }
        }
        StringBuilder result=new StringBuilder();
        int counter=0;
        for (String current:indexOfThisKey.KeySet())
        {
            if (indexOfThisKey.get(current)!=null)
            {
                counter++;
                result.append("Group "+counter+":").append("\n");
                for (Integer index:indexOfThisKey.get(current))
                {
                    String temp=table.getTableTree().search(index);
                    result.append(temp).append("\n");
                }
                result.append("--------------------------------\n");
            }
        }

        if (indexOfThisKey.isEmpty())
        {
            return "Wrong Column!!";
        }
            return result.toString();
    }
    //Sum---------------------------------------------------------------------------------------
    public double sum(String ColumnName,Table table)
    {
        double sum=0;
        for (Table.Column column: table.getColumnsList())
        {
            if (column.getColKey().equals(ColumnName))
            {
                for (Table.Record record: column.getColRecords())
                {
                    sum+=Double.parseDouble(record.getValue());
                }
                return sum;
            }
        }
        return -1000000000;
    }
}

