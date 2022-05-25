import entry.TimeStampedCookieEntry;
import file.CookieFile;
import file.CookieFileInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import parser.StringParser;
import search.ConcurrentSearch;

public class Main {

  public static void main(String[] args) throws ParseException {
    // minimal checking for command line inputs!
    String file = args[0];
    assert args[1].equals("-d");
    String date = args[2];

    CookieFileInterface<TimeStampedCookieEntry> cookieFile = new CookieFile<>(new StringParser<>(),
        file);
    ConcurrentSearch search = new ConcurrentSearch(cookieFile);
    List<String> cookies = search.getMostFrequentCookie(
        new SimpleDateFormat("yyyy-MM-dd").parse(date));

    // pretty print output
    cookies.forEach(System.out::println);
  }
}
