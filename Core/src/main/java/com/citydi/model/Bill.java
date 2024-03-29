package com.citydi.model;

import com.citydi.common.base.BaseEntity;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.List;

/**
 * Created by Emad Aghayi
 * at 2013/05/07 - 12:11
 */
@Entity
@Table(name = "BILL")
//@SequenceGenerator(name = "idGenerator", sequenceName = "SEQ_BILL", allocationSize = 1)
public class Bill extends BaseEntity {

    private int billAmount;
    private String billType;
//    private Customer customer;
    private List<Customer> customerListManyToMany;

    @Column(name ="BILLAMOUNT" )
    public int getBillAmount() {
        return billAmount;
    }
    @Column(name ="BILLTYPE" )
    public String getBillType() {
        return billType;
    }
//    @ManyToOne
//    @JoinColumn(name = "FK_CUSTOMER")
//    public Customer getCustomer() {
//        return customer;
//    }
    @ManyToMany(mappedBy = "billListManyToMany")
    public List<Customer> getCustomerListManyToMany() {
        return customerListManyToMany;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    public void setCustomerListManyToMany(List<Customer> customerListManyToMany) {
        this.customerListManyToMany = customerListManyToMany;
    }
}


