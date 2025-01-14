package org.library;

import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This formatter only useful when directly logging with JUL
// It is not used when bridging to other frameworks
public class JsonFormatter extends Formatter {
  private static final Pattern FIELD_PATTERN = Pattern.compile("\\{(.+?)=(.+?)}");
  @Override
  public String format(LogRecord record) {
    // Create a JSON object
    JsonObject json = new JsonObject();

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
    Object[] params = record.getParameters();
    if (params != null && params.length > 0) {
      return json.toString() + " - Parameters: " + Arrays.toString(params) +  "\n";
    } else {
      return json.toString() +  "\n";
    }

    // return json.toString() + "\n";
  }
}
