package org.epam.com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {

  public static void main(String[] args) {
    new AnnotationConfigApplicationContext();
  }
}