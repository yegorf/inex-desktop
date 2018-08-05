package sample.Controllers;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DB.DatabaseHandler;
import sample.Entries.User;
import sample.MyAlert;
import sample.UniqueValidators.EmailValidator;
import sample.UniqueValidators.UniqueEmailValidator;
import sample.UniqueValidators.UniqueNameValidator;
import sample.UniqueValidators.ValidationStrategy;

public class RegistrationController {

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

            String name = regUsernameField.getText();
            String password = regPasswordField.getText();
            String email = regEmailField.getText();

            boolean validation = true;

            if(!ValidationStrategy.validation(new EmailValidator(), email)) {
                MyAlert.show("Error", "Incorrect email!");
                validation = false;
            }
            if(!ValidationStrategy.validation(new UniqueNameValidator(dbHandler), name)) {
                MyAlert.show("Error", "This username is already taken!");
                validation = false;
            }
            if(!ValidationStrategy.validation(new UniqueEmailValidator(dbHandler), email)) {
                MyAlert.show("Error", "This email is already registrated in system!");
                validation = false;
            }


            if(validation) {
                User user = new User(name, password, email);

                dbHandler.signUpUser(user);
                openNewScene("/sample/fxml_files/sample.fxml");
            }
        });
    }


    public void openNewScene(String window) {
        regSignUpButton.getScene().getWindow().hide();

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
