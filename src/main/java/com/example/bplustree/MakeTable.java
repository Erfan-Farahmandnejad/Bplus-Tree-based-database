package com.example.bplustree;
import com.example.bplustree.HelloApplication;
import com.example.bplustree.Implementation.BplusTree.BPlusTree;
import com.example.bplustree.Implementation.Map.UnsortedTableMap;
import com.example.bplustree.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MakeTable implements Initializable {

    @FXML
    private Button btn_AddColumn;

    @FXML
    private Button btn_Addcell;

    @FXML
    private Button btn_Back;

    @FXML
    private Button btn_IndexSelect;

    @FXML
    private TableView<Table.Record> tbl_DataTable;

    @FXML
    private TextField txtField_Addcell;

    @FXML
    private TextField txtField_ColumnName;

    @FXML
    private TextField txtField_Index;

    private Table currentTable=new Table("table");


    public static List<Table>allTables=new ArrayList<Table>();
    int rowCount=-1;

    @FXML
    void addColumn(ActionEvent event) {
        TableColumn<Table.Record,String> column = new TableColumn<>(this.txtField_ColumnName.getText());
        this.tbl_DataTable.getColumns().add(column);
        currentTable.getColumns().put(this.txtField_ColumnName.getText(),new Table.Column(this.txtField_ColumnName.getText()));
        currentTable.getColumnsList().add(new Table.Column(this.txtField_ColumnName.getText()));
        tbl_DataTable.refresh();
    }

    @FXML
    void addRecord(ActionEvent event) {
        //todo: نشون دادن اینکه الان چیا تو جدول هستند با استفاده از یک تری ویو

        String[]order=txtField_Addcell.getText().split(" ");
        if (order[0].equals("0"))
        {
            rowCount++;
        }

        currentTable.getColumnsList().get(Integer.parseInt(order[0])).getColRecords().add(new Table.Record(order[1],rowCount,tbl_DataTable.getColumns().get(Integer.parseInt(order[0])).getText()));
       //add to row
        if (currentTable.getRows().get(rowCount)!=null)
        {
            currentTable.getRows().put(rowCount,currentTable.getRows().get(rowCount)+"\n"+tbl_DataTable.getColumns().get(Integer.parseInt(order[0])).getText()+":"+order[1]);
        }
        else
        {
            currentTable.getRows().put(rowCount,tbl_DataTable.getColumns().get(Integer.parseInt(order[0])).getText()+":"+order[1]);
        }


        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Value Added!");
        alert.setContentText(order[1]+ " added to Column with Number "+order[0]);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
        alert.setHeight(300);
        alert.setWidth(300);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.show();
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

    @FXML
    void selectIndex(ActionEvent event) {

        String[]order=txtField_Index.getText().split(" ");

        //specify the column we want
        Table.Column temp = null;
        for (Table.Column cur: currentTable.getColumnsList())
        {
            if (cur.getColKey().equals(order[0]))
            {
                temp=cur;
                break;
            }
        }
        //برای اینکه از صفر تا این مقدار در b+ وارد کنیم

        //اگر ستون مورد نظر پیدا شد
        if (temp!=null)
        {

            currentTable.setTableKey(order[0]);
            currentTable.setIndexesOfKey(new UnsortedTableMap<>());
            //وارد کردن مقادیر در مپ Index ها که به هر استرینگی مجموعه ای از ایندکس های تری رو مپ میکنه
            for (Table.Record cur: temp.getColRecords())
            {
                //key is already existing in the table and seen before
                if (this.currentTable.getIndexesOfKey().get(cur.getValue())!=null)
                {
                    this.currentTable.getIndexesOfKey().get(cur.getValue()).add(cur.getRowKey());
                }
                else
                {
                    ArrayList<Integer>temp1=new ArrayList<Integer>();
                    temp1.add(cur.getRowKey());
                    this.currentTable.getIndexesOfKey().put(cur.getValue(),temp1);
                }
            }

            //fill the b+Tree
            for (int i = 0; i <this.currentTable.getRows().size() ; i++) {
                this.currentTable.getTableTree().insert(i,this.currentTable.getRows().get(i));
            }

            this.currentTable.setTableName(order[1]);
            //adding the table to the tables list
            allTables.add(this.currentTable);

            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Table Saved!!");
            alert.setContentText("Table saved successfully!!\n now you can do query's in the database or build another table");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
            alert.setHeight(300);
            alert.setWidth(300);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.show();
        }

        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Index!!");
            alert.setContentText("Column with this name Does not exist!!");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("Alerts.css").toExternalForm());
            alert.setHeight(300);
            alert.setWidth(300);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_Addcell.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_Back.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_AddColumn.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_IndexSelect.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        txtField_Addcell.getStylesheets().add(getClass().getResource("TextFieldStyle.css").toExternalForm());
        txtField_Index.getStylesheets().add(getClass().getResource("TextFieldStyle.css").toExternalForm());
        txtField_ColumnName.getStylesheets().add(getClass().getResource("TextFieldStyle.css").toExternalForm());
    }


}

