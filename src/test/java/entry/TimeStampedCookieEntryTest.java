package entry;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.sql.Date;
import java.time.LocalDate;
import org.testng.annotations.Test;

public class TimeStampedCookieEntryTest {

  @Test
  public void timeStampIsCorrectlyConvertedTest() {
    TimeStampedCookieEntry timeStamp = new TimeStampedCookieEntry("cookie", "2018-12-08T22:03:00+00:00");
    TimeStampedCookieEntry timeStamp2 = new TimeStampedCookieEntry("cookie", Date.valueOf(
        LocalDate.of(2018, 12, 8)));
    assertThat(timeStamp.getTimeStamp(), is(timeStamp2.getTimeStamp()));
  }

}