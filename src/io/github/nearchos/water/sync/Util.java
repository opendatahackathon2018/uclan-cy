package io.github.nearchos.water.sync;

import io.github.nearchos.water.data.Data;
import io.github.nearchos.water.data.DayStatistics;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {

    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");

    private static DayStatistics getDayStatistics(final InputStream inputStream) throws IOException {
        final Workbook workbook = getWorkbook(inputStream);
        return getDayStatistics(workbook);
    }

    static Workbook getWorkbook(final InputStream inputStream) throws IOException {

        final WorkbookSettings workbookSettings = new WorkbookSettings();
        try {
            return Workbook.getWorkbook(inputStream, workbookSettings);
        } catch (BiffException biffe) {
            throw new IOException(biffe);
        }
    }

    static DayStatistics getDayStatistics(final Workbook workbook) throws IOException {

        Date date;
        Map<String,Double> mapStorage = new HashMap<>();
        Map<String,Double> mapInflow = new HashMap<>();

        final Vector<String> damNamesEn = new Vector<>(Arrays.asList(Data.DAM_NAMES_EN));

        try {
            final Sheet sheet = workbook.getSheet(0);
            final String dateS = sheet.getCell(11, 9).getContents();
            date = DATE_FORMAT.parse(dateS);
            for(int j = 0; j < sheet.getRows(); j++) {
                String damName = sheet.getCell(1, j).getContents().trim();
                {
                    String damStorageS = sheet.getCell(7, j).getContents();
                    if(!damName.isEmpty() && damNamesEn.contains(damName)) {
                        final double damStorage = Double.parseDouble(damStorageS);
                        mapStorage.put(damName, damStorage);
                    }
                }
                {
                    String damInflowS = sheet.getCell(5, j).getContents();
                    if(!damName.isEmpty() && damNamesEn.contains(damName)) {
                        final double damInflow = Double.parseDouble(damInflowS);
                        mapInflow.put(damName, damInflow);
                    }
                }
            }
        } catch (ParseException pe) {
            throw new IOException(pe);
        }

        return new DayStatistics(date, mapStorage, mapInflow);
    }
}