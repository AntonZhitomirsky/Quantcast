package file;

import entry.TimeStampedCookieEntry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import parser.Parser;

public class CookieFileTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  public final String path = "test.csv";
  private File tempCookieFile;
  private final static int lines = 5;
  private final static String someRule = "---";
  private final static TimeStampedCookieEntry defaultCookieEntry = new TimeStampedCookieEntry("123",
      "2020-10-01");
  public Parser<TimeStampedCookieEntry> parser = context.mock(Parser.class);

  @Before
  public void setUp() {
    try {
      tempCookieFile = folder.newFile("temp.csv");
      FileWriter fileWriter = new FileWriter(tempCookieFile);
      BufferedWriter buffWriter = new BufferedWriter(fileWriter);
      for (int i = 0; i < lines; i++) {
        buffWriter.write("line" + i + "\n");
      }
      buffWriter.close();
    } catch (IOException ioe) {
      System.err.println(
          "error creating temporary test file in " + this.getClass().getSimpleName());
    }
  }

  @Test
  public void readsAndConvertsLinesFromFileTest() {

    context.checking(new Expectations() {{
      exactly(1).of(parser).setRule("line0");
      will(returnValue(someRule));
      for (int i = 1; i < lines; i++) {
        exactly(1).of(parser).parse("line" + i);
        will(returnValue(defaultCookieEntry));
      }
    }});

    CookieFile<TimeStampedCookieEntry> cf = new CookieFile<>(parser, tempCookieFile);
  }

}
