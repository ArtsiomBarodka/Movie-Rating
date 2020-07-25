package epam.my.project.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The type Security configuration.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class SecurityConfiguration {
    /**
     * The constant ROLE_USER.
     */
    public static final String ROLE_USER = "USER";
    /**
     * The constant ROLE_ADMIN.
     */
    public static final String ROLE_ADMIN = "ADMIN";

    private static final Map<String, List<String>> MAP_CONFIGURATION = new HashMap<>();

    static {
        init();
    }

    private SecurityConfiguration() {
    }

    private static void init() {
        List<String> url1 = new ArrayList<>();
        url1.add("/app/logout");
        url1.add("/app/user/edit/*");
        url1.add("/app/user/edit/save/*");
        url1.add("/app/user/delete");
        url1.add("/app/comment/delete");
        MAP_CONFIGURATION.put(ROLE_USER, url1);

        List<String> url2 = new ArrayList<>();
        url2.add("/app/logout");
        url2.add("/app/movie/edit/*");
        url2.add("/app/movie/edit/save/*");
        url2.add("/app/movie/delete/*");
        url2.add("/app/movie/new/create");
        url2.add("/app/movie/create/save");
        url2.add("/app/user/edit/*");
        url2.add("/app/user/edit/save/*");
        MAP_CONFIGURATION.put(ROLE_ADMIN, url2);
    }

    /**
     * Gets url for role.
     *
     * @param role the role
     * @return the url for role
     */
    public static List<String> getUrlForRole(String role) {
        return MAP_CONFIGURATION.get(role);
    }

    /**
     * Has url boolean.
     *
     * @param url the url
     * @return the boolean
     */
    public static boolean hasUrl(String url){
        if(Objects.isNull(url)) return false;
        return MAP_CONFIGURATION.values().stream()
                .anyMatch(list -> list.contains(url));
    }
}
