package org.tkxdpm20201.Nhom19.data.daos;

import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.entities.Rental;

import java.sql.SQLException;

public interface CardDao extends BaseDao<Card>{
    void insertCard(Card card) throws SQLException;
}
