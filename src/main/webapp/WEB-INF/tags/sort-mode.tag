<%@ tag import="epam.my.project.configuration.SortMode" %>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="rating" value="<%=SortMode.RATING%>"/>
<c:set var="added" value="<%=SortMode.ADDED%>"/>


<div id="sort-by">
    <div class="dropdown">
        <button class="btn btn-dark dropdown-toggle" type="button" id="sort-by-dropdown" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false"> Sort by</button>
        <div class="dropdown-menu" aria-labelledby="sort-by-dropdown">
            <a href="/movie?sort=${rating}" class="dropdown-item ${SORT_MODE == rating ? "active" : ""}" type="button">${rating}</a>
            <a href="/movie?sort=${added}" class="dropdown-item ${SORT_MODE == added ? "active" : ""}" type="button">${added}</a>
        </div>
    </div>
</div>