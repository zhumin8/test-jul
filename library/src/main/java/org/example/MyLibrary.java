package org.example;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLibrary {
  private final static Logger JULLOGGER = Logger.getLogger(MyLibrary.class.getName());
  static void testLogging() {
    System.out.println("you are now running library methods.");
    // Remove all existing handlers (including the default ConsoleHandler)

    // Get the root logger
    Logger rootLogger = Logger.getLogger("");

    // Remove handlers from the root logger
    for (Handler handler : rootLogger.getHandlers()) {
      rootLogger.removeHandler(handler);
    }

    Handler handler = new ConsoleHandler();
    handler.setFormatter(new JsonFormatter());
    JULLOGGER.addHandler(handler);

    JULLOGGER.log(Level.INFO, "this is a logging message");
    JULLOGGER.log(Level.INFO, "This is a message with {userId=user123} and {transactionId=tx456}");
  }
}