package org.library;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class LogbackConfig {
  static void setUpLogbackBridge() {
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();

    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    PatternLayoutEncoder encoder = new PatternLayoutEncoder();
    encoder.setContext(loggerContext);
    // encoder.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg %converterName%n");
    // encoder.setPattern("{\"timestamp\": \"%d{yyyy-MM-dd'T'HH:mm:ss.SSS'Z'}\", \"level\": \"%level\", \"logger\": \"%logger{36}\", \"message\": \"%message\", \"customFields\": %converterName}%n");

    // %nopex is used to prevent any exceptions thrown by the converter from disrupting logging.
    encoder.setPattern("{\"timestamp\": \"%d{yyyy-MM-dd'T'HH:mm:ss.SSS'Z'}\", \"level\": \"%level\", \"logger\": \"%logger{36}\", \"message\": \"%replace(%msg){'\\{(.+?)=(.+?)}', ''}\"%nopex %converterName}%n");
    encoder.start();
    FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
    fileAppender.setContext(loggerContext);
    fileAppender.setFile("library.log");
    fileAppender.setEncoder(encoder);
    fileAppender.start();
    ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

    rootLogger.addAppender(fileAppender);
  }
}
