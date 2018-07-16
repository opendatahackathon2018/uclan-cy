package io.github.nearchos.water.data;

import java.util.SortedMap;
import java.util.Vector;

public class Timeseries {

    private final Vector<Dam> dams;
    private final SortedMap<String,DamsPercentage> percentages;

    public Timeseries(final Vector<Dam> dams, final SortedMap<String, DamsPercentage> percentages) {
        this.dams = dams;
        this.percentages = percentages;
    }

    public Vector<Dam> getDams() {
        return dams;
    }

    public SortedMap<String, DamsPercentage> getPercentages() {
        return percentages;
    }
}