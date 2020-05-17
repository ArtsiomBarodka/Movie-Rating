<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 04.05.2020
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<main id="form-page" class="container-fluid pt-2" >
    <div id="form" class="row justify-content-center align-items-center" >
        <form class="col-9 needs-validation" action="/sign-up" method="POST">
            <h5 class="d-flex justify-content-center">Registration</h5>
            <div class="form-group">
                <label for="inputName">Name</label>
                <input type="text" class="form-control" id="inputName" name="name" placeholder="Enter a name" aria-describedby="nameHelpBlock" required minlength="4" maxlength="45">
                <small id="nameHelpBlock" class="form-text text-muted">
                    Your name must be 4-45 characters long, contain letters and must not contain spaces and numbers.
                </small>
            </div>
            <div class="form-group">
                <label for="inputEmail">Email address</label>
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Enter a email" required>
            </div>
            <div class="form-group">
                <label for="inputPassword">Password</label>
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Enter a password" aria-describedby="passwordHelpBlock" required minlength="6" maxlength="20">
                <small id="passwordHelpBlock" class="form-text text-muted">
                    Your password must be 6-20 characters long, contain letters and numbers, and must not contain spaces.
                </small>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="remember-me" name="rememberMe">
                <label class="form-check-label" for="remember-me">Remember Me</label>
            </div>
            <button type="submit" class="btn btn-warning btn-block">Register</button>
            <hr>
            <h6 class="d-flex justify-content-start mb-2">With Social Media</h6>
            <div id="social-media">
                <a href="#" class="fb btn btn-block">
                    <i class="fa fa-facebook fa-fw"></i>
                </a>
                <a href="#" class="google btn btn-block">
                    <i class="fa fa-google fa-fw"></i>
                </a>
            </div>
            <hr>
            <div class="d-flex justify-content-between align-items-center mb-2">
                <h6 class="m-0">Don’t want to register?</h6>
                <div id="form-footer-btn">
                    <a href="sign-in.html" class="btn btn-secondary btn-sm">Sign in</a>
                    <a href="main.html" class="btn btn-secondary btn-sm">Cancel</a>
                </div>
            </div>
        </form>
    </div>
</main>
