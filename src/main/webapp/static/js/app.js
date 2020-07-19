$(document).ready(function () {
    showHeaderNavBarToScroll();
    scrollToMainByClickHeader();
    activateSortModeByClick();
    animateSearchFilterByClick();
    validateFormImput();
    activateStarsByRating();
    nextCommentPageAjax();
    previousCommentPageAjax();
    nextMoviePageAjax();
    previousMoviePageAjax();
    uploadImageAjax();
    googleSignUp();
});

function googleSignUp() {
    let button = $("#google-sign-in");
    if (button.length > 0) {
        gapi.load('auth2', function () {
            let auth2 = gapi.auth2.init({
                client_id: googleClientId,
                cookiepolicy: 'profile email',
            });
            auth2.attachClickHandler(button.get()[0], {}, function (googleUser) {
                var authToken = googleUser.getAuthResponse().id_token;
                let auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    window.location = '/app/from-social/google?code=' + authToken;
                });
            }, function () {
                alert("Error")
            });
            console.log("Init google sign-up successful");
        });
    }
}

function uploadImageAjax(){
    $('#fileUploadForm').submit(function(event){
        event.preventDefault();
        let form = $(this);
        let formData = new FormData();
        formData.append("image", $('#inputFile')[0].files[0]);
        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: formData,
            contentType: false,
            processData:false,
            success: function(json){
                $("img").attr("src", json.ImageLink);
                $("#inputImageLink").attr("value", json.ImageLink);
            },
            error: function () {
                alert("Error");
            }
        })
    })
}

function nextCommentPageAjax(){
    $('#next-page').click(function () {
        let comments = $('#comments');
        if(comments.length){
            let pageNumber = parseInt($(comments).attr('data-page-number'));
            let nextPageNumber = pageNumber + 1;
            changePageAjax(this, nextPageNumber,$(comments), $(".list-unstyled"));
        }
    })
}

function previousCommentPageAjax(){
    $('#previous-page').click(function () {
        let comments = $('#comments');
        if(comments.length){
            let pageNumber = parseInt($(comments).attr('data-page-number'));
            let nextPageNumber = pageNumber - 1;
            changePageAjax($(this), nextPageNumber, $(comments), $(".list-unstyled"));
        }
    })
}

function nextMoviePageAjax(){
    $('#next-page').click(function () {
        let films = $('#films');
        if(films.length){
            let pageNumber = parseInt($(films).attr('data-page-number'));
            let nextPageNumber = pageNumber + 1;
            changePageAjax(this, nextPageNumber, $(films), $(".list-unstyled"));
        }
    })
}

function previousMoviePageAjax(){
    $('#previous-page').click(function () {
        let films = $('#films');
        if(films.length){
            let pageNumber = parseInt($(films).attr('data-page-number'));
            let nextPageNumber = pageNumber - 1;
            changePageAjax($(this), nextPageNumber, $(films), $(".list-unstyled"));
        }
    })
}

function changePageAjax(id , nextPageNumber, container, list){
    scrollToMain();
    $(container).css("opacity", "0.3");
    let url = "/app/ajax/html" + location.pathname.substring("/app".length) + '?page=' + nextPageNumber + '&' + location.search.substring(1);
    $.ajax({
        url: url,
        success: function (html) {
            $(list).find('li').each(function () {
                $(this).remove();
            });
            $(list).append(html);
            activateStarsByRating();
            let pageCount = parseInt($(container).attr('data-page-count'));
            $(container).attr('data-page-number', nextPageNumber);
            $('#page-number').text(nextPageNumber);
            $('.page-item').each(function () {
                $(this).removeClass("disabled");
            });
            if(nextPageNumber === pageCount || nextPageNumber === 1) {
                $(id).parent().addClass("disabled");
            }
        },
        error: function () {
            alert("Error");
        }
    });
    $(container).css("opacity", "1");
}

function animateSearchFilterByClick() {
    $(".collapsible").each(function (index) {
        $(this).click(function (event) {
            $(this).toggleClass("active");
            let content = this.nextElementSibling;
            if (content.style.maxHeight) {
                content.style.maxHeight = null;
            } else {
                content.style.maxHeight = content.scrollHeight + "px";
            }
        });
    });
}

function scrollToMain(){
    $("html, body").animate(
        {
            scrollTop: $("main").offset().top - 70,
        },
        100
    );
}

function scrollToMainByClickHeader() {
    $("#header-row-image").click(scrollToMain);
}

function validateFormImput() {
    $(".needs-validation")
        .find(":input")
        .each(function () {
            $(this).keyup(function () {
                if (!document.getElementById($(this).attr("id")).validity.valid) {
                    $(this).removeClass("is-valid");
                    $(this).addClass("is-invalid");
                } else {
                    $(this).removeClass("is-invalid");
                    $(this).addClass("is-valid");
                }
            });
        });
}

function showHeaderNavBarToScroll() {
    if($("#header-row-image").length !== 0){
        window.onscroll = function () {
            if (
                document.body.scrollTop > 200 ||
                document.documentElement.scrollTop > 200
            ) {
                $("#header-navbar").css("top","0");
            } else {
                $("#header-navbar").css("top","-60px");
            }
        }
    } else{
        $("header").css("height","0px");
        $("#header-navbar").css("top","0");
    }
}

function activateSortModeByClick() {
    $(".dropdown-item").click(function () {
        $("#sort-by-dropdown").animate("slow");
        let that = $(this);
        $(".dropdown-item").each(function (index) {
            if ($(this).hasClass("active")) {
                $(this).removeClass("active");
            }
        });
        that.addClass("active");
    });
}

function activateStarsByRating() {
    $(".rating-field").each(function () {
        let rating = $(this).data("raiting-value");
        $(this)
            .find(".fa-star")
            .each(function () {
                let data = $(this).data("raiting");
                if (data < rating) {
                    $(this).addClass("checked");
                } else if (data - 0.25 < rating) {
                    $(this).addClass("checked");
                } else {
                    $(this).removeClass("checked");
                }
            });
    });
}

