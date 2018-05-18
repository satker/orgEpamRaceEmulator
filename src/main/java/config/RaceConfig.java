package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("") // search the com.company package for @Component classes
@ImportResource("classpath:WEB-INF/applicationContext.xml") // XML with DataSource bean
public class RaceConfig {

}
