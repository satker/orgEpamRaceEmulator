package org.epam.com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.epam.com") // search the com.company package for @Component classes
public class RaceConfig {

}
