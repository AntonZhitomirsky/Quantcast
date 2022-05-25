package file;

import entry.TimeStampedCookieEntry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import parser.Parser;

public class CookieFile<E extends TimeStampedCookieEntry> implements CookieFileInterface {
  // CookieFile is a generic because in future applications one might have time stamp, cookie and
  // geographical position, or maybe more metadata. In this case only the generic type must
  // change

  private final Parser<E> stringParser;
  private List<E> lines;

  public CookieFile(Parser<E> stringParser) {
    this.stringParser = stringParser;
  }

  @Override
  public void read(File file) throws FileNotFoundException {
//    // read first line for the parsing pattern
//    Scanner reader = new Scanner(file);
//    stringParser.setRule(reader.nextLine());
//    // read the rest for cookie and timestamp information
//    while (reader.hasNextLine()) {
//      data += reader.nextLine();
//    }
//    reader.close();
  }

  @Override
  public String setRule(String rule) {
    // convert rule into regex to parse
    // operates on the assumption that the rule is only ever going to be cookie(delimiter)timestamp
    return stringParser.setRule(rule);
  }

  @Override
  public E parse(String line) {
    return stringParser.parse(line);
  }

  @Override
  public List<E> getLines() {
    return lines;
  }
}
