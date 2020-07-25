package epam.my.project.configuration;

/**
 * Constants for all application.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class Constants {
    /**
     * The constant maximum uploaded image size.
     */
    public static final int MAX_UPLOADED_IMAGE_SIZE = 2 * 1024 * 1024; // 2 Mb

    /**
     * The constant MAX_MOVIES_PER_TOP.
     */
    public static final int MAX_MOVIES_PER_TOP = 3;

    /**
     * The constant DEFAULT_IMAGE_LINK.
     */
    public static final String DEFAULT_IMAGE_LINK = "/static/image/unnamed.jpg";

    /**
     * The constant ITEMS_PER_HTML_PAGE_1.
     */
    public static final int ITEMS_PER_HTML_PAGE_1 = 5;

    /**
     * The constant ITEMS_PER_HTML_PAGE_2.
     */
    public static final int ITEMS_PER_HTML_PAGE_2 = 10;

    /**
     * The constant ITEMS_PER_HTML_PAGE_3.
     */
    public static final int ITEMS_PER_HTML_PAGE_3 = 25;

    /**
     * The constant ITEMS_PER_HTML_PAGE_4.
     */
    public static final int ITEMS_PER_HTML_PAGE_4 = 50;

    /**
     * The constant MAX_COOKIE_AGE.
     */
    public static final int MAX_COOKIE_AGE = 60 * 60 * 24 * 14; //2 weeks

    /**
     * The constant REDIRECT_FROM_FB.
     */
    public static final String REDIRECT_FROM_FB = "/app/from-social/fb";

    /**
     * The constant NOT_FOUND_COMMAND.
     */
    public static final String NOT_FOUND_COMMAND = "NOT_FOUND_COMMAND";

    /**
     * The constant GOOGLE_SOCIAL.
     */
    public static final String GOOGLE_SOCIAL = "GOOGLE_SOCIAL";

    /**
     * The constant FACEBOOK_SOCIAL.
     */
    public static final String FACEBOOK_SOCIAL = "FACEBOOK_SOCIAL";

    private Constants(){}
}
