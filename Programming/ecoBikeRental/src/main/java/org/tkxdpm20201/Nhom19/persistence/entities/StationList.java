package org.tkxdpm20201.Nhom19.persistence.entities;

import java.util.List;

public class StationList {
    private List<Station> stationList;

    public StationList(){}

    public StationList(List<Station> stationList) {
        this.stationList = stationList;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public void setStationList(List<Station> stationList) {
        this.stationList = stationList;
    }
}
