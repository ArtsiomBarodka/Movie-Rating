package epam.my.project.model.domain;

import epam.my.project.exception.InternalServerErrorException;

public class Page {
    private final int page;
    private final int limit;

    public Page(int limit) throws InternalServerErrorException {
        this(1, limit);
    }

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

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }

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
