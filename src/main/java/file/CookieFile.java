package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CookieFile<E extends Entry> implements CookieFileInterface {

  private final FileParser<E> fileParser = new FileParser<>();
  // CookieFile is a generic because in future applications one might have time stamp, cookie and
  // geographical position, or maybe more metadata. In this case only the generic type must
  // change

  private String data;
  private static final String CSV_REGEX = ".*\\.csv";
  private static final Pattern CSV_MATCHER = Pattern.compile(CSV_REGEX);

  @Override
  public void read(File file) throws FileNotFoundException {
    // read first line for the parsing pattern
    Scanner reader = new Scanner(file);
    fileParser.setRule(reader.nextLine());
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
    return fileParser.setRule(rule);
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
    // no match found, fine is not well-defined
    return fileParser.parse(line);
  }
}
