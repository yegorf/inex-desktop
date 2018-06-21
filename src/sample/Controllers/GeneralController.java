package sample.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.User;

public class GeneralController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameLabel;

    @FXML
    void initialize() {

        setHello(Controller.u);
    }

    void setHello(User user)
    {
        String hello = "Hello " + user.getUsername() + "! " +
                "Got a lot of money today?";

        nameLabel.setText(hello);
    }
}