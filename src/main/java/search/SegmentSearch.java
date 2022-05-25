package search;

import entry.TimeStampedCookieEntry;
import java.util.Date;
import java.util.List;

public class SegmentSearch implements Runnable {

  private final Date date;
  private final List<TimeStampedCookieEntry> segment;
  private final ConcurrentSearch cookieFrequency;

  public SegmentSearch(Date date, List<TimeStampedCookieEntry> segment,
      ConcurrentSearch cookieFrequency) {
    this.date = date;
    this.segment = segment;
    this.cookieFrequency = cookieFrequency;
  }

  @Override
  public void run() {
    for (TimeStampedCookieEntry entry : segment) {
      if (entry.getTimeStamp().equals(date)) {
        cookieFrequency.incrementCookie(entry.getCookie());
      }
    }
  }


}
