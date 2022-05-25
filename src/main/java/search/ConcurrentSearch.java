package search;

import entry.TimeStampedCookieEntry;
import file.CookieFileInterface;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentSearch implements SmallestCache {

  private final HashMap<String, Integer> cookieFrequency = new HashMap<>();
  private final CookieFileInterface<TimeStampedCookieEntry> cookieFile;
  private final static int timeoutSeconds = 20;

  public ConcurrentSearch(CookieFileInterface<TimeStampedCookieEntry> cookieFile) {
    this.cookieFile = cookieFile;
  }

  @Override
  public List<String> getMostFrequentCookie(Date date) {
    List<TimeStampedCookieEntry> lines = cookieFile.getLines();

    int numberOfThreads = 2;
    ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

    List<TimeStampedCookieEntry> head = lines.subList(0, lines.size() / 2);
    List<TimeStampedCookieEntry> tail = lines.subList(lines.size() / 2, lines.size());

    executor.execute(new SegmentSearch(date, head, this));
    executor.execute(new SegmentSearch(date, tail, this));

    executor.shutdown();
    try {
      executor.awaitTermination(timeoutSeconds, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    return getMostFrequentFromHashMap();
  }

  private List<String> getMostFrequentFromHashMap() {
    List<String> maxCookies = new LinkedList<>();
    int currentMax = 0;
    for (Map.Entry<String, Integer> entry : cookieFrequency.entrySet()) {
      if (entry.getValue() > currentMax) {
        maxCookies = new LinkedList<>();
        maxCookies.add(entry.getKey());
        currentMax = entry.getValue();
      } else if (entry.getValue() == currentMax) {
        maxCookies.add(entry.getKey());
      }
    }
    return maxCookies;
  }

  protected synchronized void incrementCookie(String cookie) {
    if (cookieFrequency.containsKey(cookie)) {
      cookieFrequency.put(cookie, cookieFrequency.get(cookie) + 1);
    } else {
      cookieFrequency.put(cookie, 1);
    }
  }
}
