package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.BaseDao;
import org.tkxdpm20201.Nhom19.data.daos.CardDao;
import org.tkxdpm20201.Nhom19.data.daos.DBHelper;
import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.entities.bike.Bike;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CardDaoImp implements CardDao {


    public void insertCard(Card card) throws SQLException {
    //Insert Card To Database
    }

    @Override
    public List<Card> getAll() throws SQLException {
        return null;
    }

    @Override
    public Card getById(int id) throws SQLException{
        String sqlQuery = "SELECT * FROM CARDS WHERE id = ?";
        PreparedStatement preparedStatement =  DBHelper.getConnection().prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            Card card = new Card(
                    rs.getBigDecimal("balance"),
                    rs.getString("card_code"),
                    rs.getString("owner"),
                    rs.getString("security_code"),
                    rs.getString("expiration_date")
            );
            return card;
        }
        return null;
    }

    @Override
    public Card create(Card object) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Card object) throws SQLException {
        return false;
    }


}
