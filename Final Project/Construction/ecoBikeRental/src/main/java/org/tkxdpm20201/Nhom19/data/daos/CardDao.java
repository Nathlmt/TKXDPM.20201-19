package org.tkxdpm20201.Nhom19.data.daos;

import org.tkxdpm20201.Nhom19.data.entities.Card;

import java.sql.SQLException;

public interface CardDao {
    public void insertCard(Card card) throws SQLException;
}
