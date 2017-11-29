package com.eduvilar.zenhome.model.appliances;

import com.eduvilar.zenhome.enums.Status;
import com.eduvilar.zenhome.model.Intensity;

/**
 * Created by efraespada on 29/11/2017.
 */

public class Microwave extends Appliance {

    public Microwave(Status status, Intensity intensity) {
        super(status, intensity);
    }
}