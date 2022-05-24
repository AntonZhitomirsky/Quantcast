import frequency.Cookie;
import frequency.FrequencyFinder;
import frequency.LinearSearch;
import java.io.File;
import java.util.List;

public class Main {

  private static File getFile(String filePath) {
    return new File(filePath);
  }

  public static void main(String[] args) {
    FrequencyFinder freq = new FrequencyFinder(getFile(args[0]), args[1], new LinearSearch());
    // run search through file - get intermediate representation
    List<Cookie> cookies = freq.getMostFrequentCookie();
    // return most frequent cookie(s)
    assert cookies != null;
    cookies.forEach(System.out::println);
  }
}
