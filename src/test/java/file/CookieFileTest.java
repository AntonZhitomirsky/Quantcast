package file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CookieFileTest {

  private File tempCookieFile;
  private final String fName = "temp.csv";

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  public CookieFileInterface cookieFile = new CookieFile<TimeStampedCookieEntry>();

  // generates a valid parser regex rule
  @Test
  public void generatesRegexParserFromRule() {
    String rule1 = cookieFile.setRule("field");
    String rule2 = cookieFile.setRule("field1~~field2");
    String rule3 = cookieFile.setRule("field1,field2-filed3");
    assertThat(rule1, is("(.*)"));
    assertThat(rule2, is("(.*)~~(.*)"));
    assertThat(rule3, is("(.*),(.*)-(.*)"));
  }

  // from a line can extract timeStamp and cookie data
  @Test
  public void canApplyRegexParserToExtractTimeStampAndCookieData() {
    String rule = cookieFile.setRule("cookie,timestamp");
    TimeStampedCookieEntry entry = (TimeStampedCookieEntry) cookieFile.parse(
        "mycookiehash,mytimestampdata");
    assertThat(entry.getCookie(), is("mycookiehash"));
    assertThat(entry.getDate(), is("mytimestampdata"));
  }
}
