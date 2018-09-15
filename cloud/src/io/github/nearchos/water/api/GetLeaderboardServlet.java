package io.github.nearchos.water.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nearchos.water.data.DatastoreHelper;
import io.github.nearchos.water.data.Score;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class GetLeaderboardServlet extends HttpServlet {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        final Vector<Score> scores = DatastoreHelper.getAllScores(true);
        final String reply = gson.toJson(scores);
        response.getWriter().println(reply);
    }
}
