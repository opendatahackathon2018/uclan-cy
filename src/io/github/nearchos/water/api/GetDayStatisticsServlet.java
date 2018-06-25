package io.github.nearchos.water.api;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nearchos.water.data.DatastoreHelper;
import io.github.nearchos.water.data.DayStatistics;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static io.github.nearchos.water.data.DayStatistics.SIMPLE_DATE_FORMAT;

public class GetDayStatisticsServlet extends HttpServlet {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    private final MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        try {
            // unless a "?date=dd-MM-yy" parameter is specified, use current date
            final String dateS = request.getParameter("date");
            final Date date = dateS == null ? new Date() : SIMPLE_DATE_FORMAT.parse(dateS);

            if(date.after(new Date())) { // if the requested date uis in the future, return error message
                final Message errorMessage = Message.getErrorMessage("Cannot query day-statistics for future date " + SIMPLE_DATE_FORMAT.format(date) + ".");
                response.getWriter().println(gson.toJson(errorMessage));
            } else {
                final String memcacheKey = getMemcacheKey(date);
                if(memcacheService.contains(memcacheKey)) {
                    final String dayStatisticsJson = (String) memcacheService.get(memcacheKey);
                    response.getWriter().println(dayStatisticsJson); // write the response
                } else {
                    final DayStatistics dayStatistics = DatastoreHelper.getDayStatistics(SIMPLE_DATE_FORMAT.format(date)); // get from datastore
                    if(dayStatistics == null) {
                        final Message errorMessage = Message.getErrorMessage("Could not find day-statistics for " + SIMPLE_DATE_FORMAT.format(date) + " or earlier. Make sure to specify the parameter date, e.g. 'date=2018-06-17'.");
                        response.getWriter().println(gson.toJson(errorMessage));
                    } else {
                        // return the result as JSON
                        response.getWriter().println(gson.toJson(dayStatistics));
                    }
                }
            }
        } catch (ParseException pe) {
            final Message errorMessage = Message.getErrorMessage(pe.getMessage());
            response.getWriter().println(gson.toJson(errorMessage));
        }
    }

    public static String getMemcacheKey(final Date date) {
        return "dayStatistics@" + SIMPLE_DATE_FORMAT.format(date);
    }
}