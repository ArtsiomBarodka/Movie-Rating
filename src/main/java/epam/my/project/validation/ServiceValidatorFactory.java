package epam.my.project.validation;

import static epam.my.project.configuration.Constants.*;

public final class ServiceValidatorFactory {
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

        return email.matches(regExp);
    };

    public static final Validator<String> NAME_VALIDATOR = (name)->{
        String regExp = buildStringRegexp(false, true, MIN_NAME_LENGTH, MAX_NAME_LENGTH);

        if(name == null){
            return false;
        }
        if(name.isEmpty()){
            return false;
        }

        return name.matches(regExp);
    };

    public static final Validator<String> PASSWORD_VALIDATOR = (password)->{
        String regExp = buildStringRegexp(true,false, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH);

        if(password == null){
            return false;
        }
        if(password.isEmpty()){
            return false;
        }

        return password.matches(regExp);
    };

    private static String buildStringRegexp(boolean withNumber,
                                            boolean withoutNumber,
                                            int minLength,
                                            int maxLength){
        StringBuilder patternBuilder = new StringBuilder("(?=.*[a-z])(?=\\S+$)");
        if(withNumber){
            patternBuilder.append("(?=.*[0-9])");
        }
        if(withoutNumber){
            patternBuilder.append("(?=\\D+$)");
        }
        patternBuilder.append(".{")
                .append(minLength)
                .append(",")
                .append(maxLength)
                .append("}");
        return patternBuilder.toString();
    }

    private ServiceValidatorFactory(){

    }
}
