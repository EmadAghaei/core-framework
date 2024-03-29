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
import java.util.List;

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


    public void fillManyToOne() {

        System.out.print("Entered");

        Customer customer =new Customer();
        Bill bill = new Bill();

        customer.setAddress("IR");
        customer.setExpireDate(new Date());
        customer.setFamily("IR");
        customer.setName("X-MAN");
        customer.setNationalCode("89");
        customer.setGender("1");
        customerDAO.save(customer);
        bill.setBillAmount(2347);
        bill.setBillType("emad");
        bill.setCustomer(customer);
        billDAO.save(bill);
        System.out.print("Done!");
    }

    public void find() {

//        billDAO.findById(5L).getCustomer();
    }

    public void removeManyToOne() {
        List<Bill> billList= billDAO.findAll();
        billDAO.remove(billList.get(billList.size()-1));
        System.out.print("Done!!");
    }
}

