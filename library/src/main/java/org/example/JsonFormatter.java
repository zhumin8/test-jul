package org.example;

import com.google.gson.JsonObject;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonFormatter extends Formatter {
  private static final Pattern FIELD_PATTERN = Pattern.compile("\\{(.+?)=(.+?)}");
  @Override
  public String format(LogRecord record) {
    // Create a JSON object
    JsonObject json = new JsonObject();

    // Add logging details
    json.addProperty("timestamp", record.getMillis());
    json.addProperty("level", record.getLevel().getName());
    json.addProperty("logger", record.getLoggerName());
    json.addProperty("message", record.getMessage());

    // Extract custom fields from the message
    String message = record.getMessage();
    Matcher matcher = FIELD_PATTERN.matcher(message);
    while (matcher.find()) {
      String fieldName = matcher.group(1);
      String fieldValue = matcher.group(2);
      json.addProperty(fieldName, fieldValue);
    }

    // Remove custom fields from the message
    String cleanMessage = message.replaceAll("\\{(.+?)=(.+?)}", "");
    json.addProperty("message", cleanMessage);

    Throwable thrown = record.getThrown();
    if (thrown != null) {
      json.addProperty("exception", thrown.toString());
    }

    return json.toString() + "\n";
  }
}
