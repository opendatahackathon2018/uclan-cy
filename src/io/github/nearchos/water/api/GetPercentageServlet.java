package io.github.nearchos.water.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nearchos.water.data.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static io.github.nearchos.water.data.DayStatistics.SIMPLE_DATE_FORMAT;

public class GetPercentageServlet extends HttpServlet {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        final String dateParameter = request.getParameter("date");
        try {
            final Date date;
            if(dateParameter == null || dateParameter.trim().isEmpty()) {
                date = new Date();
            } else {
                date = SIMPLE_DATE_FORMAT.parse(dateParameter);
            }

            if(date.after(new Date())) {
                final Message errorMessage = Message.getErrorMessage("Cannot query percentages for future date " + SIMPLE_DATE_FORMAT.format(date) + ".");
                response.getWriter().println(gson.toJson(errorMessage));
            }

            final DayStatistics latestDayStatistics = DatastoreHelper.getDayStatistics(SIMPLE_DATE_FORMAT.format(date));

            if(latestDayStatistics == null) {
                final Message errorMessage = Message.getErrorMessage("Cannot find percentage statistics for date " + SIMPLE_DATE_FORMAT.format(date) + ".");
                response.getWriter().println(gson.toJson(errorMessage));
            } else {
                final DamsPercentage damsPercentage = new DamsPercentage(latestDayStatistics);
                response.getWriter().println(gson.toJson(damsPercentage));
            }

        } catch (ParseException pe) {
            final Message errorMessage = Message.getErrorMessage("Cannot parse date parameter " + dateParameter + ".");
            response.getWriter().println(gson.toJson(errorMessage));
        }
    }
}
