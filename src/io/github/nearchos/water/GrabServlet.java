package io.github.nearchos.water;

import com.google.common.io.ByteStreams;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

public class GrabServlet extends HttpServlet {

    public static final Logger log = Logger.getLogger("cyprus-water");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        final ServletOutputStream out = response.getOutputStream();

        Document doc = Jsoup.connect(WDD_ROOT).get();
        log(doc.title());

        String stringOfInterest = "<h1>" + doc.title() + "</h1><br>";
        final Element element = doc.getElementById("HotspotRectangle171");
        final String link = element.attr("href");
        final String absoluteLink = "http://www.cyprus.gov.cy/moa/wdd/WDD.nsf" + link.substring(2);
        stringOfInterest += "<a href=" + absoluteLink + ">" + absoluteLink + "</a>\n";

//        final byte [] xlsData = doRequestBinary(absoluteLink);
        final Workbook workbook = doRequestXls(absoluteLink);

        stringOfInterest += "<a href=" + absoluteLink + ">" + absoluteLink + "</a>\n";
//        stringOfInterest += "<p>xls size: " + (xlsData == null ? "null" : xlsData.length) + "</p>";

        stringOfInterest += "<p>xls names: " + (workbook == null ? "null" : workbook.getAllNames()) + "</p>";

        out.println(stringOfInterest);
    }

    public static final String WDD_ROOT = "http://www.cyprus.gov.cy/moa/wdd/WDD.nsf/reservoir_en/reservoir_en?OpenDocument";

    private String doRequestHtml(final String url) throws IOException {
        final URL requestUrl = new URL(url);
        final HttpURLConnection httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
        httpURLConnection.setRequestProperty("Content-Type", "text/html;charset=UTF-8");

        int responseCode = httpURLConnection.getResponseCode();
        if(responseCode != 200) {
            log.severe("RequestServlet @ '" + url + "' produced response code: " + responseCode);
            throw new IOException("HTTP (HTML) response code: " + responseCode);
        }

        final InputStream inputStream = httpURLConnection.getInputStream();

        return convertStreamToString(inputStream);
    }

    private static String convertStreamToString(InputStream inputStream) {
        final Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() + "\n" : "";
    }

    private byte [] doRequestBinary(final String url) throws IOException {
        final URL requestUrl = new URL(url);
        final HttpURLConnection httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
        httpURLConnection.setRequestProperty("Content-Type", "application/vnd.ms-excel");

        int responseCode = httpURLConnection.getResponseCode();
        if(responseCode != 200) {
            log.severe("RequestServlet @ '" + url + "' produced response code: " + responseCode);
            throw new IOException("HTTP (XML) response code: " + responseCode);
        }

        final InputStream stdInputStream = httpURLConnection.getInputStream();

        return ByteStreams.toByteArray(stdInputStream);
    }

    private Workbook doRequestXls(final String url) throws IOException {
        final URL requestUrl = new URL(url);
        final HttpURLConnection httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
        httpURLConnection.setRequestProperty("Content-Type", "application/vnd.ms-excel");

        int responseCode = httpURLConnection.getResponseCode();
        if(responseCode != 200) {
            log.severe("RequestServlet @ '" + url + "' produced response code: " + responseCode);
            throw new IOException("HTTP (XML) response code: " + responseCode);
        }

        try {
            return WorkbookFactory.create(httpURLConnection.getInputStream());
        } catch (InvalidFormatException ife) {
            throw new IOException(ife);
        }
    }
}