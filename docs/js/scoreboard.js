(function () {
    retrieveScores();
})();

//Function for fetching scoreboard data
function retrieveScores(){
    fetch(API_SCOREBOARD)
    .then((resp) => resp.json())
    .then(function (data) {
        console.log(data);
        var scoreboard = document.getElementById('scoreboard');
        var scoreboardHTML = [];
        var count = 0;

        data.forEach((e)=>{
            count++;
            scoreboardHTML.push(`<tr><td>${count}</td><td>${e.nickname}</td><td>${e.score}</td></tr>`);
        });

        scoreboard.innerHTML = scoreboardHTML.join("");
       
    })
    .catch(function (error) {
        console.log(error);
    });
}