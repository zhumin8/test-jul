package org.example;

import java.util.logging.LogRecord;
import org.library.JsonFormatter;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class CustomBridgeHandler extends SLF4JBridgeHandler {
  private final JsonFormatter formatter = new JsonFormatter();

  @Override
  public void publish(LogRecord record) {
    String formattedMessage = formatter.format(record);
    // ... logic to bridge the formatted message to SLF4j ...
  }
}
