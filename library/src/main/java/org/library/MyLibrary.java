package org.library;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLibrary {
  private final static Logger JULLOGGER = Logger.getLogger(MyLibrary.class.getName());
  public static void testLogging() {
    System.out.println("you are now running library methods.");

    if (isLogbackPresent()) {
      LogbackConfig.setUpLogbackBridge();
    }


    // setting custom formatter

    // // Get the root logger
    // Logger rootLogger = Logger.getLogger("");
    //
    // // Remove handlers from the root logger
    // for (Handler handler : rootLogger.getHandlers()) {
    //   rootLogger.removeHandler(handler);
    // }
    //
    // Handler handler = new ConsoleHandler();
    // handler.setFormatter(new JsonFormatter());
    // JULLOGGER.addHandler(handler);

    JULLOGGER.log(Level.INFO, "this is a logging message from library ");
    JULLOGGER.log(Level.INFO, "This is a message  from library with {userId=user123}  {origin=library} and {transactionId=tx456}");
    JULLOGGER.log(Level.FINE, "This is a debug level message from library {origin=library} ");

    JULLOGGER.log(Level.INFO, "From library: isLogbackPresent?{0}", isLogbackPresent());
    // LogRecord record = new LogRecord(Level.INFO, "This is a log message -- {0},{1}.");
    // record.setParameters(new Object[] { "param1", "123"});
    // JULLOGGER.log(record);
  }

  // Use reflection to determine
  private static boolean isLogbackPresent() {
    try {
      // Get the ClassLoader of the user's application
      // This line retrieves the ClassLoader associated with the current thread.
      // In most application servers and environments, this will be the ClassLoader of the user's application,
      // which is where their dependencies (including Logback if present) are loaded.
      ClassLoader userClassLoader = Thread.currentThread().getContextClassLoader();

      // Use the user's ClassLoader for the reflection check
      Class.forName("ch.qos.logback.classic.LoggerContext", false, userClassLoader);
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }


}
