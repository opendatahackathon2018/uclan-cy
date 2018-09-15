const dams_list = document.getElementById('dams-list');

(function () {
    retrieveDamsData();
})();

//Function that retrieves json data for building the dams list
function retrieveDamsData() {
    fetch(API_DAMS)
        .then((resp) => resp.json())
        .then(function (data) {
            //Calling list builder function
            buildDamList(data, dams_list);
        })
        .catch(function (error) {
            console.log(error);
        });
}

//Function that returns an object that contains the names and the water percentage level of each dam
function retrieveDamsPercentage() {
    return fetch(API_PERCENTAGES)
        .then((resp) => resp.json())
        .then(function (data) {
            //Initialising the water quage
            var gauge1 = loadLiquidFillGauge("fillgauge1", Math.round(data.totalPercentage * 100));
            //Applying the dam water percentages on the table
            applyDamPercentages(data.damNamesToPercentage);
        })
        .catch(function (error) {
            console.log(error);
        });
}

//Function that builds the list of dams, retrievs the data for the list and the list container element
function buildDamList(data, containerElement) {
    var listItems = [];

    //Itterating through data building the list tiles for each dam
    data.forEach(element => {
        listItems.push(`
        <a href="dam_details.html?damName=${element.nameEn}" class="blue-text text-darken-1 collection-item">${element.nameEn}<div class="secondary-content secondary-content-fix">
        <div class="progress blue lighten-4">
        <div id="${element.nameEn}-perc" class="determinate blue darken-1" style="width: 0%"></div>
        </div>
        </div></a>
        `);
    });

    containerElement.innerHTML = listItems.join("");

    //Calling a function that adds the water pecentage to each dam
    retrieveDamsPercentage();
}

//Function that adds the percentage of the water to each dam list tile
function applyDamPercentages(damPercentages) {
    //Itterating through percentages
    for (var dam in damPercentages) {
        //Finding tile element
        var percentageSpan = document.getElementById(dam + '-perc');

        //Making the percentage ready
        var percentage = Math.round(damPercentages[dam] * 100) + '%';

        //Appling the perccentage to each list tile progress bar
        percentageSpan.setAttribute("style", "width:" + percentage);
    }
}