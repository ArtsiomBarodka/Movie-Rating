package epam.my.project.model.domain;

public class SocialAccount {
    private String name;
    private String email;


    public SocialAccount() {
    }

    public SocialAccount(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
