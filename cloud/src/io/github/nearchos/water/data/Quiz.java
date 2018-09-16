package io.github.nearchos.water.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz implements Serializable {

    private Mood mood;
    private List<Question> questions;

    Quiz(Mood mood, Question[] questions) {
        this(mood, Arrays.asList(questions));
    }

    public Quiz(Mood mood, List<Question> questions) {
        this.mood = mood;
        this.questions = questions;
    }

    public Mood getMood() {
        return mood;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public static class Question implements Serializable {
        private String question;
        private String recommendedMeasureUnit;
        private String imageUrl;
        private String source;
        private List<Answer> answers;

        public Question(String question, String recommendedMeasureUnit, String imageUrl, String source, Answer[] answers) {
            this.question = question;
            this.recommendedMeasureUnit = recommendedMeasureUnit;
            this.imageUrl = imageUrl;
            this.source = source;
            this.answers = Arrays.asList(answers);
            // shuffle answers
            Collections.shuffle(this.answers);
        }

        public String getQuestion() {
            return question;
        }

        public String getRecommendedMeasureUnit() {
            return recommendedMeasureUnit;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getSource() {
            return source;
        }

        public List<Answer> getAnswers() {
            return answers;
        }
    }

    public static class Answer implements Serializable {
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

    public static final String HALF_EMPTY_QUESTIONS_AS_JSON =
            "[\n" +
                    "  {\n" +
                    "    \"question\": \"How much water inflow was there in dams in Cyprus in 2017?\",\n" +
                    "    \"recommendedMeasureUnit\": \"cubicMeter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"http://www.cyprus.gov.cy/moa/wdd/WDD.nsf/reservoir_en/reservoir_en?OpenDocument\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 42198000, \"correct\": true },\n" +
                    "      { \"answer\": 21099000, \"correct\": false },\n" +
                    "      { \"answer\": 84396000, \"correct\": false },\n" +
                    "      { \"answer\": 51285000, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water inflow was there in dams in Cyprus in 2016?\",\n" +
                    "    \"recommendedMeasureUnit\": \"cubicMeter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"http://www.cyprus.gov.cy/moa/wdd/WDD.nsf/reservoir_en/reservoir_en?OpenDocument\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 23355000, \"correct\": true },\n" +
                    "      { \"answer\": 46710000, \"correct\": false },\n" +
                    "      { \"answer\": 11675000, \"correct\": false },\n" +
                    "      { \"answer\": 675000, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water do you think is need to grow a day's food for a family of four?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"http://graphics.latimes.com/food-water-footprint/\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 25.7, \"correct\": true },\n" +
                    "      { \"answer\": 2.57, \"correct\": false },\n" +
                    "      { \"answer\": 0.257, \"correct\": false },\n" +
                    "      { \"answer\": 0.0257, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water is needed to produce an egg?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"https://www.watereducation.org/post/food-facts-how-much-water-does-it-take-produce\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 0.238, \"correct\": true },\n" +
                    "      { \"answer\": 2.384, \"correct\": false },\n" +
                    "      { \"answer\": 0.023, \"correct\": false },\n" +
                    "      { \"answer\": 0.002, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water is needed to produce a chicken?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"https://www.watereducation.org/post/food-facts-how-much-water-does-it-take-produce\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 1.25, \"correct\": true },\n" +
                    "      { \"answer\": 2.50, \"correct\": false },\n" +
                    "      { \"answer\": 0.125, \"correct\": false },\n" +
                    "      { \"answer\": 0.010, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water does it take to make a pair of jeans?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"https://www.treehugger.com/clean-water/36-eye-opening-facts-about-water.html\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 10, \"correct\": true },\n" +
                    "      { \"answer\": 1, \"correct\": false },\n" +
                    "      { \"answer\": 0.1, \"correct\": false },\n" +
                    "      { \"answer\": 0.01, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water does it take to produce 1 Kg of beef?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"https://www.treehugger.com/clean-water/36-eye-opening-facts-about-water.html\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 15, \"correct\": true },\n" +
                    "      { \"answer\": 150, \"correct\": false },\n" +
                    "      { \"answer\": 1.5, \"correct\": false },\n" +
                    "      { \"answer\": 0.015, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much inflow was there in Cyprus' dams in July 2018?\",\n" +
                    "    \"recommendedMeasureUnit\": \"cubicMeter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"http://www.cyprus.gov.cy/moa/wdd/WDD.nsf/reservoir_en/reservoir_en?OpenDocument\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 50000, \"correct\": true },\n" +
                    "      { \"answer\": 500000, \"correct\": false },\n" +
                    "      { \"answer\": 5000000, \"correct\": false },\n" +
                    "      { \"answer\": 5000, \"correct\": false }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "]";

    public static final String HALF_FULL_QUESTIONS_AS_JSON =
            "[\n" +
                    "  {\n" +
                    "    \"question\": \"What is the total capacity of Cyprus' dams?\",\n" +
                    "    \"recommendedMeasureUnit\": \"cubicMeter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"http://www.cyprus.gov.cy/moa/wdd/WDD.nsf/reservoir_en/reservoir_en?OpenDocument\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 291338000, \"correct\": true },\n" +
                    "      { \"answer\": 191338000, \"correct\": false },\n" +
                    "      { \"answer\": 391338000, \"correct\": false },\n" +
                    "      { \"answer\": 91338000, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water can a small drip from a faucet waste in a day?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"https://water.usgs.gov/edu/activity-drip.html\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 0.128, \"correct\": true },\n" +
                    "      { \"answer\": 1.285, \"correct\": false },\n" +
                    "      { \"answer\": 0.013, \"correct\": false },\n" +
                    "      { \"answer\": 0.001, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water does a five-minute shower use?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"https://www.treehugger.com/clean-water/36-eye-opening-facts-about-water.html\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 0.05, \"correct\": true },\n" +
                    "      { \"answer\": 0.005, \"correct\": false },\n" +
                    "      { \"answer\": 0.5, \"correct\": false },\n" +
                    "      { \"answer\": 0.002, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water does the World Health Organization recommend per person daily to meet the requirements of most people under most conditions?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"https://www.treehugger.com/clean-water/36-eye-opening-facts-about-water.html\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 0.0075, \"correct\": true },\n" +
                    "      { \"answer\": 0.075, \"correct\": false },\n" +
                    "      { \"answer\": 0.75, \"correct\": false },\n" +
                    "      { \"answer\": 7.5, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water does the World Health Organization recommends per person daily to cover basic hygiene and food hygiene needs?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"https://www.treehugger.com/clean-water/36-eye-opening-facts-about-water.html\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 0.002, \"correct\": true },\n" +
                    "      { \"answer\": 0.02, \"correct\": false },\n" +
                    "      { \"answer\": 0.2, \"correct\": false },\n" +
                    "      { \"answer\": 2.0, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much water on average, does a European resident use per day?\",\n" +
                    "    \"recommendedMeasureUnit\": \"liter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"https://www.treehugger.com/clean-water/36-eye-opening-facts-about-water.html\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 0.19, \"correct\": true },\n" +
                    "      { \"answer\": 0.38, \"correct\": false },\n" +
                    "      { \"answer\": 0.095, \"correct\": false },\n" +
                    "      { \"answer\": 0.043, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"The greatest inflow in Cyprus' dams in the last 10 years was in Jan 2012. How much inflow was there?\",\n" +
                    "    \"recommendedMeasureUnit\": \"cubicMeter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"http://www.cyprus.gov.cy/moa/wdd/WDD.nsf/reservoir_en/reservoir_en?OpenDocument\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 92634000, \"correct\": true },\n" +
                    "      { \"answer\": 926340000, \"correct\": false },\n" +
                    "      { \"answer\": 9263400, \"correct\": false },\n" +
                    "      { \"answer\": 926340, \"correct\": false }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "\n" +
                    "  {\n" +
                    "    \"question\": \"How much fresh water can the Dhekelia Desalination Plant produce daily?\",\n" +
                    "    \"recommendedMeasureUnit\": \"cubicMeter\",\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"source\": \"http://www.cyprus.gov.cy/moa/wdd/wdd.nsf/All/D9DD3467701044CDC2256E44003D7207?OpenDocument\",\n" +
                    "    \"answers\": [\n" +
                    "      { \"answer\": 40000, \"correct\": true },\n" +
                    "      { \"answer\": 4000, \"correct\": false },\n" +
                    "      { \"answer\": 400000, \"correct\": false },\n" +
                    "      { \"answer\": 400, \"correct\": false }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "\n" +
                    "]";
}