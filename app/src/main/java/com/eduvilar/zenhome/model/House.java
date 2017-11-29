package com.eduvilar.zenhome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by efraespada on 29/11/2017.
 */

public class House {

    private List<HouseModel> spaces;

    public House(List<HouseModel> spaces) {
        this.spaces = spaces;
    }

    public List<Garage> getGarages() {
        List<Garage> alarms = new ArrayList<>();
        for (HouseModel space : spaces) {
            if (space instanceof Garage) {
                alarms.add((Garage) space);
            }
        }
        return alarms;
    }

    public List<Living> getLivings() {
        List<Living> livings = new ArrayList<>();
        for (HouseModel space : spaces) {
            if (space instanceof Living) {
                livings.add((Living) space);
            }
        }
        return livings;
    }

    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        for (HouseModel space : spaces) {
            if (space instanceof Room) {
                rooms.add((Room) space);
            }
        }
        return rooms;
    }

    public List<Kitchen> getKitchens() {
        List<Kitchen> kitchens = new ArrayList<>();
        for (HouseModel space : spaces) {
            if (space instanceof Kitchen) {
                kitchens.add((Kitchen) space);
            }
        }
        return kitchens;
    }

}
