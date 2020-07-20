package epam.my.project.model.validation;


/**
 * The type Validator factory.
 */
public final class ValidatorFactory {
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 20;
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 45;
    private static final String EMAIL_REGEXP = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String IS_NUMBER_REGEXP = "-?\\d+";
    private static final String MOVIE_DURATION_REGEXP = "[0-9][0-9]:[0-5][0-9]:[0-5][0-9]";
    private static final String MOVIE_YEAR_PATTERN = "^\\d{4}$";

    /**
     * The constant ACCOUNT_EMAIL_VALIDATOR.
     */
    public static final Validator<String> ACCOUNT_EMAIL_VALIDATOR = email->{
        if(isEmptyString(email)) {
            return false;
        }
        return email.matches(EMAIL_REGEXP);
    };

    /**
     * The constant ACCOUNT_NAME_VALIDATOR.
     */
    public static final Validator<String> ACCOUNT_NAME_VALIDATOR = name->{
        if(isEmptyString(name)) {
            return false;
        }
        String regExp = buildStringRegexp(false, MIN_NAME_LENGTH, MAX_NAME_LENGTH);
        return name.matches(regExp);
    };

    /**
     * The constant ACCOUNT_PASSWORD_VALIDATOR.
     */
    public static final Validator<String> ACCOUNT_PASSWORD_VALIDATOR = password->{
        if(isEmptyString(password)) {
            return false;
        }
        String regExp = buildStringRegexp(true, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH);
        return password.matches(regExp);
    };

    /**
     * The constant COMMENT_CONTENT_VALIDATOR.
     */
    public static final Validator<String> COMMENT_CONTENT_VALIDATOR = content-> !isEmptyString(content);

    /**
     * The constant IMAGE_LINK_VALIDATOR.
     */
    public static final Validator<String> IMAGE_LINK_VALIDATOR = imageLink-> !isEmptyString(imageLink);

    /**
     * The constant MOVIE_YEAR_VALIDATOR.
     */
    public static final Validator<String> MOVIE_YEAR_VALIDATOR = year-> {
        if(isEmptyString(year)) {
            return false;
        }
        return year.matches(MOVIE_YEAR_PATTERN);
    };

    /**
     * The constant MOVIE_DURATION_VALIDATOR.
     */
    public static final Validator<String> MOVIE_DURATION_VALIDATOR = duration-> {
        if(isEmptyString(duration)) {
            return false;
        }
        return duration.matches(MOVIE_DURATION_REGEXP);
    };

    /**
     * The constant MOVIE_NAME_VALIDATOR.
     */
    public static final Validator<String> MOVIE_NAME_VALIDATOR = name-> !isEmptyString(name);

    /**
     * The constant MOVIE_DESCRIPTION_VALIDATOR.
     */
    public static final Validator<String> MOVIE_DESCRIPTION_VALIDATOR = description-> !isEmptyString(description);

    /**
     * The constant IS_NUMBER_VALUE.
     */
    public static final Validator<String> IS_NUMBER_VALUE = s->{
        if(isEmptyString(s)) {
            return false;
        }
        return s.matches(IS_NUMBER_REGEXP);
    };

    private static String buildStringRegexp(boolean mustHaveNumber,
                                            int minLength,
                                            int maxLength){
        StringBuilder patternBuilder = new StringBuilder("(?=.*[a-z])(?=\\S+$)");
        if(mustHaveNumber){
            patternBuilder.append("(?=.*[0-9])");
        }
        patternBuilder.append(".{")
                .append(minLength)
                .append(",")
                .append(maxLength)
                .append("}");
        return patternBuilder.toString();
    }

    private static boolean isEmptyString(String s){
        return (s == null || s.trim().isEmpty());
    }

    private ValidatorFactory(){

    }
}
