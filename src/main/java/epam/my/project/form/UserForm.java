package epam.my.project.form;

public class UserForm {
    private String name;
    private String imageLink;
    private String rating;
    private boolean isBanned;

    public UserForm() {
    }

    public UserForm(String name, String imageLink, String rating, boolean isBanned) {
        this.name = name;
        this.imageLink = imageLink;
        this.rating = rating;
        this.isBanned = isBanned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }
}
