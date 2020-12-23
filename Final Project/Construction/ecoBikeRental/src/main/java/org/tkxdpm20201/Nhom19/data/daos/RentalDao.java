package org.tkxdpm20201.Nhom19.data.daos;

import org.tkxdpm20201.Nhom19.data.entities.Rental;

public interface RentalDao extends BaseDao<Rental>{

    Rental getByTransactionId(int transactionId);
}
