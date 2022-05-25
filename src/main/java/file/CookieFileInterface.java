package file;

import entry.TimeStampedCookieEntry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface CookieFileInterface<E> {
  List<E> getLines();
}
