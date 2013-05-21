import com.citydi.facade.BillFacade;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Emad Aghayi
 * at 2013/05/14 - 15:26
 */
public class BillTest {

    private static BeanFactory factory = null;

    @BeforeClass
    public static void loadSpring() {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {"/spring/mainContext.xml"});
        factory = (BeanFactory) appContext;
    }

    @Test
    public void test(){
        BillFacade billFacade = (BillFacade) factory.getBean("billFacade");
        billFacade.billFill();
//        billFacade.CheckloadCustomer();
    }

}
