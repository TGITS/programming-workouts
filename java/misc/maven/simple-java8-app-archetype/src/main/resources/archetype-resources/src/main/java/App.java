#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
    	List<Integer> numbers = new ArrayList<Integer>();
		for(int i=1; i <= 100; i++) {
			numbers.add(i);
		}

		FizzBuzz fizzbuzz = new FizzBuzz();
		System.out.println(fizzbuzz.transform(numbers));
    }
}
