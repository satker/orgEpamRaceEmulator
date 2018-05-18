import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan
public class Main {

  public static void main(String[] args) {
    new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");
  }
}