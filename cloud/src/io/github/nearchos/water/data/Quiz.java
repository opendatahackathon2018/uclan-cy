package io.github.nearchos.water.data;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz implements Serializable {

    private List<Question> questions;

    public Quiz(Question [] questions) {
        this.questions = Arrays.asList(questions);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    private static class Question implements Serializable {
        private String question;
        private String imageUrl;
        private List<Answer> answers;

        public Question(String question, String imageUrl, Answer [] answers) {
            this.question = question;
            this.imageUrl = imageUrl;
            this.answers = Arrays.asList(answers);
            // shuffle answers
            Collections.shuffle(this.answers);
        }

        public String getQuestion() {
            return question;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public List<Answer> getAnswers() {
            return answers;
        }
    }

    private static class Answer implements Serializable {
        private double answer;
        private boolean correct;

        public Answer(double answer, boolean correct) {
            this.answer = answer;
            this.correct = correct;
        }

        public double getAnswer() {
            return answer;
        }


        public boolean isCorrect() {
            return correct;
        }
    }

    static Question question1 = new Question(
                "What is the total capacity of Cyprus' dams?",
                null,
                new Answer[] {
                    new Answer(291338000d, true),
                    new Answer(291338000d / 2, false),
                    new Answer(291338000d / 2.5, false),
                    new Answer(291338000d * 3, false),
                });

    static Question question2 = new Question(
                "How much water inflow was there in dams in Cyprus in 2017?",
                null,
                new Answer[] {
                    new Answer(42198000d, true),
                    new Answer(291338000d / 2, false),
                    new Answer(291338000d / 2.5, false),
                    new Answer(291338000d * 3, false),
                });

    public static Quiz SAMPLE_QUIZ = new Quiz(new Question [] { question1, question2 });
}