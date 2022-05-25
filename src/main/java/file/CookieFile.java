package file;

import entry.TimeStampedCookieEntry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import parser.Parser;

public class CookieFile<E extends TimeStampedCookieEntry> implements CookieFileInterface {
  // CookieFile is a generic because in future applications one might have time stamp, cookie and
  // geographical position, or maybe more metadata. In this case only the generic type must
  // change

  private final Parser<E> stringParser;
  private List<E> lines = new LinkedList<>();

  public CookieFile(Parser<E> stringParser, String path) {
    this.stringParser = stringParser;
    readFile(path);
  }

  public CookieFile(Parser<E> stringParser, File file) {
    this.stringParser = stringParser;
    readFile(file);
  }

  private void readFile(File file) {
    try {
      Scanner reader = new Scanner(file);
      stringParser.setRule(reader.nextLine());
      while (reader.hasNextLine()) {
        lines.add(stringParser.parse(reader.nextLine()));
      }
      reader.close();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private void readFile(String path) {
    readFile(new File(path));
  }

  @Override
  public List<E> getLines() {
    return lines;
  }
}
