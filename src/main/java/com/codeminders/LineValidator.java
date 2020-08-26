package com.codeminders;

public class LineValidator {

  private static String START_COMMENT = "/*";
  private static String END_COMMENT = "*/";

  public static boolean isCommentStarted(String line) {
    if (line.length() < START_COMMENT.length()) {
      return false;
    }
    int startCommentPosition = line.indexOf(START_COMMENT);
    if (startCommentPosition < 0) {
      return false;
    }
    if (isInQuotes(line, startCommentPosition)) {
      int quoteEndIndex = line.indexOf("\"", startCommentPosition);
      return quoteEndIndex > 0 && isCommentStarted(line.substring(quoteEndIndex + 1));
    }
    int endCommentPosition = line.indexOf(END_COMMENT, startCommentPosition);
    if (endCommentPosition < 0) {
      return true;
    }
    return isCommentStarted(line.substring(endCommentPosition + END_COMMENT.length()));
  }

  public static boolean isCommentEnded(String line) {
    if (line.length() < END_COMMENT.length()) {
      return false;
    }
    int endCommentPosition = line.indexOf(END_COMMENT);
    if (endCommentPosition < 0) {
      return false;
    }
    return !isCommentStarted(line.substring(endCommentPosition + END_COMMENT.length()));
  }

  private static boolean isInQuotes(String line, int checkedIndex) {
    if (checkedIndex <= 0) {
      return false;
    }
    long quotesCount = line.substring(0, checkedIndex).chars()
      .filter(ch -> ch =='"')
      .count();

    return quotesCount%2 > 0;
  }

  public static boolean isContainCode(String line, boolean isCommentStarted) {
    line = line.trim();
    if (line.isEmpty() || line.startsWith("//")) {
      return false;
    }
    if (line.length() == 1) {
      return true;
    }

    if (isCommentStarted || line.indexOf("/*") == 0) {
      int endCommentPosition = line.indexOf("*/");
      if (endCommentPosition < 0 || endCommentPosition + 2 >= line.length()) {
        return false;
      }
      return isContainCode(line.substring(endCommentPosition + 2), false);

    }
    return true;
  }
}
