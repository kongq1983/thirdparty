import com.kq.drools.entity.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * ConditionTest
 *
 * @author kq
 * @date 2019-03-20
 */
public class ConditionTest extends BaseTest{


    @Test
    public void testCondition(){

        KieSession kieSession = getKieSessionBySessionName("conditional-rules");
//        KieSession kieSession = getKieSessionBySessionName("all-rules");

        System.out.println("kieSession"+kieSession);

        Person p = new Person();
        p.setAge(19);
//        p.setAge(20);

        kieSession.insert(p);

        int nums = kieSession.fireAllRules();

        System.out.println("Fire " + nums + " rules!");

        System.out.println("age="+p.getAge());



//        KieServices kieServices = KieServices.get();
//        KieContainer kieContainer = kieServices.getKieClasspathContainer();
//        KieSession kieSession = kieContainer.newKieSession("all-rules");

    }


}
