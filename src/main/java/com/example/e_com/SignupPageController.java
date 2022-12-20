package com.example.e_com;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupPageController {
    @FXML
    TextField userId,name,password,usertype;
    @FXML
    public void signUp(MouseEvent e) throws SQLException {

        String query = String.format("select UserID from user where UserID = '%s'",userId.getText());
        ResultSet res = HelloApplication.connection.executeQuery(query);
        if(res.next())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Signup");
            alert.setContentText("User already exists,Enter a valid UserID");
            alert.showAndWait();
        }
        else
        {
            String queryInsert = String.format("insert into user values('%s','%s','%s','%s')"
                ,userId.getText(),name.getText(),password.getText(),usertype.getText());
            if(userId.getText().equals(""))
            {
                System.out.println("enter a valid id");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Signup");
                alert.setContentText("Id can't be empty,Enter a valid UserID");
                alert.showAndWait();
            }
            else if(name.getText().equals(""))
            {
                System.out.println("enter a valid name");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Signup");
                alert.setContentText("Enter a valid Name");
                alert.showAndWait();
            }
            else if(password.getText().equals(""))
            {
                System.out.println("enter a valid password");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Signup");
                alert.setContentText("Enter a valid password");
                alert.showAndWait();
            }
            else if(usertype.getText().equals("") || (!usertype.getText().equals("Seller") && !usertype.getText().equals("seller"))
                    && (!usertype.getText().equals("Buyer") && !usertype.getText().equals("buyer")))
            {
                System.out.println("enter a valid type");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Signup");
                alert.setContentText("Enter User Type only Buyer or Seller");
                alert.showAndWait();
            }
            else {
                int response = HelloApplication.connection.updateQuery(queryInsert);
                if (response > 0)
                {
                    System.out.println("user signup");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Signup");
                    alert.setContentText("Signed up successfully");
                    alert.showAndWait();
                }

            }
        }
    }
    @FXML
    public void loginPage(MouseEvent e) throws IOException {
        AnchorPane loginpage = FXMLLoader.load(getClass().getResource("loginpage.fxml"));
        HelloApplication.root.getChildren().add(loginpage);
    }
}
