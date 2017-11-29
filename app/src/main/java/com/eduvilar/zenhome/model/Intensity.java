package com.eduvilar.zenhome.model;

/**
 * Created by efraespada on 29/11/2017.
 */

public class Intensity {

    private String currentStringI;
    private int currentIntI;
    private int[] numLevels;
    private String[] sLevels;

    public Intensity(int... levels) {
        numLevels = new int[levels.length];
        for (int i = 0; i < levels.length; i++) {
            numLevels[i] = levels[i];
        }
    }

    public Intensity(String... levels) {
        sLevels = new String[levels.length];
        for (int i = 0; i < levels.length; i++) {
            sLevels[i] = levels[i];
        }
    }
    public void setValue(String value) {
        boolean found = false;
        for (String val : sLevels) {
            if (val.equals(value)) {
                found = true;
                break;
            }
        }
        if (found) {
            currentStringI = value;
        }
    }

    public void setValue(int value) {
        boolean found = false;
        for (int val : numLevels) {
            if (val == value) {
                found = true;
                break;
            }
        }
        if (found) {
            currentIntI = value;
        }
    }

    public int getCurrentIValue() {
        return currentIntI;
    }

    public String getCurrentSValue() {
        return currentStringI;
    }


}
