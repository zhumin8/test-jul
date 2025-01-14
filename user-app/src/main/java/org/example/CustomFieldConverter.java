package org.example;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomFieldConverter extends ClassicConverter {
  private static final Pattern FIELD_PATTERN = Pattern.compile("\\{(.+?)=(.+?)}");

  @Override
  public String convert(ILoggingEvent event) {
    String message = event.getMessage();
    Matcher matcher = FIELD_PATTERN.matcher(message);
    StringBuilder extractedFields = new StringBuilder("{");

    // while (matcher.find()) {
    //   String fieldName = matcher.group(1);
    //   String fieldValue = matcher.group(2);
    //   extractedFields.append("\"").append(fieldName).append("\": \"").append(fieldValue).append("\", ");
    // }
    //
    // if (extractedFields.length() > 1) {
    //   // Remove the trailing comma and space
    //   extractedFields.delete(extractedFields.length() - 2, extractedFields.length());
    // }
    //
    // extractedFields.append("}");
    extractedFields.append(", \"");
    while (matcher.find()) {
      String fieldName = matcher.group(1);
      String fieldValue = matcher.group(2);
      // Directly add the fields as key-value pairs
      extractedFields.append(", \"").append(fieldName).append("\": \"").append(fieldValue).append("\"");
    }
    // Remove the leading ", " if any fields were extracted
    if (extractedFields.length() > 0) {
      extractedFields.delete(0, 2);
    }

    return extractedFields.toString();
  }
}