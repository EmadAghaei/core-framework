import com.citydi.facade.CustomerFacade;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: e.aghaei
 * Date: 5/20/13
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerTest {
    private static BeanFactory factory =null;
        @BeforeClass
    public static void  loadSpring(){
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"/spring/mainContext.xml"}) ;
                    factory= (BeanFactory)applicationContext;

    }
    @Test
    public void customerInsertTest(){
        CustomerFacade customerFacade = (CustomerFacade) factory.getBean("customerFacade");
      /*fill OneToMany*/
        //customerFacade.fillCustomer();

      /*fill ManyToMany*/
        customerFacade.fillCustomerManyToMany();

    }
    @Test
    public void customerRemoveTest(){
        CustomerFacade customerFacade = (CustomerFacade) factory.getBean("customerFacade");
        customerFacade.removeCustomer();

          }
}
