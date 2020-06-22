<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>


<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>


<main id="form-page" class="container-fluid pt-5">
    <div id="form" class="row justify-content-center align-items-center">
        <form class="col-9 needs-validation" action="/app/sign-in" method="POST">
            <h5 class="d-flex justify-content-center"><fmt:message key="sign-in.title"/></h5>
            <h6 class="d-flex justify-content-start"><fmt:message key="sign-in.manually.title"/></h6>
            <tags:message/>
            <div class="form-group">
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="<fmt:message key="sign-in.email.text"/>" required>
                <tags:validation field="email" violatons="${VIOLATIONS}"/>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="<fmt:message key="sign-in.password.text"/>"
                       aria-describedby="passwordHelpBlock" required minlength="6" maxlength="20">
                <tags:validation field="password" violatons="${VIOLATIONS}"/>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="remember-me" name="rememberMe">
                <label class="form-check-label" for="remember-me"><fmt:message key="sign-in.remember"/></label>
            </div>
            <button type="submit" class="btn btn-warning btn-block"><fmt:message key="sign-in.submit"/></button>
            <hr>
            <h6 class="d-flex justify-content-start mb-2"><fmt:message key="sign-in.social.title"/></h6>
            <div id="social-media">
                <a href="/app/sign-in/facebook" class="fb btn btn-block">
                    <i class="fa fa-facebook fa-fw"></i>
                </a>
                <button type="button" id="google-sign-in" class="google btn btn-block">
                    <i class="fa fa-google fa-fw"></i>
                </button>
            </div>
            <hr>
            <div class="d-flex justify-content-between align-items-center mb-2">
                <h6 class="m-0"><fmt:message key="sign-in.footer.title"/></h6>
                <div id="form-footer-btn" class="pl-5">
                    <a href="/app/show/sign-up" class="btn btn-secondary btn-sm mb-1"><fmt:message key="sign-in.sign-up"/></a>
                    <a href="/app/movies" class="btn btn-secondary btn-sm mb-1"><fmt:message key="sign-in.back"/></a>
                </div>
            </div>
        </form>
    </div>
</main>
