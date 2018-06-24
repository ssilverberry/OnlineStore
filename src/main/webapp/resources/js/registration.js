function registration () {
    var username = document.querySelector('.username');
    var password = document.querySelector('.pswrd');
    var email = document.querySelector('.mail');
    var myTextElem = document.querySelector('.mytext');
    var secNameElem = document.querySelector('.secondname');
    var phoneElem = document.querySelector('.phone');
    var address = document.querySelector('.address');
    var modal = document.querySelector('#modal');

    var request = new XMLHttpRequest();

    var usrReqStr = 'name=' + username.value;
    var pswrdReqStr = '&password=' + password.value;
    var mailReqStr = '&email=' + email.value;
    var secNameReqStr = '&surname=' + secNameElem.value;
    var phoneReqStr = '&phone=' + phoneElem.value;
    var adrReqStr = '&address=' + address.value;
    var requestString = 'saveUser?' + usrReqStr + pswrdReqStr + mailReqStr +
        secNameReqStr + phoneReqStr + adrReqStr;

    myTextElem.innerHTML = username.value + ' ' + password.value + ' ' + email.value;

    request.open('get', requestString, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            myTextElem.innerHTML = request.statusText + request.responseText;
        } else {
            myTextElem.innerHTML = request.statusText + request.responseText;
        }
        modal.modal('toggle');
        //window.location.assign("/");
    };

}
