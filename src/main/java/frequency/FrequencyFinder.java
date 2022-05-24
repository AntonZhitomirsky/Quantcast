package frequency;

import java.io.File;
import java.util.List;

public class FrequencyFinder {

  private final File file;
  private final String date;
  private final CountingStrategy countingStrategy;

  public FrequencyFinder(File file, String date, CountingStrategy countingStrategy) {
    this.file = file;
    this.date = date;
    this.countingStrategy = countingStrategy;
  }

  public List<Cookie> getMostFrequentCookie() {
    return countingStrategy.getMostFrequentCookie(file, date);
  }
}
