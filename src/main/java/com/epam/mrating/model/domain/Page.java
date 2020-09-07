package com.epam.mrating.model.domain;

import com.epam.mrating.service.exception.InternalServerErrorException;

/**
 * The type Page.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class Page {
    private final int page;
    private final int limit;

    /**
     * Instantiates a new Page.
     *
     * @param limit the limit
     * @throws InternalServerErrorException the internal server error exception
     */
    public Page(int limit) throws InternalServerErrorException {
        this(1, limit);
    }

    /**
     * Instantiates a new Page.
     *
     * @param page  the page
     * @param limit the limit
     * @throws InternalServerErrorException the internal server error exception
     */
    public Page(int page, int limit) throws InternalServerErrorException {
        if(page < 1) {
            throw new InternalServerErrorException("Invalid page value. Should be >= 1");
        }
        if(limit < 1) {
            throw new InternalServerErrorException("Invalid limit value. Should be >= 1");
        }
        this.page = page;
        this.limit = limit;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Get offset int.
     *
     * @return the int
     */
    public int getOffset(){
        return (page-1) * limit;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
