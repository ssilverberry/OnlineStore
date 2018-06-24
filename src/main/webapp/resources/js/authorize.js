function authorize () {
    var mailElem = document.querySelector('.email');
    var pswrdElem = document.querySelector('.pswrd');
    var infoElem = document.querySelector('.info');
    //var succesText = document.querySelector('.user-name');

    var request = new XMLHttpRequest();

    var mailReqStr = 'email=' + mailElem.value;
    var pswrdReqStr = '&password=' + pswrdElem.value;

    var requestStr = 'getByCredential?' + mailReqStr + pswrdReqStr;

    request.open('get', requestStr, true);
    request.send();

    request.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            infoElem.innerHTML = request.statusText + ' ' + mailReqStr + ' ' + pswrdReqStr;
        } else {
            infoElem.innerHTML = request.statusText + ' ' + mailReqStr + ' ' + pswrdReqStr;
        }
    };

}
