package epam.my.project.configuration;

public enum SortMode {
    ADDED,
    RATING,
    DURATION,
    FEES,
    BUDGET;

    public static SortMode of(String name) {
        SortMode mode = null;
        for(SortMode sortMode : SortMode.values()) {
            if(sortMode.name().equalsIgnoreCase(name)) {
                mode = sortMode;
            }
        }
        return mode;
    }
}
