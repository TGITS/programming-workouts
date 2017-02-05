package tgits.programming.workout.triforce;

import org.junit.Assert;
import org.junit.Test;

import tgits.programming.workout.triforce.Triforce;

public class TriforceTest 
{
	@Test
	public void testPad() {
	   String expected = "  *";
	   char paddingChar = ' ';
	   int padding = 2;
	   StringBuilder sb = new StringBuilder();
	   Triforce.pad(sb, padding, paddingChar);
	   sb.append('*');
	   String result = sb.toString();
	   Assert.assertEquals(expected, result);
	}
	 
    @Test
    public void testCenter() {
    	String centralPattern = "*";
    	String expectedResult = " * ";
        Assert.assertEquals(expectedResult, Triforce.center(centralPattern,3));
        centralPattern = "***";
    	expectedResult = "  ***  ";
        Assert.assertEquals(expectedResult, Triforce.center(centralPattern,7));
        centralPattern = "***";
    	expectedResult = "   ***   ";
        Assert.assertEquals(expectedResult, Triforce.center(centralPattern,9));
        centralPattern = "**";
    	expectedResult = "  **  ";
        Assert.assertEquals(expectedResult, Triforce.center(centralPattern,6));
    }
    
    @Test
    public void testCalculateNumberOfCharactersForALine() {
    	int seed = 1;
    	int expected = 1;
    	Assert.assertEquals(expected, Triforce.calculateNumberOfCharactersForALine(seed));
    	seed = 2;
    	expected = 3;
    	Assert.assertEquals(expected, Triforce.calculateNumberOfCharactersForALine(seed));
    	seed = 10;
    	expected = 19;
    	Assert.assertEquals(expected, Triforce.calculateNumberOfCharactersForALine(seed));
    }
    
    @Test
    public void testCalculateMaxWidth() {
    	int height = 1;
    	int expected = 3;
    	Assert.assertEquals(expected, Triforce.calculateMaxWidthForSingleTriangle(height));
    	height = 2;
    	expected = 7;
    	Assert.assertEquals(expected, Triforce.calculateMaxWidthForSingleTriangle(height));
    }
    
    @Test
    public void testCreateTriforce() {
    	
    }
}
