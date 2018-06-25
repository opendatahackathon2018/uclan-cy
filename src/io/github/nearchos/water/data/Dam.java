package io.github.nearchos.water.data;

import java.io.Serializable;

public class Dam implements Serializable {

    public static final long ONE_MILLION = 1000000L;

    private String nameEn;
    private String nameEl;
    private int yearOfConstruction;
    private int height;
    private long capacity;
    private double lat;
    private double lng;
    private String riverNameEl;
    private String typeEl;
    private String imageUrl;
    private String wikipediaUrl;

    public Dam(String nameEn, String nameEl, int yearOfConstruction, int height, long capacity, double lat, double lng, String riverNameEl, String typeEl, String imageUrl, String wikipediaUrl) {
        this.nameEn = nameEn;
        this.nameEl = nameEl;
        this.yearOfConstruction = yearOfConstruction;
        this.height = height;
        this.capacity = capacity;
        this.lat = lat;
        this.lng = lng;
        this.riverNameEl = riverNameEl;
        this.typeEl = typeEl;
        this.imageUrl = imageUrl;
        this.wikipediaUrl = wikipediaUrl;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameEl() {
        return nameEl;
    }

    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    public int getHeight() {
        return height;
    }

    public long getCapacity() {
        return capacity;
    }

    public double getCapacityInMCM() {
        return 1D * capacity / ONE_MILLION;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getRiverNameEl() {
        return riverNameEl;
    }

    public String getTypeEl() {
        return typeEl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }
}