<%@ tag import="epam.my.project.configuration.Constants" %>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageable1" value="<%=Constants.ITEMS_PER_HTML_PAGE_1%>"/>
<c:set var="pageable2" value="<%=Constants.ITEMS_PER_HTML_PAGE_2%>"/>
<c:set var="pageable3" value="<%=Constants.ITEMS_PER_HTML_PAGE_3%>"/>
<c:set var="pageable4" value="<%=Constants.ITEMS_PER_HTML_PAGE_4%>"/>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<div id="load-more" class="d-flex justify-content-center">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li class="page-item mx-1 disabled">
                <button class="page-link" id="previous-page">
                    <i class="fa fa-chevron-left" aria-hidden="true"></i>
                </button>
            </li>
            <li class="page-item mx-1 pt-1">
                <span id="page-number">1</span> / ${PAGE_COUNT}
            </li>
            <li class="page-item mx-1 ${PAGE_COUNT > 1 ? "" : "disabled"}">
                <button class="page-link" id="next-page">
                    <i class="fa fa-chevron-right" aria-hidden="true"></i>
                </button>
            </li>
            <li class="page-item mx-1">
                <div id="page-by">
                    <div class="dropdown">
                        <button class="btn btn-outline-dark dropdown-toggle" type="button" id="page-by-dropdown" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            ${PAGEABLE}
                        </button>
                        <div class="dropdown-menu" aria-labelledby="page-by-dropdown">
                            <h6 class="dropdown-header">Items per page</h6>
                            <a href="${pageContext.servletContext.contextPath}?${QUERY}sort=${SORT_MODE}&pageable=${pageable1}" class="dropdown-item ${PAGEABLE == pageable1 ? "active" : ""}" type="button">${pageable1}</a>
                            <a href="${pageContext.servletContext.contextPath}?${QUERY}sort=${SORT_MODE}&pageable=${pageable2}" class="dropdown-item ${PAGEABLE == pageable2 ? "active" : ""}" type="button">${pageable2}</a>
                            <a href="${pageContext.servletContext.contextPath}?${QUERY}sort=${SORT_MODE}&pageable=${pageable3}" class="dropdown-item ${PAGEABLE == pageable3 ? "active" : ""}" type="button">${pageable3}</a>
                            <a href="${pageContext.servletContext.contextPath}?${QUERY}sort=${SORT_MODE}&pageable=${pageable4}" class="dropdown-item ${PAGEABLE == pageable4 ? "active" : ""}" type="button">${pageable4}</a>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </nav>
</div>