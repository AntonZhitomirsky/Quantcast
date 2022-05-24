package frequency;

import org.junit.Test;

public class RunnableCounterTest {

  public final RunnableCounter rc = new RunnableCounter();

  @Test
  public void canCallRun() {
    rc.run();
    // test should pass vacuously
  }


}