<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 24.04.2020
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="${LOCALE}">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="fragment/styles.jsp"/>
    <title>Movie Rating</title>
</head>
<body>
<jsp:include page="fragment/header.jsp" />
<jsp:include page="${CURRENT_PAGE}" />
<jsp:include page="fragment/footer.jsp" />
<jsp:include page="fragment/scripts.jsp" />
</body>
</html>