package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.daos.StationDao;
import org.tkxdpm20201.Nhom19.data.entities.station.Station;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                    rs.getDate("LATEST_UPDATE")
                    );
            stationList.add(station);
        }
        return stationList;
    }


    @Override
    public boolean updateStationWhenRentBike(int stationId) throws SQLException {
        String sqlUpdate = "UPDATE STATIONS " +
                "SET available_bike = available_bike - 1,"+
                " available_rack = available_rack + 1," +
                " latest_update = ?" +
                "WHERE id = ?";
        PreparedStatement ps = DBHelper.getConnection().prepareStatement(sqlUpdate);
        ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        ps.setInt(2, stationId);
        return ps.execute();
    }
}
