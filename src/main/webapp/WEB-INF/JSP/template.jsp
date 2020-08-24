<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<!DOCTYPE html>
<html lang="${LOCALE}">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="google-signin-client_id" content="422052970161-8afc33aovdmblfsrbq5kevumpcb5edfs.apps.googleusercontent.com">
    <jsp:include page="fragment/styles.jsp"/>
    <title><fmt:message key="app.title"/></title>
</head>
<body>
<jsp:include page="fragment/header.jsp" />
<jsp:include page="${CURRENT_PAGE}" />
<jsp:include page="fragment/footer.jsp" />
<jsp:include page="fragment/scripts.jsp" />
</body>
</html>