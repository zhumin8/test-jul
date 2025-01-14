package org.example;

import org.library.MyLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(org.library.Main.class);

  public static void main(String[] args) {
    // configure bridge
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();

    // continue with application code and log
    System.out.println("This is the user app.");
    MyLibrary.testLogging();

    LOGGER.info("This is log item from user app");
    LOGGER.atInfo().addKeyValue("key", "string value").log("Another message from user app");
    LOGGER.debug("This is debug message from user app");

  }
}