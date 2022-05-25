package file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser<E extends Entry> implements Parser<E> {

  private String rule;

  public FileParser() {
  }

  @Override
  public String setRule(String rule) {
    // convert rule into regex to parse
    // operates on the assumption that the rule is only ever going to be cookie(delimiter)timestamp
    String regex = "([a-zA-Z\\d]+)"; // regex to separate tokens from punctuation delimiters
    Matcher m = Pattern.compile(regex).matcher(rule); // applies regex to rule
    this.rule = m.replaceAll("(\\.*)");
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
    // no match found, fine is not well-defined
    return null;
  }

  public String getRule() {
    return rule;
  }

}