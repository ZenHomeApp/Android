package com.eduvilar.zenhome.model.appliances;

import com.eduvilar.zenhome.enums.Status;
import com.eduvilar.zenhome.model.Intensity;

/**
 * Created by efraespada on 29/11/2017.
 */

public class Appliance {

    protected Intensity intensity;
    protected Status status;

    public Appliance(Status status, Intensity intensity) {
        this.status = status;
        this.intensity = intensity;
    }

    public Intensity getIntensity() {
        return intensity;
    }

    public Status getStatus() {
        return status;
    }

    public void changeStatus() {
        this.status = Status.ON == status ? Status.OFF : Status.ON;
    }
}
