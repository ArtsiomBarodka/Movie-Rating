<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="/WEB-INF/tags.tld"%>

<%@ attribute name="field" required="true" type="java.lang.String"%>
<%@ attribute name="violatons" required="true" type="com.epam.mrating.component.validator.model.Violations"%>

<c:if test="${not empty violatons}">
    <small class="form-text text-danger">
         <u:violation field="${field}" violations="${violatons}"/>
    </small>
</c:if>