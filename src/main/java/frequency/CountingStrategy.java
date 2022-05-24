package frequency;

import java.io.File;
import java.util.List;

public interface CountingStrategy {

  List<Cookie> getMostFrequentCookie(File file, String date);
}
