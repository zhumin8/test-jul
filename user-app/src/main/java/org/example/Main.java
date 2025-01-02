package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    System.out.println("This is the user app.");
    MyLibrary.testLogging();

    LOGGER.info("This is log item from user app");
    LOGGER.atInfo().addKeyValue("key", "string value").log("Another message from user app");

  }
}