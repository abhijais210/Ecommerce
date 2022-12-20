package com.example.e_com;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginpageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
        String query = String.format("select * from user where userID = '%s' AND pass = '%s' ",email.getText(),password.getText());
        //query = select * from user where emailID = 'shiv@gmail.com. AND pass = 123456
        ResultSet res = HelloApplication.connection.executeQuery(query);
        System.out.println(res.toString());

        if(res.next())
        {
            HelloApplication.emailId = res.getString("userID");
            String userType = res.getString("userType");
            if(userType.equals("seller"))
            {
                AnchorPane sellerpage = FXMLLoader.load(getClass().getResource("sellerpage.fxml"));
                HelloApplication.root.getChildren().add(sellerpage);
            }
            else
            {
                ProductPage productPage = new ProductPage();

                header head = new header();
                AnchorPane productPane = new AnchorPane();
                productPane.getChildren().add(productPage.products());
                productPane.setLayoutX(150);
                productPane.setLayoutY(100);
                HelloApplication.root.getChildren().clear();//remove everything from children
                HelloApplication.root.getChildren().addAll(head.root,productPane);
            }
            System.out.println("user exist in database");
        }
        else
        {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Login Failed, Please check credetials and try again");
            dialog.showAndWait();
        }
    }
    @FXML
    public void signup(MouseEvent e) throws IOException {
        AnchorPane signupPage = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        HelloApplication.root.getChildren().add(signupPage);
    }
}
