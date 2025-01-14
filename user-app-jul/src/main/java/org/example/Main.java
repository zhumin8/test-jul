package org.example;

import java.util.logging.Logger;
import org.library.MyLibrary;

public class Main {

  private final static Logger JULLOGGER = Logger.getLogger(Main.class.getName());
  public static void main(String[] args) {
    System.out.println("Hello, World!");
    JULLOGGER.info("log message from user app w/o logback.");

    MyLibrary.testLogging();
  }
}