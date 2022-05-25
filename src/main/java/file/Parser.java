package file;

public interface Parser<E extends Entry> {

  String setRule(String rule);

  E parse(String line);
}
