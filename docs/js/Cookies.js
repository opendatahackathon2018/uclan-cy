//COOKIE NAMES:
const MEASURE_TYPE_COOKIE = "COOKIE_MEASURE_TYPE";
const MEASURE_TYPE_RATIO_COOKIE = "COOKIE_MEASURE_TYPE_RATIO";
const USER_NAME_COOKIE = "COOKIE_USER_NAME";
const GLASS_OPTION_COOKIE = "COOKIE_GLASS_OPTION";

//GLASS OPTION COOKIE VALUES:
const HALF_FULL = "HALF_FULL";
const HALF_EMPTY = "HALF_EMPTY";

//Sets a cookie with a specific name, value and expiration time.
//If a cookie with the same name already exists, the cookie is replaced.
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}//end setCookie()

//Sets a cookie, but for a long time:
function setCookie(cname, cvalue) {
    var d = new Date();
    var exdays = 365;
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}//end setCookie()

//Returns a cookie's value or empty string if it does not exist.
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1);
        if (c.indexOf(name) === 0) return c.substring(name.length,c.length);
    }//end for
    return "";
}//end getCookie()

//Returns true if cookie exists, false if it does not.
function cookieExists(cname) {
    if (getCookie(cname) !== "") return true; else return false;
}

//Deletes a given cookie.
function deleteCookie(cname) {
    var cvalue = "";
    var d = new Date();
    d.setTime(d.getTime() - 1);
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}//end deleteCookie()