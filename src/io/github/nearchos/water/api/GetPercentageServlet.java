/*
 * Copyright (c) 2018.
 *
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR
 *  "LICENSE"). THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. ANY USE OF THE WORK OTHER
 *   THAN AS AUTHORIZED UNDER THIS LICENSE OR COPYRIGHT LAW IS PROHIBITED.
 *
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND AGREE TO BE BOUND BY THE TERMS OF THIS
 *  LICENSE. TO THE EXTENT THIS LICENSE MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS
 *   CONTAINED HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 *
 * The complete license is available at https://creativecommons.org/licenses/by/3.0/legalcode
 */

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
