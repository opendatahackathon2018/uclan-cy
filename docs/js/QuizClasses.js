class Question {

    get question() {
        return this._question;
    }

    get answers() {
        return this._answers;
    }
    constructor(question, answers) {

        this._question = question;
        this._answers = answers;
    }

}

class Answer {

    get answer() {
        return this._answer;
    }

    get correct() {
        return this._correct;
    }

    constructor(answer, correct) {
        this._answer = answer;
        this._correct = correct;
    }

}

class Quiz {

    constructor(questions) {
        this._questions = questions;
        this._currentQuestionIndex = 0;
    }

    get currentQuestionIndex() {
        return this._currentQuestionIndex;
    }

    get questions() {
        return this._questions;
    }

    /**
     * Answers a question.
     */
    answerQuestion(answer) {
        //If all questions have been answered:
        if (this._currentQuestionIndex >= this._questions.length) {
            return -1;
        }
        //If there are questions to be answered:
        else {

            //Get the next question:
            var currentQuestion = this._questions[this._currentQuestionIndex];

            //Find the correct answer:
            var correctAnswer;
            for (var i = 0; i < currentQuestion.answers.length; i++) {
                if (currentQuestion.answers[i].correct) {
                    correctAnswer = currentQuestion.answers[i];
                    break;
                }
            }

            //Compare the user's answer with the correct answer:
            var result;
            console.log("USER ANSWER -> " + answer);
            console.log("CORRECT ANSWER -> " + correctAnswer.answer);
            if (correctAnswer.answer == answer) {
                result = true;
            }
            else result = false;
            return result;
        }
    }

    /**
     * Gets the contents of the next question, or false if quiz is over.
     */
    getNextQuestion() {
        if (this._currentQuestionIndex >= this._questions.length) {
            return false;
        }
        else {
            return this._questions[this._currentQuestionIndex];
        }
    }

}