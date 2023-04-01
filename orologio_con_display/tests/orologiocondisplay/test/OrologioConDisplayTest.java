package orologiocondisplay.test;

import static org.junit.Assert.assertTrue;
import java.time.LocalTime;
import org.junit.jupiter.api.*;
import orologiocondisplay.OrologioConDisplay;

public class OrologioConDisplayTest {

	private OrologioConDisplay myClock;
	
	@BeforeEach
	public void setUp() {
		myClock = new OrologioConDisplay(LocalTime.of(0, 0, 0));
	}
	
	@Test
	public void test_1_minute() {
		OrologioConDisplay clock2 = new OrologioConDisplay(LocalTime.of(0, 1, 0));
		for (int i = 0; i < 60; i++) {
			myClock.tic();
		}
		assertTrue(clock2.equals(myClock));
	}
	
	@Test
	public void test_59_seconds() {
		OrologioConDisplay clock2 = new OrologioConDisplay(LocalTime.of(0, 0, 59));
		for (int i = 0; i < 59; i++) {
			myClock.tic();
		}
		assertTrue(clock2.equals(myClock));
	}
	
}
