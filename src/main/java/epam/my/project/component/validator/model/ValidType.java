package epam.my.project.component.validator.model;

/**
 * The enum Valid type.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public enum ValidType {
    /**
     * The Email.
     */
    EMAIL{
        public boolean isValid(String value){
            if(isEmptyString(value)) {
                return false;
            }
            return value.matches(EMAIL_REGEXP);
        }
    },
    /**
     * The Account name.
     */
    ACCOUNT_NAME{
        public boolean isValid(String value){
            if(isEmptyString(value)) {
                return false;
            }
            String regExp = buildStringRegexp(false, MIN_NAME_LENGTH, MAX_NAME_LENGTH);
            return value.matches(regExp);
        }
    },
    /**
     * The Password.
     */
    PASSWORD{
        public boolean isValid(String value){
            if(isEmptyString(value)) {
                return false;
            }
            String regExp = buildStringRegexp(true, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH);
            return value.matches(regExp);
        }
    },
    /**
     * The Content.
     */
    CONTENT{
        public boolean isValid(String value){
            return !isEmptyString(value);
        }
    },
    /**
     * The Image link.
     */
    IMAGE_LINK{
        public boolean isValid(String value){
            return !isEmptyString(value);
        }
    },
    /**
     * The Year.
     */
    YEAR{
        public boolean isValid(String value){
            if(isEmptyString(value)) {
                return false;
            }
            return value.matches(MOVIE_YEAR_PATTERN);
        }
    },
    /**
     * The Duration.
     */
    DURATION{
        public boolean isValid(String value){
            if(isEmptyString(value)) {
                return false;
            }
            return value.matches(MOVIE_DURATION_REGEXP);
        }
    },
    /**
     * The Movie name.
     */
    MOVIE_NAME{
        public boolean isValid(String value){
            return !isEmptyString(value);
        }
    },
    /**
     * The Description.
     */
    DESCRIPTION{
        public boolean isValid(String value){
            return !isEmptyString(value);
        }
    },
    /**
     * The Number.
     */
    NUMBER{
        public boolean isValid(String value){
            if(isEmptyString(value)) {
                return false;
            }
            return value.matches(IS_NUMBER_REGEXP);
        }
    };

    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 20;
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 45;
    private static final String EMAIL_REGEXP = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String MOVIE_DURATION_REGEXP = "[0-9][0-9]:[0-5][0-9]:[0-5][0-9]";
    private static final String MOVIE_YEAR_PATTERN = "^\\d{4}$";
    private static final String IS_NUMBER_REGEXP = "-?\\d+";

    /**
     * Is valid boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public abstract boolean isValid(String value);

    private static boolean isEmptyString(String s){
        return (s == null || s.trim().isEmpty());
    }

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

}
