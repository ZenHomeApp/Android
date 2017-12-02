package com.eduvilar.zenhome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by efraespada on 29/11/2017.
 */

public class House {

    private String name;
    private List<Space> spaces;

    public House(String name) {
        this.name = name;
        this.spaces = new ArrayList<>();
    }

    public House(String name, List<Space> spaces) {
        this.name = name;
        this.spaces = spaces;
    }

    public List<Garage> getGarages() {
        List<Garage> alarms = new ArrayList<>();
        for (Space space : spaces) {
            if (space instanceof Garage) {
                alarms.add((Garage) space);
            }
        }
        return alarms;
    }

    public List<Living> getLivings() {
        List<Living> livings = new ArrayList<>();
        for (Space space : spaces) {
            if (space instanceof Living) {
                livings.add((Living) space);
            }
        }
        return livings;
    }

    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        for (Space space : spaces) {
            if (space instanceof Room) {
                rooms.add((Room) space);
            }
        }
        return rooms;
    }

    public List<Kitchen> getKitchens() {
        List<Kitchen> kitchens = new ArrayList<>();
        for (Space space : spaces) {
            if (space instanceof Kitchen) {
                kitchens.add((Kitchen) space);
            }
        }
        return kitchens;
    }

    public String getName() {
        return name;
    }
}
