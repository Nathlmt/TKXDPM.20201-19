package org.tkxdpm20201.Nhom19.persistence.daos;

import org.tkxdpm20201.Nhom19.persistence.entities.Rental;

public interface RentalDao extends BaseDao<Rental>{

    Rental getByTransactionId(int transactionId);
}
