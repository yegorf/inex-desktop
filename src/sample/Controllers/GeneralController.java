package sample.Controllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import sample.CurrentUser;
import sample.DB.DatabaseHandler;
import sample.Entries.Income;
import sample.Entries.Money;
import sample.Entries.User;

public class GeneralController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameLabel;

    @FXML
    private TableColumn<Income, String> sumColumn;

    @FXML
    private TableView<Income> incomeTable;

    @FXML
    private TableColumn<Income, String> dateColumn;

    @FXML
    private TableColumn<Income, String> reasonColumn;

    @FXML
    private Label eurLabel;

    @FXML
    private Label usdLabel;

    @FXML
    private Label nowRub;

    @FXML
    private Label nowEur;

    @FXML
    private Label nowUah;

    @FXML
    private Label nowUsd;

    @FXML
    private Button signOutButton;

    @FXML
    void initialize() {

        Document doc = null;

        try {
            doc = Jsoup.connect("https://www.yandex.ru/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element kursUSD = doc.select(".inline-stocks__value_inner").first();
        Element kursEUR = doc.select(".inline-stocks__value_inner").last();

        usdLabel.setText(kursUSD.text());
        eurLabel.setText(kursEUR.text());

        String hello = "Hello " + CurrentUser.username + "! " +
                "Got a lot of money today?";
        nameLabel.setText(hello);

        DatabaseHandler dbHandler = new DatabaseHandler();
        Money money = dbHandler.getAllIncome();
        ArrayList<Double> incomeList = money.getIncome();

        nowRub.setText("RUB: " + incomeList.get(0).toString());
        nowUah.setText("UAH: " + incomeList.get(1).toString());
        nowUsd.setText("USD: " + incomeList.get(2).toString());
        nowEur.setText("EUR: " + incomeList.get(3).toString());

        sumColumn.setCellValueFactory(new PropertyValueFactory<>("Sum"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("Reason"));

        incomeTable.setItems(money.getList());

        signOutButton.setOnAction(event -> {
            openNewScene("/sample/fxml_files/sample.fxml");
        });
    }


    public void openNewScene(String window)
    {
        signOutButton.getScene().getWindow().hide();

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