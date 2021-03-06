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

package io.github.nearchos.water.sync;

import com.google.gson.Gson;
import io.github.nearchos.water.data.DatastoreHelper;
import io.github.nearchos.water.data.DayStatistics;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class GrabServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger("cyprus-water");

    private static final Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // fetching and parsing root web page
        Document doc = Jsoup.connect(WDD_ROOT).get();
        // log(doc.title());
        final Elements elements = doc.getElementsByTag("area");
        final Element element = elements.first();
        final String link = element.attr("href");
        // inferring link to date's data
        final String absoluteLink = "http://www.cyprus.gov.cy/moa/wdd/WDD.nsf" + link.substring(2);
        log("fetching XLS from absoluteLink: "  + absoluteLink);

        // fetching and processing XLS
        final DayStatistics dayStatistics = getDayStatistics(absoluteLink);
        final String date = dayStatistics.getDateAsString();

        // check if given object is already in datastore - if non-null, then set date already exists in datastore
        final PrintWriter printWriter = response.getWriter();
        final DayStatistics latestDayStatistics = DatastoreHelper.getDayStatistics(date);
        if(latestDayStatistics == null || !latestDayStatistics.getDateAsString().equals(dayStatistics.getDateAsString())) {
            DatastoreHelper.addDayStatistics(dayStatistics);
            printWriter.println("Added dayStatistics for: " + date);
            log("Added dayStatistics for: " + date + " --> " + dayStatistics);
        } else {
            printWriter.println("Skipped as dayStatistics already in datastore for: " + date);
            log("Skipped as dayStatistics already in datastore for: " + date);
        }
    }

    private static final String WDD_ROOT = "http://www.cyprus.gov.cy/moa/wdd/WDD.nsf/reservoir_en/reservoir_en?OpenDocument";

    private DayStatistics getDayStatistics(final String absoluteLink) throws IOException {
        final org.apache.poi.ss.usermodel.Workbook workbook = Util.doRequestXls(absoluteLink);
        return Util.getDayStatistics(workbook);
    }
}