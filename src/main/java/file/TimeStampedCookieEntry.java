package file;

public class TimeStampedCookieEntry implements Entry {

  private final String cookie;
  private final String date;

  public TimeStampedCookieEntry(String cookie, String date) {
    this.cookie = cookie;
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  public String getCookie() {
    return cookie;
  }

  @Override
  public String toString() {
    return "{cookie='" + cookie + '\'' + ", date='" + date + "'}";
  }
}
