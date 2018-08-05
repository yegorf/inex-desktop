package sample.UniqueValidators;

import sample.DB.DatabaseHandler;
import sample.Entries.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UniqueEmailValidator extends Validator {

    private DatabaseHandler dbHandler;

    public UniqueEmailValidator(DatabaseHandler db) {
        dbHandler = db;
    }

    @Override
    public boolean check(String email) {
        try {
            ArrayList<User> usersList = dbHandler.getAllUsersInformation();
            for(User user : usersList) {
                if(user.getEmail().equals(email)) {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }
}
