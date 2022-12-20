package com.example.e_com;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;


public class headerController {
    @FXML
    Label email;
    @FXML
    TextField searchtext;
    @FXML
    public void initialize()//works similar like constructor
    {
        if(!HelloApplication.emailId.equals(""))
        {
            loginbutton.setOpacity(0);
            email.setText(HelloApplication.emailId);
        }
    }
    @FXML
    public void search(MouseEvent e) throws IOException, SQLException {
        ProductPage productPage = new ProductPage();

        header head = new header();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(productPage.productsbySearch(searchtext.getText()));
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
        HelloApplication.root.getChildren().clear();//remove everything from children
        HelloApplication.root.getChildren().addAll(head.root,productPane);
    }
    @FXML
    Button loginbutton,logoutbutton;
    @FXML
    public void login(MouseEvent e) throws IOException {
        AnchorPane loginpage = FXMLLoader.load(getClass().getResource("loginpage.fxml"));
        HelloApplication.root.getChildren().add(loginpage);
    }
    @FXML
    public void logoutappear(MouseEvent e)
    {
        if(logoutbutton.getOpacity() == 0)
        {
            logoutbutton.setOpacity(1);
        }
        else
        {
            logoutbutton.setOpacity(0);
        }
    }

    @FXML
    public void logout(MouseEvent e) throws IOException {
        if(logoutbutton.getOpacity() == 1)
        {
            HelloApplication.emailId = "";
            logoutbutton.setOpacity(0);
            header head = new header();
            HelloApplication.root.getChildren().add(head.root);
        }
    }
}
