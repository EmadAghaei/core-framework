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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public void fillOneToMany(){
        Customer customer=new Customer();
        customer.setAddress("erewrdfgv");
        customer.setExpireDate(new Date());
        customer.setName("emad");
        customer.setFamily("aghayi");
        customer.setGender("111");
        customer.setNationalCode("121212");
        Bill bill = new Bill();
        bill.setBillAmount(989898);
        bill.setBillType("set");
        /*Commented for ManyToMany*/
//        bill.setCustomer(customer);
        List<Bill> billList = new ArrayList<Bill>();
        billList.add(bill);
         /*Commented for ManyToMany*/
//        customer.setBills(billList);
        customerDAO.save(customer);
//        List<Customer> list =customerDAO.findAll();
//        List<Customer> list =customerDAO.findBillsByCustomer();
//        List<Customer> list =customerDAO.findBillsByCustomer();
//        List<Customer> list = (List<Customer>) customerDAO.findBillsByCustomer();
//        for(int i=0; i<list.size();i++){
//            bill.setCustomer(list.get(i));
//            list.get(i).getBills().add(bill);
//            customerDAO.save(list.get(i));
//        }
//        List list2= customerDAO.findById(1L).getBills();
    }

    public void removeOneToMany() {
        List<Customer> customerList =customerDAO.findAll();
        customerDAO.remove(customerList.get(customerList.size()-1));
    }

    public void fillManyToMany() {

        Customer customer=new Customer();
        customer.setAddress("ManyToMany");
        customer.setExpireDate(new Date());
        customer.setName("ManyToMany");
        customer.setFamily("ManyToMany");
        customer.setGender("ManyToMany");
        customer.setNationalCode("ManyToMany");
        Bill bill = new Bill();
        bill.setBillAmount(989898);
        bill.setBillType("set");
        List<Bill> billListTemp = new ArrayList<Bill>();
        billListTemp.add(bill);

        customer.setBillListManyToMany();
        customer.set
        bill.setCustomer(customer);
        List<Bill> billList = new ArrayList<Bill>();
        billList.add(bill);
        customer.setBills(billList);
        customerDAO.save(customer);
    }
}
