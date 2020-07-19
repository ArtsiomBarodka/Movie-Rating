package epam.my.project.configuration;

/**
 * The enum Image category.
 */
public enum ImageCategory {

    /**
     * User photo image category.
     */
    USER_PHOTO("/media/users/", 500, 500),

    /**
     * Movie photo image category.
     */
    MOVIE_PHOTO("/media/posters/", 500, 740);

    private final String root;
    private final int width;
    private final int height;

     ImageCategory(String root, int width, int height) {
        this.root = root;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets root.
     *
     * @return the root
     */
    public String getRoot() {
        return root;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Of image category.
     *
     * @param name the name
     * @return the image category
     */
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
