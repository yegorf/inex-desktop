package sample.UniqueValidators;

import sample.DB.DatabaseHandler;

public class ValidationStrategy {

    public static boolean validation(Validator validator, String text) {
        return validator.check(text);
    }
}
