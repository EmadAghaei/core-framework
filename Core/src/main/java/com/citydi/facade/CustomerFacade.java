package com.citydi.facade;

import com.citydi.bo.CustomerBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: e.aghaei
 * Date: 5/20/13
 * Time: 10:10 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope("prototype")
public class CustomerFacade {

    @Autowired
    private CustomerBo customerBo;

    public void fillCustomer(){
        customerBo.fillOneToMany();
        System.out.print("Customer Savedddddddddddddd");
    }

    public void removeCustomer() {
        customerBo.removeOneToMany();
        System.out.print("Customer Deletedddddddddddddd");
    }

    public void fillCustomerManyToMany() {
        customerBo.fillManyToMany();
        System.out.print("ManyToMany Customer Savedddddddddddddd");
    }
}
