package epam.my.project.configuration;

/**
 * The enum Sort mode.
 */
public enum SortMode {
    /**
     * Added sort mode.
     */
    ADDED,
    /**
     * Rating sort mode.
     */
    RATING,
    /**
     * Duration sort mode.
     */
    DURATION,
    /**
     * Fees sort mode.
     */
    FEES,
    /**
     * Budget sort mode.
     */
    BUDGET;

    /**
     * Of sort mode.
     *
     * @param name the name
     * @return the sort mode
     */
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
