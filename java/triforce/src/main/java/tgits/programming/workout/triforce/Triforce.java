package tgits.programming.workout.triforce;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Triforce 
{
	private Triforce(){
    	super();
    }
	
    public static void main( String[] args )
    {	
    	drawTriForce(createTriforce(1));
    	System.out.println("");
    	drawTriForce(createTriforce(2));
    	System.out.println("");
    	drawTriForce(createTriforce(3));    	
    }
    
    public static String center(String centralPattern, int width){
    	if (centralPattern == null) {
    		throw new IllegalArgumentException("The provided String must not be null");
    	}
    	
    	if (width <= 0) {
    		throw new IllegalArgumentException("The provided integer must be strictly positive");
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	int padding = (width - centralPattern.length()) / 2 ;
    	pad(sb, padding, ' ');
    	sb.append(centralPattern);
    	pad(sb, padding, ' ');
    	return sb.toString();
    }
   
    public static void pad(StringBuilder sb, int padding, char paddingChar){
    	for(int i = 0; i < padding; i++){
    		sb.append(paddingChar);
    	}
    }
    
    public static int calculateNumberOfCharactersForALine(int seed) {
    	if (seed <= 0) {
    		throw new IllegalArgumentException("The provided integer must be strictly positive");
    	}
    	return 2 * seed - 1;
    }
    
    public static String createStringPattern(char base, int repetition){
    	if (repetition <= 0) {
    		throw new IllegalArgumentException("The provided integer must be strictly positive");
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < repetition; i++){
    		sb.append(base);
    	}
    	return sb.toString();
    }
    
    public static int calculateMaxWidthForSingleTriangle(int height){
    	if (height <= 0) {
    		throw new IllegalArgumentException("The provided integer must be strictly positive");
    	}
    	
    	return (2 * height - 1) * 2 + 1;
    }
    
    public static int calculateMaxWidthForDuplicateTriangle(int height){
    	if (height <= 0) {
    		throw new IllegalArgumentException("The provided integer must be strictly positive");
    	}
    	
    	return (2 * height - 1);
    }
    
    public static List<String> createTriforce(int height) {
    	List<String> lines = new ArrayList<>();
    	int maxWidth = calculateMaxWidthForSingleTriangle(height);
    	String line;
    	for(int i=1; i<=height; i++) {
    		line = center(createStringPattern('*', calculateNumberOfCharactersForALine(i)),maxWidth);
    		lines.add(line);
    	}
    	maxWidth = calculateMaxWidthForDuplicateTriangle(height);
    	for(int i=1; i<=height; i++) {
    		line = center(createStringPattern('*', calculateNumberOfCharactersForALine(i)),maxWidth);
    		lines.add(line + " " + line);
    	}
    	return lines;
    }
    
    public static void drawTriForce(List<String> lines) {
    	for(String line:lines){
    		System.out.println(line);
    	}
    }
}
