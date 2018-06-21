package sample.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DB.DatabaseHandler;
import sample.User;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button regSignUpButton;

    @FXML
    private TextField regUsernameField;

    @FXML
    private TextField regEmailField;

    @FXML
    private PasswordField regPasswordField;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        regSignUpButton.setOnAction(event -> {

            User user = new User(regUsernameField.getText(),
                    regPasswordField.getText(),regEmailField.getText());

            dbHandler.signUpUser(user);
        });
    }
}
