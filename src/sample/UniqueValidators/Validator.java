package sample.UniqueValidators;

import sample.DB.DatabaseHandler;

public abstract class Validator {
    protected abstract boolean check(String text);
}
