package bowling;


import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBowling {
	private Bowling bowling;
	@Before
	public void setUp(){
		bowling = new Bowling();
	}
	@Test
	public void testFullStrike() throws IllegalCaracterException, SizeOfResultException, SumFrameTooHighException, FrameIsNullException {
		bowling.game("X X X X X X X X X X X X");
		assertEquals(bowling.score(), 300);
	}
	@Test
	public void testHalfMiss() throws IllegalCaracterException, SizeOfResultException, SumFrameTooHighException, FrameIsNullException {
		bowling.game("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-");
		assertEquals(bowling.score(), 90);
	}
	@Test
	public void testFullMiss() throws IllegalCaracterException, SizeOfResultException, SumFrameTooHighException, FrameIsNullException {
		bowling.game("-- -- -- -- -- -- -- -- -- --");
		assertEquals(bowling.score(), 0);
	}
	@Test
	public void testFullSpare() throws IllegalCaracterException, SizeOfResultException, SumFrameTooHighException, FrameIsNullException {
		bowling.game("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5");
		assertEquals(bowling.score(), 150);
	}
	@Test
	public void testClassic() throws IllegalCaracterException, SizeOfResultException, SumFrameTooHighException, FrameIsNullException {
		bowling.game("X 9/ 52 72 X 7- X 9- 8/ 9/X");
		assertEquals(bowling.score(), 142);
	}
	@Test
	public void testClassic2() throws IllegalCaracterException, SizeOfResultException, SumFrameTooHighException, FrameIsNullException {
		bowling.game("X 2/ 52 3- X 72 X 81 8/ X 5-");
		assertEquals(bowling.score(), 136);
	}
	@Test
	public void testClassic3() throws IllegalCaracterException, SizeOfResultException, SumFrameTooHighException, FrameIsNullException {
		bowling.game("X 9- 22 72 2- 7- X 9- 8/ -4");
		assertEquals(bowling.score(), 92);
	}
	@Test
	public void testErrorLenght() throws IllegalCaracterException, SizeOfResultException {
		try {
			bowling.game("5/ 5/");
		} 
		catch (SizeOfResultException e) {
		    String expectedMessage = "The number of frame is False";
		    assertEquals( "Exception message must be correct", expectedMessage, e.getMessage() );
		}  
	}
	@Test
	public void testErrorCharacter() throws IllegalCaracterException, SizeOfResultException {
		try {
			bowling.game("5/ c/ zd g/ 5/ 5/ f/ 5r 5/ 5/5");
		} 
		catch (IllegalCaracterException e) {
		    String expectedMessage = "The only authorized caracters are 1,2,3,4,5,6,7,8,9,-,/,X";
		    assertEquals( "Exception message must be correct", expectedMessage, e.getMessage() );
		}   
	}
	@Test
	public void testSumInvalid() throws IllegalCaracterException, SizeOfResultException, SumFrameTooHighException, FrameIsNullException {
		try {
			bowling.game("5/ 5/ 5/ 5/ 5/ 58 5/ 5/ 5/ 5/5");
			bowling.score();
		} 
		catch (SumFrameTooHighException e) {
		    String expectedMessage = "The calculated score whithout Strike and spare is greater than 9";
		    assertEquals( "Exception message must be correct", expectedMessage, e.getMessage());
		} 
	}
	@Test
	public void testEmptyFrame() throws IllegalCaracterException, SizeOfResultException, SumFrameTooHighException, FrameIsNullException {
		try {
			bowling.score();
		} 
		catch (FrameIsNullException e) {
		    String expectedMessage = "The Line is Empty";
		    assertEquals( "Exception message must be correct", expectedMessage, e.getMessage());
		} 
	}
}
