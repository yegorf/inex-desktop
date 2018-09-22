package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.CurrentUser;
import sample.Entries.Currency;
import sample.Entries.Income;
import sample.Entries.Money;
import sample.Entries.User;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler implements Configs {

    Connection dbConnection;

    public Connection getDbConnection()
        throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort +"/" +dbName + "?" +
                "autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);

        return dbConnection;
    }

    public ArrayList<User> getAllUsersInformation() throws SQLException, ClassNotFoundException {
        ArrayList<User> usersList = new ArrayList<User>();
        String select = "SELECT * FROM " + ConstUser.TABLE;

        Statement statement = getDbConnection().createStatement();
        ResultSet resSet = statement.executeQuery(select);

        while(resSet.next()) {
            User user = new User();
            user.setUsername(resSet.getString(1));
            user.setPassword(resSet.getString(2));
            user.setEmail(resSet.getString(3));
            usersList.add(user);
        }

        return usersList;
    }

    public Money getAllIncome() {

        final char dm = (char)34;
        String select = "SELECT * FROM " + ConstIncome.TABLE +
                " WHERE " + ConstIncome.USER + " = " + dm + CurrentUser.username + dm;

        ObservableList<Income> obsList = FXCollections.observableArrayList();
        try {
            Statement statement = getDbConnection().createStatement();
            ResultSet resSet = statement.executeQuery(select);

            while(resSet.next()) {
                Income income = new Income();
                income.setSum(resSet.getDouble(3));
                income.setDate(resSet.getString(4));
                income.setReason(resSet.getString(5));
                income.setPositive(resSet.getBoolean(6));

                switch (resSet.getString(7)) {
                    case "RUB":
                        income.setCurrency(Currency.RUB);
                        break;
                    case "UAH":
                        income.setCurrency(Currency.UAH);
                        break;
                    case "USD":
                        income.setCurrency(Currency.USD);
                        break;
                    case "EUR":
                        income.setCurrency(Currency.EUR);
                        break;
                    default: income.setCurrency(Currency.UNKNOWN);
                }
                obsList.add(income);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new Money(obsList);
    }


    public void signUpUser(User user) {
        String insert = "INSERT INTO " + ConstUser.TABLE + "(" +
                ConstUser.NAME + "," + ConstUser.PASSWORD + "," +
                ConstUser.EMAIL + ")" +
                "VALUES(?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getUsername());
            prSt.setString(2,user.getPassword());
            prSt.setString(3,user.getEmail());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addIncome(Income income) {
        String insert = "INSERT INTO " + ConstIncome.TABLE + "(" +
                ConstIncome.USER + "," + ConstIncome.SUM + "," +
                ConstIncome.DATE + "," + ConstIncome.REASON + "," +
                ConstIncome.POS + "," + ConstIncome.CURRENCY + ")" +
                "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, CurrentUser.username);
            prSt.setDouble(2, income.getSum());
            prSt.setString(3, income.getDate());
            prSt.setString(4, income.getReason());
            prSt.setBoolean(5, income.isPositive());
            prSt.setString(6, income.getCurrency().toString());

            prSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + ConstUser.TABLE + " WHERE " +
                ConstUser.NAME + "=? AND " + ConstUser.PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getUsername());
            prSt.setString(2,user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}
