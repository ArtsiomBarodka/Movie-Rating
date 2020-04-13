package epam.my.project.configuration;

import java.util.*;

public final class SecurityConfiguration {
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    private static final Map<String, List<String>> MAP_CONFIGURATION = new HashMap<>();

    static {
        init();
    }

    private SecurityConfiguration() {
    }

    private static void init() {
        List<String> url1 = new ArrayList<>();

        url1.add("/userInfo");
        url1.add("/employeeTask");

        MAP_CONFIGURATION.put(ROLE_USER, url1);

        List<String> url2 = new ArrayList<>();

        url2.add("/userInfo");
        url2.add("/managerTask");

        MAP_CONFIGURATION.put(ROLE_ADMIN, url2);
    }

    public static Set<String> getAllAppRoles() {
        return MAP_CONFIGURATION.keySet();
    }

    public static List<String> getUrlForRole(String role) {
        return MAP_CONFIGURATION.get(role);
    }

    public static boolean hasUrl(String url){
        if(Objects.isNull(url)) return false;
        return MAP_CONFIGURATION.values().stream()
                .anyMatch(list -> list.contains(url));
    }
}
