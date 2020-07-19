package epam.my.project.model.domain;

import java.util.List;

/**
 * The type Sql search query.
 */
public class SQLSearchQuery {
    private String query;
    private List<Object> params;

    /**
     * Instantiates a new Sql search query.
     */
    public SQLSearchQuery() {
    }

    /**
     * Instantiates a new Sql search query.
     *
     * @param query  the query
     * @param params the params
     */
    public SQLSearchQuery(String query, List<Object> params) {
        this.query = query;
        this.params = params;
    }

    /**
     * Gets query.
     *
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets query.
     *
     * @param query the query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Gets params.
     *
     * @return the params
     */
    public List<Object> getParams() {
        return params;
    }

    /**
     * Sets params.
     *
     * @param params the params
     */
    public void setParams(List<Object> params) {
        this.params = params;
    }
}
