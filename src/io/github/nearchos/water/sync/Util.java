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

import io.github.nearchos.water.data.Data;
import io.github.nearchos.water.data.DayStatistics;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class Util {

    private static final Logger log = Logger.getLogger("cyprus-water");

    static Workbook doRequestXls(final String url) throws IOException {
        final URL requestUrl = new URL(url);
        final HttpURLConnection httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/vnd.ms-excel");

        int responseCode = httpURLConnection.getResponseCode();
        if(responseCode != 200) {
            log.severe("RequestServlet @ '" + url + "' produced response code: " + responseCode);
            throw new IOException("HTTP (XML) response code: " + responseCode);
        }

        return getWorkbook(httpURLConnection.getInputStream());
    }

    private static DayStatistics getDayStatistics(final InputStream inputStream) throws IOException {
        final Workbook workbook = getWorkbook(inputStream);
        return getDayStatistics(workbook);
    }

    static Workbook getWorkbook(final InputStream inputStream) throws IOException {
        return new XSSFWorkbook(inputStream);
    }

    static DayStatistics getDayStatistics(final Workbook workbook) throws IOException {
        Date date;
        Map<String,Double> mapStorage = new HashMap<>();
        Map<String,Double> mapInflow = new HashMap<>();

        final Vector<String> damNamesEn = new Vector<>(Arrays.asList(Data.DAM_NAMES_EN));

        try {
            final Sheet sheet = workbook.getSheetAt(0);
            date = sheet.getRow(9).getCell(11).getDateCellValue();
            for(int j = 16; j < 41; j++) {
                String damName = sheet.getRow(j).getCell(1).getStringCellValue().trim(); {
                    double damStorage = sheet.getRow(j).getCell(7).getNumericCellValue();
                    if(!damName.isEmpty() && damNamesEn.contains(damName)) {
                        mapStorage.put(damName, damStorage);
                    }
                }
                {
                    double damInflow = sheet.getRow(j).getCell(5).getNumericCellValue();
                    if(!damName.isEmpty() && damNamesEn.contains(damName)) {
                        mapInflow.put(damName, damInflow);
                    }
                }
            }
        } catch (RuntimeException re) {
            throw new IOException(re);
        }

        return new DayStatistics(date, mapStorage, mapInflow);
    }
}