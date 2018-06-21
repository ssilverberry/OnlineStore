function registration () {
    var username = document.querySelector('.username');
    var password = document.querySelector('.pswrd');
    var email = document.querySelector('.mail');
    var myTextElem = document.querySelector('.mytext');
    var secNameElem = document.querySelector('.secondname');
    var phoneElem = document.querySelector('.phone');
    var address = document.querySelector('.address');

    var request = new XMLHttpRequest();

    var usrReqStr = 'usr=' + username.value;
    var pswrdReqStr = '&password=' + password.value;
    var mailReqStr = '&email=' + email.value;
    var secNameReqStr = '&surname=' + secNameElem.value;
    var phoneReqStr = '&phone=' + phoneElem.value;
    var adrReqStr = '&address=' + address.value;
    var requestString = 'registration?' + usrReqStr + pswrdReqStr + mailReqStr +
        secNameReqStr + phoneReqStr + adrReqStr;

    myTextElem.innerHTML = username.value + ' ' + password.value + ' ' + email.value;

    localStorage.setItem('fname', String(username.value));
    localStorage.setItem('sname', String(secNameElem.value));
    localStorage.setItem('email', String(email.value));
    localStorage.setItem('pswrd', String(password.value));

    request.open('get', requestString, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            //console.log (usr + ' ' + pswrd + ' ' + mail);
            myTextElem.innerHTML = request.responseText;
        }
    };
    console.log('Function executed.');
}
