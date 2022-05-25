package entry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStampedCookieEntry {

  private final Date timeStamp;
  private final String cookie;

  public TimeStampedCookieEntry(String cookie, String timeStamp) {
    this(cookie, stringToDate(timeStamp));
  }

  private static Date stringToDate(String timeStamp) {
    // specification only concerned about the date, not the time
    String date = timeStamp.split("T")[0];
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    } catch (ParseException e) {
      return new Date(0, Calendar.JANUARY, 0);
    }
  }

  public TimeStampedCookieEntry(String cookie, Date timeStamp) {
    this.cookie = cookie;
    this.timeStamp = timeStamp;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public String getCookie() {
    return cookie;
  }

  @Override
  public String toString() {
    return "{cookie='" + cookie + '\'' + ", date='" + timeStamp + "'}";
  }
}
