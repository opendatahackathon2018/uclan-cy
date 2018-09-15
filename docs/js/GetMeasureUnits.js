const CALL_URL = API_MEASUREUNITS;

/**
 * Creates and makes a call to the API to fetch and show the water measurement units:
 */
function getMeasureUnits() {

    var typesList = document.getElementById("measureUnitsList");

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var jsonObject = JSON.parse(this.responseText);
            for (var i = 0; i < jsonObject.length; i++) {
                var item = document.createElement("li");
                item.innerHTML = "<a href='#' onClick='setCookie(MEASURE_TYPE_COOKIE, \"" + jsonObject[i].nameEn + "\", 365); /*TODO REMOVE ALERT*/ alert(\"The selected type is: \" + getCookie(MEASURE_TYPE_COOKIE)); '>" + jsonObject[i].nameEn + "</a>";
                typesList.appendChild(item);
            }
        }
    };
    xhttp.open("GET", API_MEASUREUNITS, true);
    xhttp.send();
}