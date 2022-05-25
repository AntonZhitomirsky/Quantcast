package file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class FileParserTest {

  public Parser<TimeStampedCookieEntry> parser = new FileParser<>();

  // generates a valid parser regex rule
  @Test
  public void generatesRegexParserFromRule() {
    String rule1 = parser.setRule("field");
    String rule2 = parser.setRule("field1~~field2");
    String rule3 = parser.setRule("field1,field2-filed3");
    assertThat(rule1, is("(.*)"));
    assertThat(rule2, is("(.*)~~(.*)"));
    assertThat(rule3, is("(.*),(.*)-(.*)"));
  }

  // from a line can extract timeStamp and cookie data
  @Test
  public void canApplyRegexParserToExtractTimeStampAndCookieData() {
    String rule = parser.setRule("cookie,timestamp");
    TimeStampedCookieEntry entry = parser.parse("mycookiehash,mytimestampdata");
    assertThat(entry.getCookie(), is("mycookiehash"));
    assertThat(entry.getDate(), is("mytimestampdata"));
  }
}
