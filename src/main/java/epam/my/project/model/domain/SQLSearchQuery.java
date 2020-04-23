package epam.my.project.model.domain;

import java.util.List;

public class SQLSearchQuery {
    private String query;
    private List<Object> params;

    public SQLSearchQuery() {
    }

    public SQLSearchQuery(String query, List<Object> params) {
        this.query = query;
        this.params = params;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }
}
