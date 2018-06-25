package io.github.nearchos.water.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.github.nearchos.water.data.Dam.ONE_MILLION;
import static io.github.nearchos.water.data.DayStatistics.SIMPLE_DATE_FORMAT;

public class DamsPercentage implements Serializable {

    private transient static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    private final Map<String, Double> damNamesToPercentage = new HashMap<>();
    private Date date;
    private final double totalPercentage;
    private final double totalCapacityInMCM;

    public DamsPercentage(final DayStatistics dayStatistics) {

        assert dayStatistics != null;
        this.date = dayStatistics.getDate();
        final Collection<String> allDamNames = dayStatistics.getDamNames();
        double currentStorage = 0d;
        for(final String damName : allDamNames) {
            currentStorage += dayStatistics.getStorageInMCM(damName);
        }

        long tempTotalCapacityInCM = 0L;
        final Dam [] allDams = gson.fromJson(Data.DAMS_JSON, Dam [].class);
        for(final Dam dam : allDams) {
            tempTotalCapacityInCM += dam.getCapacity();
            damNamesToPercentage.put(dam.getNameEn(), dayStatistics.getStorageInMCM(dam.getNameEn()) / dam.getCapacityInMCM());
        }

        this.totalCapacityInMCM = 1D * tempTotalCapacityInCM / ONE_MILLION;
        totalPercentage = currentStorage / totalCapacityInMCM;
    }

    public Date getDate() {
        return date;
    }

    public String getDateAsString() {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public double getPercentage(final String damName) {
        return damNamesToPercentage.get(damName);
    }

    public double getTotalCapacityInMCM() {
        return totalCapacityInMCM;
    }

    public double getTotalPercentage() {
        return totalPercentage;
    }
}