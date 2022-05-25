import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class MainTest {

  @Rule
   public JUnitRuleMockery context = new JUnitRuleMockery();

  @Test
  public void mainCallsAppropriateStrategyToFindFrequencyTest() {
    // I haven't been able to figure out why my java doesn't recognise the dependencies introduced
    // by the xml file.
  }
}