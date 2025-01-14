package org.library;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.FileAppender;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class LogbackConfig {
  // Installs bridge handler and setup logback to use custom converter.
  // Sets up encoder pattern to include converterName
  static void setUpLogbackBridge() {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();

    
    // these sets up for user app to user below encoder pattern (included %converterName keyword)
    // NOTE: attaches a specific appender to logger, to allow user flexibility to control appenders
    // need to provide multiple options AND/OR expose to users to customize
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    // setup conversionrule,
    // ref: https://stackoverflow.com/questions/40922746/how-do-i-programmatically-set-conversionrule-for-logback
    // https://stackoverflow.com/questions/43387987/how-to-configure-logback-conversionrule-programmatically-using-java
    // maintain and future changes?
    String conversionWord = "converterName";
    String converterClass = "org.library.CustomFieldConverter";
    // This line retrieves the PatternRuleRegistry from the LoggerContext, an internal object
    Map<String, String> ruleRegistry = (Map)  loggerContext.getObject(CoreConstants.PATTERN_RULE_REGISTRY);
    if (ruleRegistry == null) {
      ruleRegistry = new HashMap<String, String>();
    }
    loggerContext.putObject(CoreConstants.PATTERN_RULE_REGISTRY, ruleRegistry);
    ruleRegistry.put(conversionWord, converterClass);


    PatternLayoutEncoder encoder = new PatternLayoutEncoder();
    encoder.setContext(loggerContext);

    // %nopex is used to prevent any exceptions thrown by the converter from disrupting logging.
    encoder.setPattern("{\"timestamp\": \"%d{yyyy-MM-dd'T'HH:mm:ss.SSS'Z'}\", \"level\": \"%level\", \"logger\": \"%logger{36}\", \"message\": \"%replace(%msg){'\\{(.+?)=(.+?)}', ''}\"%nopex %converterName}%n");
    encoder.start();
    FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
    fileAppender.setContext(loggerContext);
    fileAppender.setFile("library.log");
    fileAppender.setEncoder(encoder);
    fileAppender.start();
    // // add appender to root logger
    // ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
    // rootLogger.addAppender(fileAppender);

    // Add the appender to the library's logger
    ch.qos.logback.classic.Logger libraryLogger = loggerContext.getLogger("org.library.MyLibrary");
    libraryLogger.addAppender(fileAppender);


  }
}
