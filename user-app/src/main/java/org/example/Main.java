package org.example;

import org.library.MyLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(org.library.Main.class);

  public static void main(String[] args) {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();

//     // In your user application's initialization code
//
// // 1. Get your library's logger
//     java.util.logging.Logger libraryLogger = java.util.logging.Logger.getLogger("org.example.MyLibrary");
//
// // 2. Remove any existing handlers on the library's logger
//     for (Handler handler : libraryLogger.getHandlers()) {
//       libraryLogger.removeHandler(handler);
//     }
//
// // 3. Install the SLF4JBridgeHandler
//     SLF4JBridgeHandler.removeHandlersForRootLogger();
//     SLF4JBridgeHandler slf4jHandler = new SLF4JBridgeHandler();
//     slf4jHandler.setLevel(Level.FINEST);
//     java.util.logging.Logger.getLogger("").addHandler(slf4jHandler);
//
// // 4. Add your JsonFormatter to the library's logger
//     Handler jsonHandler = new ConsoleHandler();
//     jsonHandler.setFormatter(new JsonFormatter());
//     libraryLogger.addHandler(jsonHandler);

    LogbackConfig.configureLogback();
    System.out.println("This is the user app.");
    MyLibrary.testLogging();

    LOGGER.info("This is log item from user app");
    LOGGER.atInfo().addKeyValue("key", "string value").log("Another message from user app");
    LOGGER.debug("This is debug message from user app");

  }
}