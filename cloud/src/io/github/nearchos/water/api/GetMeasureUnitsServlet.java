package io.github.nearchos.water.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nearchos.water.data.MeasureUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetMeasureUnitsServlet extends HttpServlet {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        final String reply = gson.toJson(MeasureUnit.DEFAULT_MEASURE_UNITS);
        response.getWriter().println(reply);
    }
}
