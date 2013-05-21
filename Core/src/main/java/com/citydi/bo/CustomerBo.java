package com.citydi.bo;

import com.citydi.common.base.BaseBean;
import com.citydi.dal.dao.BillDAO;
import com.citydi.dal.dao.CustomerDAO;
import com.citydi.model.Bill;
import com.citydi.model.Customer;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: e.aghaei
 * Date: 5/20/13
 * Time: 10:08 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("prototype")

public class CustomerBo extends BaseBean {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private BillDAO billDAO;
    @PostConstruct
    public void init() {

    }


    public void fill(){
//        List<Customer> list =customerDAO.findAll();
//        List<Customer> list =customerDAO.findBillsByCustomer();
        List<Customer> list =customerDAO.findBillsByCustomer();
//        List<Customer> list = (List<Customer>) customerDAO.findBillsByCustomer();
        for(int i=0; i<list.size();i++){
            Bill bill = new Bill();
            bill.setBillAmount(989898);
            bill.setBillType("set");
            bill.setCustomer(list.get(i));
            list.get(i).getBills().add(bill);
            customerDAO.save(list.get(i));
        }
        List list2= customerDAO.findById(1L).getBills();
    }
}
