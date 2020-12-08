package org.tkxdpm20201.Nhom19.data.daos.implement;

import org.tkxdpm20201.Nhom19.data.daos.TransactionDao;
import org.tkxdpm20201.Nhom19.data.entities.Transaction;

public class TransactionDaoImp extends BaseDaoImp<Transaction> implements TransactionDao {

    public TransactionDaoImp() {
        super(Transaction.class);
    }

    
}
