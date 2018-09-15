package io.github.nearchos.water.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nearchos.water.data.Quiz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class GetQuizServlet extends HttpServlet {

    public static final int LIMIT = 5; // max num of questions in quiz

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        final Quiz.Question [] allQuestions = gson.fromJson(Quiz.ALL_QUESTIONS_AS_JSON, Quiz.Question[].class);
        final Vector<Quiz.Question> allQuestionsList = new Vector<>(Arrays.asList(allQuestions));

        Collections.shuffle(allQuestionsList);
        // if needed, limit size to LIMIT
        while(allQuestionsList.size() > LIMIT) {
            allQuestionsList.remove(0);
        }

        final String reply = gson.toJson(new Quiz(allQuestionsList));
        response.getWriter().println(reply);
    }
}