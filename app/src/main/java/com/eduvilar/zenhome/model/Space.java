package com.eduvilar.zenhome.model;

import com.eduvilar.zenhome.model.appliances.Alarm;
import com.eduvilar.zenhome.model.appliances.Appliance;
import com.eduvilar.zenhome.model.appliances.Hob;
import com.eduvilar.zenhome.model.appliances.Light;
import com.eduvilar.zenhome.model.appliances.Microwave;
import com.eduvilar.zenhome.model.appliances.Oven;
import com.eduvilar.zenhome.model.appliances.PS4;
import com.eduvilar.zenhome.model.appliances.TV;
import com.eduvilar.zenhome.model.appliances.XBOX;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by efraespada on 29/11/2017.
 */

public class Space {

    private List<Appliance> appliances;

    public Space() {
        // nothing to do here
    }

    public Space(List<Appliance> appliances) {
        this.appliances = appliances;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(List<Appliance> appliances) {
        this.appliances = appliances;
    }

    public List<Alarm> getAlarms() {
        List<Alarm> alarms = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance instanceof Alarm) {
                alarms.add((Alarm) appliance);
            }
        }
        return alarms;
    }

    public List<Hob> getHobs() {
        List<Hob> hobs = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance instanceof Hob) {
                hobs.add((Hob) appliance);
            }
        }
        return hobs;
    }

    public List<Light> getLights() {
        List<Light> lights = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance instanceof Light) {
                lights.add((Light) appliance);
            }
        }
        return lights;
    }

    public List<Oven> getOvens() {
        List<Oven> ovens = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance instanceof Oven) {
                ovens.add((Oven) appliance);
            }
        }
        return ovens;
    }

    public List<TV> getTV() {
        List<TV> tvs = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance instanceof TV) {
                tvs.add((TV) appliance);
            }
        }
        return tvs;
    }

    public List<Microwave> getMicrowaves() {
        List<Microwave> microwaves = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance instanceof Hob) {
                microwaves.add((Microwave) appliance);
            }
        }
        return microwaves;
    }

    public List<PS4> getPS4s() {
        List<PS4> ps4s = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance instanceof PS4) {
                ps4s.add((PS4) appliance);
            }
        }
        return ps4s;
    }

    public List<XBOX> getXBOXs() {
        List<XBOX> xboxs = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance instanceof XBOX) {
                xboxs.add((XBOX) appliance);
            }
        }
        return xboxs;
    }


}
