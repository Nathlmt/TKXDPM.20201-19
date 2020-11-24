package org.tkxdpm20201.Nhom19.persistence.daos.implement;

import org.tkxdpm20201.Nhom19.persistence.daos.StationDao;
import org.tkxdpm20201.Nhom19.persistence.entities.Station;

import java.sql.SQLException;
import java.util.List;

public class StationDaoImp extends BaseDaoImp<Station> implements StationDao{


    @Override
    public List<Station> getAll() throws SQLException {
        System.out.println("i OK getAll");
//        String sqlSelect = "SELECT * from STATION";
//        List<Station> stationList = new ArrayList<>();
//        ResultSet rs = execQuery(sqlSelect);
//        while(rs.next()){
//            Station station = new Station(rs.getInt("ID"),
//                    rs.getString("STATION_NAME"),
//                    rs.getString("ADDRESS"),
//                    rs.getString("ACREAGE"),
//                    rs.getInt("AVAILABLE_BIKE"),
//                    rs.getInt("AVAILABLE_RACK"),
//                    rs.getString("STATUS"),
//                    rs.getDate("LASTEST_UPDATE")
//                    );
//            stationList.add(station);
//        }
        return null;
    }


}
