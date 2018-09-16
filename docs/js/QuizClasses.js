class Question {
    get recommendedMeasureUnit() {
        return this._recommendedMeasureUnit;
    }

    set recommendedMeasureUnit(value) {
        this._recommendedMeasureUnit = value;
    }

    get question() {
        return this._question;
    }

    get answers() {
        return this._answers;
    }

    get source() {
        return this._source;
    }

    set source(value) {
        this._source = value;
    }

    constructor(question, answers, source, measureUnit) {
        this._recommendedMeasureUnit = measureUnit;
        this._source = source;
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

class QuizStatus {
    get questionResult() {
        return this._questionResult;
    }

    get endOfQuiz() {
        return this._endOfQuiz;
    }
    constructor(questionResult, endOfQuiz) {

        this._questionResult = questionResult;
        this._endOfQuiz = endOfQuiz;
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
            return new QuizStatus(false, true);
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
            if (correctAnswer.answer === answer) {
                result = true;
            }
            else result = false;
            this._currentQuestionIndex++;
            var endOfQuiz = this._currentQuestionIndex >= this._questions.length;
            return new QuizStatus(result, endOfQuiz);
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