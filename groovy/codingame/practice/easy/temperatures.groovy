input = new Scanner(System.in);

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

n = input.nextInt() // the number of temperatures to analyse
input.nextLine()
temps = input.nextLine() // the n temperatures expressed as integers ranging from -273 to 5526

// Write an answer using println
// To debug: System.err << "Debug messages...\n"

System.err << "n : " + n + "\n"
System.err << "temps : " + temps + "\n"

def result = 0
if(n > 0) {
    def temperatures = temps.tokenize(" ").collect { Integer.parseInt(it) }
    def positive_temperatures = temperatures.findAll { it >= 0 };
    def min_positive_temperature = Integer.MAX_VALUE
    if (!positive_temperatures.empty) {
        min_positive_temperature = positive_temperatures.min()
    }
    System.err << "min_positive_temperature : " + min_positive_temperature

    def negative_temperatures = temperatures.findAll { it < 0 }
    def max_negative_temperature = Integer.MIN_VALUE + 1
    if (!negative_temperatures.empty) {
        max_negative_temperature = negative_temperatures.max()
    }
    System.err <<  "max_negative_temperature : " + max_negative_temperature

    if (min_positive_temperature <= Math.abs(max_negative_temperature)) {
        result = min_positive_temperature
    }
    else {
        result = max_negative_temperature
    }
}

println result
