package io.github.nearchos.water.api;

import io.github.nearchos.water.data.DatastoreHelper;
import io.github.nearchos.water.data.Score;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class PostScoreServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        final String nickname = request.getParameter("nickname");
        final String scoreS = request.getParameter("score");

        log("nickname: " + nickname + ", scoreS: " + scoreS);
        try {
            final int score = Integer.parseInt(scoreS);

            DatastoreHelper.addScore(new Score(nickname, score, new Date()));
            response.getWriter().println("Added '" + nickname + "' -> 'score' pair");
        } catch (NumberFormatException nfe) {
            response.getWriter().println("Invalid or missing 'score' parameter");
        }
    }
}
