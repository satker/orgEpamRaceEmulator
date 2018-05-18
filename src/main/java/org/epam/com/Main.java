package org.epam.com;

import org.epam.com.config.RaceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    new AnnotationConfigApplicationContext(RaceConfig.class);
  }
}