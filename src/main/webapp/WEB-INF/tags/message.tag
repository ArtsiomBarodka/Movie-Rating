<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty MESSAGE}">
    <small class="form-text text-danger">${MESSAGE}</small>
</c:if>