function getQuestions() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {

            var QUESTIONS = [];

            var jsonObject = JSON.parse(this.responseText);
            var questionsObject = jsonObject.questions;
            //console.log(questionsObject);

            for (var i = 0; i < questionsObject.length; i++) {
                //console.log(questionsObject[i].question);
                var body = questionsObject[i].question;
                var recommendedUnit = questionsObject[i].recommendedMeasureUnit;
                var source = questionsObject[i].source;

                var answersObject = questionsObject[i].answers;
                var ANSWERS = [];

                for (var j = 0; j < answersObject.length; j++) {
                    //console.log(answersObject[j].answer);
                    ANSWERS.push(new Answer(answersObject[j].answer, answersObject[j].correct));
                }
                QUESTIONS.push(new Question(body, ANSWERS, source, recommendedUnit));
            }

            //If no questions found, show error message:
            if (QUESTIONS.length < 1) {
                document.getElementById("quizContainer").style.display = "none";
                document.getElementById("errorContainer").style.display = "block";
                return;
            }
            QUIZ = new Quiz(QUESTIONS);

            displayNextQuestion(QUIZ);
            updateUnits();

        }

    };
    xhttp.open("GET", API_QUIZ, true);
    xhttp.send();
}

function displayNextQuestion(quiz) {
    var nextQuestion = quiz.getNextQuestion();
    console.log(nextQuestion.question);

    //Set the HTML elements:
    document.getElementById("questionNumber").innerHTML = quiz._currentQuestionIndex + 1;
    document.getElementById("questionContent").innerHTML = nextQuestion.question;
    document.getElementById("questionSource").innerHTML = "<a href='" + nextQuestion.source + "'>" + nextQuestion.source + "</a>";

    document.getElementById("answerA").value = nextQuestion.answers[0].answer;
    document.getElementById("answerB").value = nextQuestion.answers[1].answer;
    document.getElementById("answerC").value = nextQuestion.answers[2].answer;
    document.getElementById("answerD").value = nextQuestion.answers[3].answer;
    document.getElementById("labelAnswerA").innerHTML = nextQuestion.answers[0].answer;
    document.getElementById("labelAnswerB").innerHTML = nextQuestion.answers[1].answer;
    document.getElementById("labelAnswerC").innerHTML = nextQuestion.answers[2].answer;
    document.getElementById("labelAnswerD").innerHTML = nextQuestion.answers[3].answer;

    document.getElementById("quizContainer").style.display = "block";
    document.getElementById("quizLoader").style.display = "none";
}