package epam.my.project.model;

public class SocialAccount {
    private final String name;
    private final String email;
    private final String avatarUrl;

    public SocialAccount(String name, String email, String avatarUrl) {
        this.name = name;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
