package epam.my.project.configuration;

public class Constants {
    public static final int MAX_UPLOADED_IMAGE_SIZE = 2 * 1024 * 1024; // 2 Mb

    public static final int MAX_MOVIES_PER_TOP = 3;

    public static final String DEFAULT_IMAGE_LINK = "/static/image/unnamed.jpg";

    public static final int ITEMS_PER_HTML_PAGE_1 = 5;

    public static final int ITEMS_PER_HTML_PAGE_2 = 10;

    public static final int ITEMS_PER_HTML_PAGE_3 = 25;

    public static final int ITEMS_PER_HTML_PAGE_4 = 50;

    public static final int MAX_COOKIE_AGE = 60 * 60 * 24 * 14; //2 weeks

    public static final String REDIRECT_FROM_FB = "/app/from-social/fb";

    public static final String NOT_FOUND_COMMAND = "NOT_FOUND_COMMAND";

    public static final String GOOGLE_SOCIAL = "GOOGLE_SOCIAL";

    public static final String FACEBOOK_SOCIAL = "FACEBOOK_SOCIAL";

    private Constants(){}
}
