package sample.Controllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import sample.CurrentUser;
import sample.DB.DatabaseHandler;
import sample.Entries.Currency;
import sample.Entries.Income;
import sample.Entries.Money;
import sample.Entries.User;
import sample.RateParser;


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
    private TableColumn<Income, String> currencyColumn;

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
    private RadioButton usdRB;

    @FXML
    private RadioButton rubRB;

    @FXML
    private RadioButton uahRB;

    @FXML
    private RadioButton eurRB;

    @FXML
    private RadioButton incomeRB;

    @FXML
    private RadioButton expendRB;

    @FXML
    private Button addButton;

    @FXML
    private TextField sumTextBox;

    @FXML
    private TextField reasonTextBox;

    @FXML
    void initialize() {

        RateParser rateParser = new RateParser();

        usdLabel.setText(rateParser.getUsdRate());
        eurLabel.setText(rateParser.getEurRate());

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

        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("Currency"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("Sum"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("Reason"));

        incomeTable.setItems(money.getList());

        signOutButton.setOnAction(event -> {
            openNewScene("/sample/fxml_files/sample.fxml");
        });

        addButton.setOnAction(event -> {

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow =
                    new SimpleDateFormat("yyyy-MM-dd");

            Income income = new Income();

            income.setSum(Double.parseDouble(sumTextBox.getText()));
            income.setReason(reasonTextBox.getText());
            income.setDate(formatForDateNow.format(dateNow));

            if(incomeRB.isSelected()) {
                income.setPositive(true);
            } else {
                income.setPositive(false);
            }

            if(rubRB.isSelected()) {
                income.setCurrency(Currency.RUB);
            }
            else if(uahRB.isSelected()) {
                income.setCurrency(Currency.UAH);
            }
            else if(usdRB.isSelected()) {
                income.setCurrency(Currency.USD);
            }
            else if(eurRB.isSelected()) {
                income.setCurrency(Currency.EUR);
            }

            dbHandler.addIncome(income);
            clearAddParameters();

        });
    }

    public void clearAddParameters() {
        sumTextBox.clear();
        reasonTextBox.clear();
        rubRB.setSelected(true);
        incomeRB.setSelected(true);
    }

    public void openNewScene(String window) {
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