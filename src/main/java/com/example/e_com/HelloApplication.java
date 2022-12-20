package com.example.e_com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static String emailId;
    public  static Group root;
    public static DatabaseConnection connection;
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        emailId = "";
        connection = new DatabaseConnection();
        root = new Group();
        header page = new header();
        ProductPage productPage = new ProductPage();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(productPage.products());
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
        root.getChildren().addAll(page.root,productPane);

        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e ->{
            try {
                connection.con.close();
                System.out.println("close succesfully");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}