package sample.UniqueValidators;

import sample.DB.DatabaseHandler;
import sample.Entries.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UniqueNameValidator extends Validator {

    private DatabaseHandler dbHandler;

    public UniqueNameValidator(DatabaseHandler db) {
        dbHandler = db;
    }

    @Override
    public boolean check(String name) {
        try {
            ArrayList<User> usersList = dbHandler.getAllUsersInformation();
            for(User user : usersList) {
                if(user.getUsername().equals(name)) {
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
