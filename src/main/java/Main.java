import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.emulation.EmulationServiceImpl;

public class Main {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "WEB-INF/applicationContext.xml");
    EmulationServiceImpl emulation = (EmulationServiceImpl) applicationContext.getBean(
        "emulation");
    emulation.start();
  }

}
