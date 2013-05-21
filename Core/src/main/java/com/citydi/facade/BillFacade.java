package com.citydi.facade;

import com.citydi.bo.BillBo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Emad Aghayi
 * at 2013/05/13 - 17:33
 */
@Component()
@Scope("prototype")
public class BillFacade {
    private static BeanFactory factory = null;
    @PostConstruct
    public void init() {

    }
//    public static void billFill() {
//        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {"/spring/mainContext.xml"});
//        factory = (BeanFactory) appContext;
//    BillBo billBo = (BillBo) factory.getBean("billBo");
//    }
        @Autowired
    private BillBo billBo;

    public void billFill(){
//       loadSpring();
        billBo.fill();
        System.out.print("dfsdfdfdfsfsdfjdfdlfkjsdjfsdlfjrinvv");
    }

    public  void CheckloadCustomer() {
        billBo.find();

    }
}

