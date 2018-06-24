function authorize () {
    var mailElem = document.querySelector('.email');
    var pswrdElem = document.querySelector('.pswrd');
    var infoElem = document.querySelector('.info');
    var usernameElem = document.querySelector('.user-name');

    var request = new XMLHttpRequest();

    var mailReqStr = 'email=' + mailElem.value.toString();
    var pswrdReqStr = '&password=' + pswrdElem.value.toString();

    var requestStr = 'getByCredential?' + mailReqStr + pswrdReqStr;

    //var emailSS = localStorage.getItem('email');
    //var pswrdSS = localStorage.getItem('pswrd');

    //var fname = sessionStorage.getItem('fname');
    //var sname = sessionStorage.getItem('sname');

    /*if (mailElem.value === emailSS &
        pswrdElem.value === pswrdSS)
        usernameElem.innerHTML = fname + ' ' + sname;
    else
        usernameElem.innerHTML = 'unnamed user';*/

    request.open('get', requestStr, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log(request.responseText + ' ' + request.statusText);
            sessionStorage.setItem('name', usernameElem.toString());
        }
    };

    setTimeout(function() {
        infoElem.style.visibility = 'hidden';
    }, 5000);
}
