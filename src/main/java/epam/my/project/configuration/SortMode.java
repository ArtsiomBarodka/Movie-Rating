package epam.my.project.configuration;

import epam.my.project.exception.InternalServerErrorException;

public enum SortMode {
    MOVIE_ADDED,

    MOVIE_RATING;

    public static SortMode of(String name) {
        for(SortMode sortMode : SortMode.values()) {
            if(sortMode.name().equalsIgnoreCase(name)) {
                return sortMode;
            }
        }
        throw new InternalServerErrorException("Undefined sort mode: "+String.valueOf(name).toUpperCase());
    }
}