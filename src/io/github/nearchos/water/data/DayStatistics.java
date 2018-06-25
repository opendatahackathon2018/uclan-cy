package io.github.nearchos.water.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class DayStatistics implements Serializable {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private long timestamp; // creation timestamp
    private Date date; // the date of the statistics
    private Map<String,Double> storageInMCM; // e.g. "Kouris" -> 19.245 means Kouris' storage on the date is 19.245 MCM
    private Map<String,Double> inflowInMCM; // e.g. "Kouris" -> 0.45 means Kouris' inflow on the date is 0.45 MCM

    public DayStatistics(final long timestamp, final Date date, final Map<String, Double> storageInMCM, final Map<String, Double> inflowInMCM) {
        this.timestamp = timestamp;
        this.date = date;
        this.storageInMCM = storageInMCM;
        this.inflowInMCM = inflowInMCM;
    }

    public DayStatistics(Date date, Map<String, Double> storage, Map<String, Double> inflow) {
        this(System.currentTimeMillis(), date, storage, inflow);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Date getDate() {
        return date;
    }

    public String getDateAsString() {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public Set<String> getDamNames() {
        return storageInMCM.keySet();
    }

    public Double getStorageInMCM(final String damName) {
        return storageInMCM.get(damName);
    }

    public Double getInflowInMCM(final String damName) {
        return inflowInMCM.get(damName);
    }
}