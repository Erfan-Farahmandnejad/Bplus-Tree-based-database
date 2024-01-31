package com.example.bplustree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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

            if (temp[0].equals("Insert")){
                insert(textField_Query.getText().substring(7),currentTable);
            }
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
//                        alert.setHeight(300);
//                        alert.setWidth(300);
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
}

