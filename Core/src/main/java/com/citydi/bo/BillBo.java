package com.citydi.bo;

import com.citydi.common.base.BaseBean;
import com.citydi.dal.dao.BillDAO;
import com.citydi.dal.dao.CustomerDAO;
import com.citydi.model.Bill;
import com.citydi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by Emad Aghayi
 * at 2013/05/07 - 13:42
 */
@Component
@Scope("prototype")
public class BillBo extends BaseBean {


    @Autowired
    private BillDAO billDAO;

    @Autowired
    private CustomerDAO customerDAO;
    @PostConstruct
    public void init() {

    }


    public void fill() {
        System.out.print("dsfsdfsdfjfsdnhsdfjui");
        Bill bill = new Bill();
//        Customer customer =new Customer();
//
//        customer.setAddress("ee");
//        customer.setExpireDate(new Date());
//        customer.setFamily("ee");
//        customer.setName("ee");
//        customer.setNationalCode("ee");
//        customer.setGender("ee");
//        customer.setBillList();
//        customerDAO.save(customer);
//        bill.setCustomer(customer);
        bill.setBillAmount(2347);
        bill.setBillType("emad");
        billDAO.save(bill);
    }

    public void find() {

//        billDAO.findById(5L).getCustomer();
    }
}

