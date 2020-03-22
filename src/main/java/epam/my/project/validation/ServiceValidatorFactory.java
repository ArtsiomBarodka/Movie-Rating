package epam.my.project.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceValidatorFactory {
    public static final Validator<String> EMAIL_VALIDATOR = (email)->{
        String regExp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
                + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
                + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

        if(email == null){
            return false;
        }
        if(email.isEmpty()){
            return false;
        }
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    };

    public static final Validator<String> NAME_VALIDATOR = (name)->{
        int maxSymbols = 45;
        int minSymbols = 4;

        if(name == null){
            return false;
        }
        if(name.isEmpty()){
            return false;
        }
        if(name.length() > maxSymbols || name.length() < minSymbols){
            return false;
        }
        return true;
    };

    public static final Validator<String> PASSWORD_VALIDATOR = (password)->{
        int maxSymbols = 20;
        int minSymbols = 6;

        if(password == null){
            return false;
        }
        if(password.isEmpty()){
            return false;
        }
        if(password.length() > maxSymbols || password.length() < minSymbols){
            return false;
        }
        return true;
    };
}
