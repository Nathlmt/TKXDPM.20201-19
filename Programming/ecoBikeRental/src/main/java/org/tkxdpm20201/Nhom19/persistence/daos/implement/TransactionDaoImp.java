package org.tkxdpm20201.Nhom19.persistence.daos.implement;

import org.tkxdpm20201.Nhom19.persistence.daos.TransactionDao;
import org.tkxdpm20201.Nhom19.persistence.entities.Transaction;

public class TransactionDaoImp extends BaseDaoImp<Transaction> implements TransactionDao {

    public TransactionDaoImp() {
        super(Transaction.class);
    }
}
