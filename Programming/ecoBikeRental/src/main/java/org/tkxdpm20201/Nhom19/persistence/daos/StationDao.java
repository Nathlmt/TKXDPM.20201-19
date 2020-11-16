package org.tkxdpm20201.Nhom19.persistence.daos;

import org.tkxdpm20201.Nhom19.persistence.Connect;
import org.tkxdpm20201.Nhom19.persistence.entities.Station;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDao implements Dao{

    private Connection connection;
    @Override
    public void connect() {
        this.connection = Connect.connect();
    }

    @Override
    public ResultSet execQuery(String query) throws SQLException {
        return this.connection.createStatement().executeQuery(query);
    }


    public List<Station> getAll() throws SQLException {
        String sqlSelect = "SELECT * from STATION";
        List<Station> stationlist = new ArrayList<>();
        ResultSet rs = execQuery(sqlSelect);
        while(rs.next()){
            Station station = new Station(rs.getString("NAME"),
                    rs.getString("ADDRESS"),
                    rs.getString("ACREAGE"),
                    rs.getInt("AVAILABLE_BIKE"),
                    rs.getInt("AVAILABLE_LACk"),
                    rs.getString("STATUS"),
                    rs.getDate("LAST_UPDATE")
                    );
            stationlist.add(station);

        }
        return stationlist;
    }

    public Station getOne(Integer id){
        return null;
    }
}
