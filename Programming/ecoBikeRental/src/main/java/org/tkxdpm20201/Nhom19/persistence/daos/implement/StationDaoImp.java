package org.tkxdpm20201.Nhom19.persistence.daos.implement;

import org.tkxdpm20201.Nhom19.persistence.daos.DBHelper;
import org.tkxdpm20201.Nhom19.persistence.daos.StationDao;
import org.tkxdpm20201.Nhom19.persistence.entities.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDaoImp extends BaseDaoImp<Station> implements StationDao{

    public StationDaoImp(){
        super(Station.class);
    }
    @Override
    public List<Station> getAll() throws SQLException {
        String sqlSelect = "SELECT * from STATIONS";
        List<Station> stationList = new ArrayList<>();
        ResultSet rs = DBHelper.executeQuery(sqlSelect);
        while(rs.next()){
            Station station = new Station(rs.getInt("ID"),
                    rs.getString("STATION_NAME"),
                    rs.getString("ADDRESS"),
                    rs.getString("ACREAGE"),
                    rs.getInt("AVAILABLE_BIKE"),
                    rs.getInt("AVAILABLE_RACK"),
                    rs.getString("STATUS"),
                    rs.getDate("LASTEST_UPDATE")
                    );
            stationList.add(station);
        }
        return stationList;
    }


}
