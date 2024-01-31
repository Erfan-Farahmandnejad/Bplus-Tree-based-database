module com.example.bplustree {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bplustree to javafx.fxml;
    exports com.example.bplustree;

}