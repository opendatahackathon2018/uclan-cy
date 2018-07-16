package io.github.nearchos.water.api;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nearchos.water.data.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class GetTimeseriesServlet extends HttpServlet {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    private final MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // first check if in memcache
        final String memcacheKey = "timeseries-" + DayStatistics.SIMPLE_DATE_FORMAT.format(new Date());
        if (memcacheService.contains(memcacheKey)) {
            final String reply = (String) memcacheService.get(memcacheKey);
            // return the cached result as JSON
            response.getWriter().println(reply);
        } else {
            // produce a new result, then cache it, and finally return it
            final Vector<Dam> dams = DatastoreHelper.getDams();
            final Vector<DayStatistics> dayStatisticsVector = DatastoreHelper.getAllDayStatistics();
            final SortedMap<String, DamsPercentage> percentages = new TreeMap<>();
            for (final DayStatistics dayStatistics : dayStatisticsVector) {
                percentages.put(dayStatistics.getDateAsString(), new DamsPercentage(dayStatistics));
            }
            final Timeseries timeseries = new Timeseries(dams, percentages);

            // set reply as JSON-formatted timeseries
            final String reply = gson.toJson(timeseries);

            // cache the result
            memcacheService.put(memcacheKey, reply);

            // return the result
            response.getWriter().println(reply);
        }
    }
}