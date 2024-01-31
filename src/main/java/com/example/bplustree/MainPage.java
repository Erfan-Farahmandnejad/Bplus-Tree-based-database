package com.example.bplustree;

import com.example.bplustree.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    @FXML
    private Button btn_MakeTable;

    @FXML
    private Button btn_Query;

    @FXML
    private TextField txtField_Welcome;

    private static int TableNumbers=0;

    @FXML
    void Query(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Query.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        HelloApplication.mainStage.setTitle("Query Page");
        scene.setFill(Color.TRANSPARENT);
        HelloApplication.mainStage.setScene(scene);
        HelloApplication.mainStage.show();
    }

    @FXML
    void makeTable(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MakeTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        HelloApplication.mainStage.setTitle("MakeTable Page");
        scene.setFill(Color.TRANSPARENT);
        HelloApplication.mainStage.setScene(scene);
        HelloApplication.mainStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btn_Query.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        btn_MakeTable.getStylesheets().add(getClass().getResource("buttonStyle.css").toExternalForm());
        txtField_Welcome.getStylesheets().add(getClass().getResource("TextFieldStyle.css").toExternalForm());
    }
}
