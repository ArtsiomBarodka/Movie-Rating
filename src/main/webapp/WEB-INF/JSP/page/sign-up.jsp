<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<main id="form-page" class="container-fluid pt-2" >
    <div id="form" class="row justify-content-center align-items-center" >
        <form class="col-9 needs-validation" action="${contextPath}/app/sign-up" method="POST">
            <h5 class="d-flex justify-content-center"><fmt:message key="sign-up.title"/></h5>
            <tags:message/>
            <div class="form-group">
                <label for="inputName"><fmt:message key="sign-up.name.label"/></label>
                <input type="text" class="form-control" id="inputName" name="name" placeholder="<fmt:message key="sign-up.name.text"/>" aria-describedby="nameHelpBlock" required minlength="4" maxlength="45">
                <small id="nameHelpBlock" class="form-text text-muted">
                    <fmt:message key="sign-up.name.description"/>
                </small>
                <tags:validation field="name" violatons="${VIOLATIONS}"/>
            </div>
            <div class="form-group">
                <label for="inputEmail"><fmt:message key="sign-up.email.label"/></label>
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="<fmt:message key="sign-up.email.text"/>" required>
                <tags:validation field="email" violatons="${VIOLATIONS}"/>
            </div>
            <div class="form-group">
                <label for="inputPassword"><fmt:message key="sign-up.password.label"/></label>
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="<fmt:message key="sign-up.password.text"/>" aria-describedby="passwordHelpBlock" required minlength="6" maxlength="20">
                <small id="passwordHelpBlock" class="form-text text-muted">
                    <fmt:message key="sign-up.password.description"/>
                </small>
                <tags:validation field="password" violatons="${VIOLATIONS}"/>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="remember-me" name="rememberMe">
                <label class="form-check-label" for="remember-me"><fmt:message key="sign-up.remember"/></label>
            </div>
            <button type="submit" class="btn btn-warning btn-block"><fmt:message key="sign-up.submit"/></button>
            <hr>
            <h6 class="d-flex justify-content-start mb-2"><fmt:message key="sign-up.social.title"/></h6>
            <div id="social-media">
                <a href="${contextPath}/app/sign-in/facebook" class="fb btn btn-block">
                    <i class="fa fa-facebook fa-fw"></i>
                </a>
                <button type="button" id="google-sign-in" class="google btn btn-block">
                    <i class="fa fa-google fa-fw"></i>
                </button>
            </div>
            <hr>
            <div class="d-flex justify-content-between align-items-center mb-2">
                <h6 class="m-0"><fmt:message key="sign-up.footer.title"/></h6>
                <div id="form-footer-btn" class="pl-5">
                    <a href="${contextPath}/app/show/sign-in" class="btn btn-secondary btn-sm mb-1"><fmt:message key="sign-up.sign-in"/></a>
                    <a href="${contextPath}/app/movies" class="btn btn-secondary btn-sm mb-1"><fmt:message key="sign-up.back"/></a>
                </div>
            </div>
        </form>
    </div>
</main>
