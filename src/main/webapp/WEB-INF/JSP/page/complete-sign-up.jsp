<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 14.05.2020
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main id="form-page" class="container-fluid pt-2" >
    <div id="form" class="row justify-content-center align-items-center" >
        <form class="col-9 needs-validation" action="/complete-sign-up">
            <h5 class="d-flex justify-content-center">Complete sign up</h5>
            <div class="form-group">
                <label for="inputName">Name</label>
                <input type="text" class="form-control" id="inputName" name="name" placeholder="Enter a name" aria-describedby="nameHelpBlock" required minlength="4" maxlength="45">
                <small id="nameHelpBlock" class="form-text text-muted">
                    Your name must be 4-45 characters long, contain letters and must not contain spaces and numbers.
                </small>
            </div>
            <button type="submit" class="btn btn-warning btn-block">Register</button>
            <hr>
            <div class="d-flex justify-content-between align-items-center mb-2">
                <h6 class="m-0">Don’t want to register?</h6>
                <div id="form-footer-btn">
                    <a href="/sign-in.jsp" class="btn btn-secondary btn-sm">Sign in</a>
                    <a href="main.html" class="btn btn-secondary btn-sm">Cancel</a>
                </div>
            </div>
        </form>
    </div>
</main>
