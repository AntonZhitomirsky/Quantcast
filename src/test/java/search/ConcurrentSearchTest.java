package search;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import entry.TimeStampedCookieEntry;
import file.CookieFileInterface;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ConcurrentSearchTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  public CookieFileInterface<TimeStampedCookieEntry> cookieFile = context.mock(
      CookieFileInterface.class);

  public ConcurrentSearch search = new ConcurrentSearch(cookieFile);

  public final static List<TimeStampedCookieEntry> lines = List.of(
      new TimeStampedCookieEntry("AtY0laUfhglK3lC7", "2018-12-09T14:19:00+00:00"),
      new TimeStampedCookieEntry("SAZuXPGUrfbcn5UA", "2018-12-09T10:13:00+00:00"),
      new TimeStampedCookieEntry("5UAVanZf6UtGyKVS", "2018-12-09T07:25:00+00:00"),
      new TimeStampedCookieEntry("AtY0laUfhglK3lC7", "2018-12-09T06:19:00+00:00"),
      new TimeStampedCookieEntry("SAZuXPGUrfbcn5UA", "2018-12-08T22:03:00+00:00"),
      new TimeStampedCookieEntry("4sMM2LxV07bPJzwf", "2018-12-08T21:30:00+00:00"),
      new TimeStampedCookieEntry("fbcn5UAVanZf6UtG", "2018-12-08T09:30:00+00:00"),
      new TimeStampedCookieEntry("4sMM2LxV07bPJzwf", "2018-12-07T23:30:00+00:00")
  );

  @Test
  public void getsOneMostFrequentCookieEntry() {

    simulateGetLines();

    Date date = java.sql.Date.valueOf(LocalDate.of(2018, 12, 9));

    List<String> mostFrequent = search.getMostFrequentCookie(date);

    assertThat(mostFrequent.size(), is(1));
    assertThat(mostFrequent.contains("AtY0laUfhglK3lC7"), is(true));
  }

  @Test
  public void getsMultipleMostFrequentCookieEntry() {

    simulateGetLines();

    Date date = java.sql.Date.valueOf(LocalDate.of(2018, 12, 8));

    List<String> mostFrequent = search.getMostFrequentCookie(date);

    assertThat(mostFrequent.size(), is(3));
    assertThat(mostFrequent.contains("SAZuXPGUrfbcn5UA"), is(true));
    assertThat(mostFrequent.contains("4sMM2LxV07bPJzwf"), is(true));
    assertThat(mostFrequent.contains("fbcn5UAVanZf6UtG"), is(true));
  }

  private void simulateGetLines() {
    context.checking(new Expectations() {{
      exactly(1).of(cookieFile).getLines();
      will(returnValue(lines));
    }});
  }

}
