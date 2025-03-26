//-----------------------------------------------------------------------------
//This is not a real test. 
//It's just a simple example to check if JUnit is properly set up and working.
//-----------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testAddition() {
        int sum = 2 + 3;
        assertEquals(5, sum, "2 + 3 should equal 5");
    }
}
