package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.CurrentUser;
import sample.DB.DatabaseHandler;
import sample.Entries.User;
import sample.MyAlert;


public class Controller {

    @FXML
    private Button signUpButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private TextField loginField;

    @FXML
    void initialize() {
        signInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")){
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Norm");
            }
            else {System.out.println("Error empty");}
        });

        signUpButton.setOnAction(event -> {
            openNewScene("/sample/fxml_files/registration.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);

        ResultSet result = dbHandler.getUser(user);
        int counter = 0;

        while(result.next()) {
            counter++;
        }

        if(counter >= 1) {
            CurrentUser.username = loginText;
            CurrentUser.password = loginPassword;
            openNewScene("/sample/fxml_files/general.fxml");
        }
        else {
            MyAlert.show("Error","Incorrect username or password");
        }
    }

    public void openNewScene(String window) {
        signUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        }catch(IOException e){e.printStackTrace();}

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}