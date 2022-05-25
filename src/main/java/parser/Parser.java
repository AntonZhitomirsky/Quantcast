package parser;

import entry.TimeStampedCookieEntry;

public interface Parser<E extends TimeStampedCookieEntry> {

  /**
   * A function returning a regex pattern which matches on strings to extract information separated
   * by an undefined number of punctuation delimiters.
   *
   * @param rule a string pattern representation
   * @return a regex pattern representation of the input
   */
  String setRule(String rule);


  /**
   * A function returning an Entry as a result of applying and extracting independent group
   * matches from a given line input
   *
   * @param line a string line to be parsed by the rule
   * @return Entry from applying parser rule on input
   */
  E parse(String line);
}
