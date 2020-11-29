package org.tkxdpm20201.Nhom19.persistence.daos.implement;

import org.tkxdpm20201.Nhom19.persistence.daos.StationDao;
import org.tkxdpm20201.Nhom19.persistence.entities.Station;

public class StationDaoImp extends BaseDaoImp<Station> implements StationDao{

    public StationDaoImp(){
        super(Station.class);
    }
//    @Override
//    public List<Station> getAll() throws SQLException {
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
//        return stationList;
//    }


}
