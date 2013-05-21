package com.citydi.dal.dao;

import com.citydi.common.base.BaseDAO;
import com.citydi.model.Bill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by Emad Aghayi
 * at 2013/05/07 - 12:12
 */
@Repository
public class BillDAO extends BaseDAO<Bill> {

    @Autowired
    public BillDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Bill.class, sessionFactory);
    }



}