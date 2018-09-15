const CALL_URL = API_MEASUREUNITS;

/**
 * Creates and makes a call to the API to fetch and show the water measurement units:
 */
function getMeasureUnits() {

    var typesList = document.getElementById("measureUnitsList");

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //this.responseText...
            //TODO
        }
    };
    xhttp.open("GET", CALL_URL, true);
    xhttp.send();
}