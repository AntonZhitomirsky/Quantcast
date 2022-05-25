package file;

import entry.TimeStampedCookieEntry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface CookieFileInterface<E> {

  // extracts information about a file from a given File.
  void read(File file) throws FileNotFoundException;

  // given a rule at the top of the file, this will parse the remainder of the strings in the file
  TimeStampedCookieEntry parse(String line);

  // given a grammar notation rule translates to regex and returns new generated parsing rule
  String setRule(String rule);

  List<E> getLines();
}
