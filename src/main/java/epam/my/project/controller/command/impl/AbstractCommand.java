package epam.my.project.controller.command.impl;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.SortMode;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.controller.request.RequestParameterNames;

import java.util.Optional;

/**
 * The type Abstract command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
abstract class AbstractCommand extends FrontCommand {
    /**
     * Get sort mode sort mode.
     *
     * @return the sort mode
     */
    protected final SortMode getSortMode(){
        Optional<String> sortMode = Optional.ofNullable(request.getParameter(RequestParameterNames.SORT));
        if(sortMode.isPresent()){
            Optional<SortMode> sort = Optional.ofNullable(SortMode.of(sortMode.get()));
            return sort.orElse(SortMode.RATING);
        }
        return SortMode.RATING;
    }

    /**
     * Get pageable int.
     *
     * @return the int
     */
    protected final int getPageable(){
        Optional<String> pageable = Optional.ofNullable(request.getParameter(RequestParameterNames.PAGEABLE));
        return pageable.map(s -> isNumber(s) ? Integer.parseInt(s) : Constants.ITEMS_PER_HTML_PAGE_1)
                .orElseGet(() -> pageable.map(Integer::parseInt).orElse(Constants.ITEMS_PER_HTML_PAGE_1));
    }

    /**
     * Get page count int.
     *
     * @param totalCount      the total count
     * @param maxCountPerPage the max count per page
     * @return the int
     */
    protected final int getPageCount(int totalCount, int maxCountPerPage){
        int result = totalCount / maxCountPerPage;
        if(result * maxCountPerPage != totalCount) {
            result++;
        }
        return result;
    }

    /**
     * Is number boolean.
     *
     * @param value the value
     * @return the boolean
     */
    protected final boolean isNumber(String value){
        if(value == null || value.trim().isEmpty()) {
            return false;
        }
        return value.matches("-?\\d+");
    }
}
