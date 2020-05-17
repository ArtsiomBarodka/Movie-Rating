<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 04.05.2020
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<main id="form-page" class="container-fluid pt-5">
    <div id="form" class="row justify-content-center align-items-center">
        <form class="col-9 needs-validation" action="/sign-in" method="POST">
            <h5 class="d-flex justify-content-center">Sign in</h5>
            <h6 class="d-flex justify-content-start">Manually</h6>
            <div class="form-group">
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Enter a email" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Enter a password"
                       aria-describedby="passwordHelpBlock" required minlength="6" maxlength="20">
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="remember-me" name="rememberMe">
                <label class="form-check-label" for="remember-me">Remember Me</label>
            </div>
            <button type="submit" class="btn btn-warning btn-block">Sign in</button>
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
                <h6 class="m-0">Don’t have an account?</h6>
                <div id="form-footer-btn">
                    <a href="sign-up.html" class="btn btn-secondary btn-sm">Sign up</a>
                    <a href="main.html" class="btn btn-secondary btn-sm">Cancel</a>
                </div>
            </div>
        </form>
    </div>
</main>
