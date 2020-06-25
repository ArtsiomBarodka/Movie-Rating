package epam.my.project.configuration;

public enum ImageCategory {

    USER_PHOTO("/media/users/", 500, 500),

    MOVIE_PHOTO("/media/posters/", 500, 740);

    private final String root;
    private final int width;
    private final int height;

     ImageCategory(String root, int width, int height) {
        this.root = root;
        this.width = width;
        this.height = height;
    }

    public String getRoot() {
        return root;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static ImageCategory of(String name) {
         ImageCategory category = null;
         for(ImageCategory imageCategory : ImageCategory.values()) {
            if(imageCategory.name().equalsIgnoreCase(name)) {
                category = imageCategory;
            }
        }
        return category;
    }

}
