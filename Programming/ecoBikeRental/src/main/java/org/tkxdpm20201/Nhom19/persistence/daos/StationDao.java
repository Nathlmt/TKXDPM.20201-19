package org.tkxdpm20201.Nhom19.persistence.daos;

import org.tkxdpm20201.Nhom19.persistence.entities.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDao extends Dao{
    public List<Station> getAll() throws SQLException {
        String sqlSelect = "SELECT * from STATION";
        List<Station> stationlist = new ArrayList<>();
        ResultSet rs = execQuery(sqlSelect);
        while(rs.next()){
            Station station = new Station(rs.getString("STATION_NAME"),
                    rs.getString("ADDRESS"),
                    rs.getString("ACREAGE"),
                    rs.getInt("AVAILABLE_BIKE"),
                    rs.getInt("AVAILABLE_RACk"),
                    rs.getString("STATUS"),
                    rs.getDate("LASTEST_UPDATE")
                    );
            stationlist.add(station);
        }
        return stationlist;
    }

    public Station getOne(Integer id){
        return null;
    }
}
