package file;

import entry.TimeStampedCookieEntry;
import java.io.File;
import java.io.FileNotFoundException;

public interface CookieFileInterface {

  // extracts information about a file from a given File.
  void read(File file) throws FileNotFoundException;

  // given a rule at the top of the file, this will parse the remainder of the strings in the file
  TimeStampedCookieEntry parse(String line);

  // given a grammar notation rule translates to regex and returns new generated parsing rule
  String setRule(String rule);
}
