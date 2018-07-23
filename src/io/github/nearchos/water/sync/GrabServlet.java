package io.github.nearchos.water.sync;

import com.google.gson.Gson;
import io.github.nearchos.water.data.DatastoreHelper;
import io.github.nearchos.water.data.DayStatistics;
import jxl.Workbook;
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

import static io.github.nearchos.water.sync.Util.doRequestXls;

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
        final Workbook workbook = doRequestXls(absoluteLink);
        final DayStatistics dayStatistics = Util.getDayStatistics(workbook);
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
}