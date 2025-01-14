package org.library;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomFieldConverter extends ClassicConverter {
  private static final Pattern FIELD_PATTERN = Pattern.compile("\\{(.+?)=(.+?)}");

  /**
   * extracts custom field from message. This need to be configured in conversionRule
   * ref: https://logback.qos.ch/manual/layouts.html#customConversionSpecifier
   */
  @Override
  public String convert(ILoggingEvent event) {
    String message = event.getMessage();
    Matcher matcher = FIELD_PATTERN.matcher(message);
    StringBuilder extractedFields = new StringBuilder("{");

    // NOTE: logic to extract json fields from JUL message.
    // Here is a simplified logic that does not deal with nested json objects (with a small error still)
    // This is parser logic that need to maintain.
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