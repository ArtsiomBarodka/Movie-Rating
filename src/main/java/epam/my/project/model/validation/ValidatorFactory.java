package epam.my.project.model.validation;

public final class ValidatorFactory {
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 20;
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 45;
    private static final String EMAIL_REGEXP = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
            + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private static final String IS_NUMBER_REGEXP = "-?\\d+";

    public static final Validator<String> EMAIL_VALIDATOR = (email)->{
        if(email == null){
            return false;
        }

        if(email.isEmpty()){
            return false;
        }

        return email.matches(EMAIL_REGEXP);
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

    public static final Validator<String> IS_NUMBER_VALUE = (userRating)->{
        if(userRating == null){
            return false;
        }
        if(userRating.isEmpty()){
            return false;
        }

        return userRating.matches(IS_NUMBER_REGEXP);
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

    private ValidatorFactory(){

    }
}
