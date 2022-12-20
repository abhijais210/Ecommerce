module com.example.e_com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.e_com to javafx.fxml;
    exports com.example.e_com;
}