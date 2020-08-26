package com.codeminders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LineValidatorTest {

  @Test
  public void shouldReturnTrueIfContainCode() {
    String line = "test code";

    boolean isContainCode = LineValidator.isContainCode(line, false);

    assertThat(isContainCode).isTrue();
  }

  @Test
  public void shouldReturnFalseIfContainCodeAndCommentStarted() {
    String line = "test code";

    boolean isContainCode = LineValidator.isContainCode(line, true);

    assertThat(isContainCode).isFalse();
  }

  @Test
  public void shouldReturnFalseForCommentedLine() {
    String line = "//test code";

    boolean isContainCode = LineValidator.isContainCode(line, false);

    assertThat(isContainCode).isFalse();
  }

  @Test
  public void shouldReturnFalseForEmptyLine() {
    String line = "   ";

    boolean isContainCode = LineValidator.isContainCode(line, false);

    assertThat(isContainCode).isFalse();
  }

  @Test
  public void shouldReturnTrueIfCommentStartedAndEndedAtLine() {
    String line = "/*comment*/ test /*comment*/ code";

    boolean isContainCode = LineValidator.isContainCode(line, false);

    assertThat(isContainCode).isTrue();
  }

  @Test
  public void shouldReturnTrueIfCommentStartedBeforeAndEndedAtLine() {
    String line = "comment*/ test /*comment*/ code";

    boolean isContainCode = LineValidator.isContainCode(line, true);

    assertThat(isContainCode).isTrue();
  }

  @Test
  public void shouldReturnTrueIfCommentStarted() {
    String line = "/*comment";

    boolean isContainCode = LineValidator.isCommentStarted(line);

    assertThat(isContainCode).isTrue();
  }

  @Test
  public void shouldReturnTrueIfCommentStartedAfterEndingOfOther() {
    String line = "/*comment */ code /*comment";

    boolean isContainCode = LineValidator.isCommentStarted(line);

    assertThat(isContainCode).isTrue();
  }

  @Test
  public void shouldReturnFalseIfCommentStartedInQuotes() {
    String line = "String string = new String(\"/*\")";

    boolean isContainCode = LineValidator.isCommentStarted(line);

    assertThat(isContainCode).isFalse();
  }

  @Test
  public void shouldReturnFalseIfCommentStartedButEndedInQuotes() {
    String line = "/*comment*/ code /*String string = new String(\"*/\")";

    boolean isContainCode = LineValidator.isCommentStarted(line);

    assertThat(isContainCode).isFalse();
  }

  @Test
  public void shouldReturnTrueIfCommentEnded() {
    String line = "*/comment";

    boolean isContainCode = LineValidator.isCommentEnded(line);

    assertThat(isContainCode).isTrue();
  }

  @Test
  public void shouldReturnTrueIfCommentEndedAfterStartingOfOther() {
    String line = "*/ code /* comment */ code";

    boolean isContainCode = LineValidator.isCommentEnded(line);

    assertThat(isContainCode).isTrue();
  }

  @Test
  public void shouldReturnFalseIfCommentEndedInQuotes() {
    String line = "String string = new String(\"*/\")";

    boolean isContainCode = LineValidator.isCommentStarted(line);

    assertThat(isContainCode).isFalse();
  }

  @Test
  public void shouldReturnTrueIfCommentEndedButStartedInQuotes() {
    String line = "*/String/* string*/ = new String(\"/*\")";

    boolean isContainCode = LineValidator.isCommentEnded(line);

    assertThat(isContainCode).isTrue();
  }


}
