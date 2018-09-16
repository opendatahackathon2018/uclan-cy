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

    if(damName === null || damName == "" || damName === undefined || currentDam === undefined){
        var mainContainer = document.getElementById('main-container');
        mainContainer.innerHTML = `
            <div class="row"><div class="col s12 center-align"><h1>Dam Not Found</h1></div></div>
        `;
        return;
    }

    //Assign data to elements
    //var titleEl = document.getElementById('dam-name');
    var imageEl = document.getElementById('dam-image');
    var nameEl = document.getElementsByClassName('dam-name');
    var constructionYearEl = document.getElementById('construction-year');
    var heightEl = document.getElementById('height');
    var capacityEl = document.getElementById('capacity');
    var wikiEl = document.getElementById('wiki-page');
    var mapEl = document.getElementById('map-page');
    imageEl.src = currentDam.imageUrl;

    //Setting all name classes
    for(var e in nameEl){
        nameEl[e].innerHTML = currentDam.nameEn;
    }

    //Setting page title
    document.title = currentDam.nameEn + " Cyprus Water";

    constructionYearEl.innerHTML = currentDam.yearOfConstruction;
    heightEl.innerHTML = currentDam.height;
    capacityEl.innerHTML = (currentDam.capacity/1000000) + ' MCM';
    if(currentDam.wikipediaUrl != "" && currentDam.wikipediaUrl != null){
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