var signIn = function () {
    var signinElem = document.querySelector('.sign-link');
    var authElem = document.querySelector('.auth-elem');
    var closeElem = document.querySelector('.return');
    var usernameElem = document.querySelector('.user-name');

    function displayOn() {
        authElem.style.visibility = 'visible';
    }

    function displayOff() {
        authElem.style.visibility = 'hidden';
    }
    window.onload = function () {
        authElem.style.visibility = 'hidden';
    };
    closeElem.addEventListener('click', displayOff);
    signinElem.addEventListener('click', displayOn);

    var fname = sessionStorage.getItem('fname');
    var sname = sessionStorage.getItem('sname');


    if (sessionStorage.getItem('email') != null) {
        usernameElem.innerHTML = fname + ' ' + sname;
        //signinElem.removeEventListener('click', displayOn);
    }
};
signIn();

/*
window.onload = function () {
    var usernameElem = document.querySelector('.user-name');
    if (localStorage.getItem('fname'))
        usernameElem.innerHTML = localStorage.getItem('fname') + ' ' + localStorage.getItem('sname');
};*/
