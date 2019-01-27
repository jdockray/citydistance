package joeldockray.demos.awslambda.wpdistance;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LambdaFunctionHandlerTest2 {
	private static final int STRESS_TEST_REPEAT_NUMBER = 50000; 
	
	@Test
    public void checkErrorPackaging() {
        SiteSearchRequest request = new SiteSearchRequest("0.0", "0.0", "-1");
		try {
			(new LambdaFunctionHandler()).handleRequest(request, new TestContext());
			Assertions.fail("RuntimeException not thrown");
		}
		catch (RuntimeException ex)
		{
			Assertions.assertThrows(InvalidResultNumberLimitException.class, () -> { throw ex.getCause(); } );
		}
	}	
	
	@Test
    public void stressTest() {
		assertTimeout(Duration.ofSeconds(5), () ->
		{
			int hash = 0;
			for (int i = 0; i < STRESS_TEST_REPEAT_NUMBER; i++)
			{
				LambdaFunctionHandlerTest handlerTest = new LambdaFunctionHandlerTest(); 
				handlerTest.setUp();
				hash ^= handlerTest.results.hashCode();			
			}
			System.out.println(hash);
		});
	}	
	
	
}
