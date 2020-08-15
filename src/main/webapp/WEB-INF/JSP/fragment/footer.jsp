<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<footer class="container-fluid">
    <div class="row justify-content-center text-white" id="footer">
        <div class="col-12 align-self-center pl-5">
            <fmt:message key="footer.author"/>
            <div class="d-inline-flex float-right pr-sm-4">
                <a href="${contextPath}/app/locale/change?locale=en&return=${CURRENT_URI}" class="text-light px-1">En</a>|
                <a href="${contextPath}/app/locale/change?locale=ru&return=${CURRENT_URI}" class="text-light px-1">Ru</a>|
                <a href="${contextPath}/app/locale/change?locale=be&return=${CURRENT_URI}" class="text-light px-1">Be</a>
            </div>
        </div>
    </div>
</footer>
