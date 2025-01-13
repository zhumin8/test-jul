package org.example;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class LogbackConfig {

  public static void configureLogback() {
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    // Create a PatternLayoutEncoder with your custom converter
    PatternLayoutEncoder encoder = new PatternLayoutEncoder();
    encoder.setContext(loggerContext);
    encoder.setPattern("{\"timestamp\": \"%d{yyyy-MM-dd'T'HH:mm:ss.SSS'Z'}\", \"level\": \"%level\", \"logger\": \"%logger{36}\", \"message\": \"%message\", \"customFields\": %converterName}{%n}");
    encoder.start();

    // Create a ConsoleAppender
    ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
    appender.setContext(loggerContext);
    appender.setEncoder(encoder);
    appender.start();

    // Get the root logger and add the appender
    Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
    rootLogger.addAppender(appender);
  }
}