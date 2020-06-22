<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<main id="form-page" class="container-fluid pt-2" >
    <div id="form" class="row justify-content-center pt-5" >
        <form class="col-9 needs-validation" action="/app/sign-up/complete">
            <h5 class="d-flex justify-content-center"><fmt:message key="sign-up.complete.title"/></h5>
            <tags:message/>
            <div class="form-group">
                <label for="inputName"><fmt:message key="sign-up.name.label"/></label>
                <input type="text" class="form-control" id="inputName" name="name" value="${SOCIAL_ACCOUNT.name}" placeholder="<fmt:message key="sign-up.name.text"/>" aria-describedby="nameHelpBlock" required minlength="4" maxlength="45">
                <small id="nameHelpBlock" class="form-text text-muted">
                    <fmt:message key="sign-up.name.description"/>
                </small>
                <tags:validation field="name" violatons="${VIOLATIONS}"/>
            </div>
            <button type="submit" class="btn btn-warning btn-block"><fmt:message key="sign-up.submit"/></button>
            <hr>
            <div class="d-flex justify-content-between align-items-center mb-2">
                <h6 class="m-0"><fmt:message key="sign-up.footer.title"/></h6>
                <div id="form-footer-btn" class="pl-5">
                    <a href="/app/show/sign-in" class="btn btn-secondary btn-sm mb-1"><fmt:message key="sign-up.sign-in"/></a>
                    <a href="/app/movies" class="btn btn-secondary btn-sm mb-1"><fmt:message key="sign-up.back"/></a>
                </div>
            </div>
        </form>
    </div>
</main>
