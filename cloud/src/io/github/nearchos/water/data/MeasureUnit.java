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

package io.github.nearchos.water.data;

import java.io.Serializable;

public class MeasureUnit implements Serializable {

    private String nameEl;
    private String nameEn;
    private String imageUrl;
    private double ratio; // how many cubic meters in one unit of this

    public MeasureUnit(String nameEl, String nameEn, String imageUrl, double ratio) {
        this.nameEl = nameEl;
        this.nameEn = nameEn;
        this.imageUrl = imageUrl;
        this.ratio = ratio;
    }

    public String getNameEl() {
        return nameEl;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getRatio() {
        return ratio;
    }

    public static final MeasureUnit [] DEFAULT_MEASURE_UNITS = {
            new MeasureUnit("Σταγόνα", "Drop", null, 1d),
            new MeasureUnit("Κουταλάκι Τσαγιού", "Tea Spoon", null, 1d),
            new MeasureUnit("Κουταλάκι Σούπας", "Soup Spoon", null, 1d),
            new MeasureUnit("Φλυτζάνι", "Cup", null, 1d),
            new MeasureUnit("Λίτρο", "Liter", null, 1d),
            new MeasureUnit("Κυβικό Μέτρο", "Cubic Meter", null, 1d),
//            new MeasureUnit("", "", 1d),
//            new MeasureUnit("", "", 1d),
//            new MeasureUnit("", "", 1d),
//            new MeasureUnit("", "", 1d),
//            new MeasureUnit("", "", 1d),

    };
}