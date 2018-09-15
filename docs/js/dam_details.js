(function () {
    retrieveDamsData();
  })();

    //Function that retrieves json data for building the dams details page
  function retrieveDamsData(){
    fetch(API_DAMS)
    .then((resp) => resp.json())
    .then(function(data) {
        //Calling list builder function
        populateDetails(data);
    })
    .catch(function(error) {
      console.log(error);
    });   
}

function populateDetails(data){
    var damName = getParameterByName('damName');
    
    var currentDam = data.filter((e) => { return e.nameEn == damName });
    var currentDam = currentDam[0];

    if(damName == null || damName == "" || damName == undefined || currentDam == undefined){
        var mainContainer = document.getElementById('main-container');
        mainContainer.innerHTML = '<h1>Dam Not Found</h1>';
        return;
    }

    //Assign data to elements
    //var titleEl = document.getElementById('dam-name');
    var imageEl = document.getElementById('dam-image');
    var nameEl = document.getElementById('dam-name');
    var constructionYearEl = document.getElementById('construction-year');
    var heightEl = document.getElementById('height');
    var capacityEl = document.getElementById('capacity');
    var wikiEl = document.getElementById('wiki-page');
    var mapEl = document.getElementById('map-page');

    imageEl.src = currentDam.imageUrl;
    nameEl.innerHTML = currentDam.nameEn;
    constructionYearEl.innerHTML = currentDam.yearOfConstruction;
    heightEl.innerHTML = currentDam.height;
    capacityEl.innerHTML = currentDam.capacity + ' Cubic Meters';
    if(currentDam.wikipediaUrl != ""){
        wikiEl.href = currentDam.wikipediaUrl;
    }else{
        wikiEl.parentNode.removeChild(wikiEl);
    }

    mapEl.href = 'https://www.google.com/maps/search/?api=1&query='+ currentDam.lat +','+ currentDam.lng +'';
}
  //Function that returns a url variable value
  function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}