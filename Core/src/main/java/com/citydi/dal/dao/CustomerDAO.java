package com.citydi.dal.dao;

import com.citydi.common.base.BaseDAO;
import com.citydi.model.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: e.aghaei
 * Date: 5/20/13
 * Time: 10:02 AM
 * To change this template use File | Settings | File Templates.
 */

@Repository

public class CustomerDAO extends BaseDAO<Customer>{
   @Autowired
    public CustomerDAO (@Qualifier("sessionFactory")SessionFactory sessionFactory){
        super(Customer.class,sessionFactory);
    }

    public List<Customer> findBillsByCustomer() {
        StringBuilder query = new StringBuilder("select c from Customer c left outer  join fetch c.bills   where 1=1  ");
//        StringBuilder query = new StringBuilder("from Customer table left outer join fetch table.bills map where EXISTS (SELECT a.id from Customer table a INNER JOIN a.bills m WHERE m.id = :id and table.id=a.id)");
        return  getHibernateTemplate().find(query.toString());
    }
}
