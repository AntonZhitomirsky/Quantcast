package parser;

import entry.TimeStampedCookieEntry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser<E extends TimeStampedCookieEntry> implements Parser<E> {

  private String rule;

  @Override
  public String setRule(String rule) {
    String regex = "([a-zA-Z\\d]+)";                  // regex to separate tokens from punctuation delimiters
    Matcher m = Pattern.compile(regex).matcher(rule); // applies regex to rule
    this.rule = m.replaceAll("(\\.*)");     // replaces all matching groups with (\\.*)
    return this.rule;
  }

  @Override
  public E parse(String line) {
    // applies the parsing pattern and returns an entry
    // for now, since assumption: cookie,timestamp always return a TimeStampedCookieEntry
    Matcher m = Pattern.compile(rule).matcher(line);
    if (m.matches()) {
      return (E) new TimeStampedCookieEntry(m.group(1), m.group(2));
    }
    // no match found, line is not well-defined/ doesn't mach the pattern
    return null;
  }
}