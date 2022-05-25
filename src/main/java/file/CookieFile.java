package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CookieFile<E extends Entry> implements CookieFileInterface {
  // CookieFile is a generic because in future applications one might have time stamp, cookie and
  // geographical position, or maybe more metadata. In this case only the generic type must
  // change

  private String rule;
  private String data;
  private static final String CSV_REGEX = ".*\\.csv";
  private static final Pattern CSV_MATCHER = Pattern.compile(CSV_REGEX);

  @Override
  public void read(File file) throws FileNotFoundException {
    // read first line for the parsing pattern
    Scanner reader = new Scanner(file);
    setRule(reader.nextLine());
    // read the rest for cookie and timestamp information
    while (reader.hasNextLine()) {
      data += reader.nextLine();
    }
    reader.close();
  }

  @Override
  public String setRule(String rule) {
    // convert rule into regex to parse
    // operates on the assumption that the rule is only ever going to be cookie(delimiter)timestamp
    String regex = "([a-zA-Z\\d]+)"; // regex to separate tokens from punctuation delimiters
    Matcher m = Pattern.compile(regex).matcher(rule); // applies regex to rule
    this.rule = m.replaceAll("(\\.*)");
    return this.rule;
  }

  @Override
  public String data() {
    assert data
        != null : "data is null, either there is no data in the file or you forget to read first?";
    return data;
  }

  @Override
  public E parse(String line) {
    // applies the parsing pattern and returns an entry
    // for now, since assumption: cookie,timestamp always return a TimeStampedCookieEntry
    Matcher m = Pattern.compile(rule).matcher(line);
    if (m.matches()) {
      return (E) new TimeStampedCookieEntry(m.group(1), m.group(2));
    }
    // no match found, fine is not well-defined
    return null;
  }

  public static void main(String[] args) {
    CookieFile<TimeStampedCookieEntry> cf = new CookieFile<>();
    cf.setRule("cookie,timestamp");
    System.out.println(cf.rule);
    System.out.println(cf.parse("4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00"));
  }
}
