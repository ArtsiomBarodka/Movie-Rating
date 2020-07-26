<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>
<c:set var="action" value="${fn:contains(CURRENT_URI,'user') ? '/upload/user_photo' : '/upload/movie_photo'}"/>


<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel"><fmt:message key="modal.upload"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="fileUploadForm" method="POST" action="${action}" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="inputFile"><fmt:message key="modal.label"/></label>
                        <input type="file" name="image" class="form-control-file" id="inputFile" accept=".jpg, .jpeg, .png">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-dark px-4" form="fileUploadForm"><fmt:message key="modal.submit"/></button>
                <button type="button" class="btn btn-dark px-4" data-dismiss="modal"><fmt:message key="modal.back"/></button>
            </div>
        </div>
    </div>
</div>