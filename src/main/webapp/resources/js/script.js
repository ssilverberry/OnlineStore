var signinElem = document.querySelector('.nav__signin');
var basketElem = document.querySelector('.basket');
var mainPartList = document.querySelector('.content__mainpart');
var mainElem = document.querySelector('.main');
var authorization = document.querySelector('.authorization');
var authorization_overlay = document.querySelector('.overlay_container');
var closeAuthElem = document.querySelector('.authorization__close');
var flag = true;

var request = new XMLHttpRequest();
var path = "productCategoriesId?";

request.onreadystatechange = function () {
    if (request.readyState === 4) {
        if (request.status === 200) {
            mainPartList.innerHTML = request.responseText;
        } else {
            console.log('An error occurred during your request: ' +  request.status + ' ' + request.statusText);
        }
    }
};
signinElem.addEventListener('click', function() {
    authorizeOverlay();
    if (flag) {
        authorization.classList.remove('display_none');
        basketElem.classList.add('display_none');
        //mainElem.classList.add('display_none');
        flag = false;
    }

});
closeAuthElem.addEventListener('click', function () {
    if (!flag) {
        authorization.classList.add('display_none');
        basketElem.classList.remove('display_none');
        authorizeOverlay();
        flag = true;
    }
});
var getCategoriesList = function (id) {
    console.log("request started !");
    request.open('get', path + "category_id=" + id);
    request.send();
};
var authorizeOverlay = function () {
    authorization_overlay.classList.toggle('overlay');
};
//authorization_overlay.addEventListener('click', authorizeOverlay);