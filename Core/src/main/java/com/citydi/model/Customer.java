package com.citydi.model;

import com.citydi.common.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: e.aghaei
 * Date: 5/20/13
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name ="CUSTOMER" )
public class Customer extends BaseEntity {


    private String name;
    private String address;
    private Date expireDate;
    private String nationalCode;
    private String gender;
    private String family;
//    private List<Bill> bills;
    private List<Bill> billListManyToMany;

    @Column(name ="NAME", nullable = false, length = 100)
    public String getName() {
        return name;
    }
    @Column(name ="ADDRESS" )
    public String getAddress() {
        return address;
    }
    @Column(name ="EXPIREDATE" )
    public Date getExpireDate() {
        return expireDate;
    }
    @Column(name ="NATIONALCODE",unique = true)
    public String getNationalCode() {
        return nationalCode;
    }
    @Column(name ="GENDER" )
    public String getGender() {
        return gender;
    }
    @Column(name ="FAMILY" )
    public String getFamily() {
        return family;
    }
//@OneToMany(cascade=CascadeType.ALL/*, fetch = FetchType.EAGER*/,mappedBy = "customer")
//    public List<Bill> getBills() {
//        return bills;
//    }
//    @Transient
//    public void addBill(Bill bill) {
//        bill.setCustomer(this);
//        bills.add(bill);
//    }
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTOMER_BILL", joinColumns = { @JoinColumn(name = "CUSTOMER_ID",referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "BILL_ID",referencedColumnName = "ID") })
    public List<Bill> getBillListManyToMany() {
        return billListManyToMany;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFamily(String family) {
        this.family = family;
    }

//    public void setBills(List<Bill> bills) {
//        this.bills = bills;
//    }

    public void setBillListManyToMany(List<Bill> billListManyToMany) {
        this.billListManyToMany = billListManyToMany;
    }
}
