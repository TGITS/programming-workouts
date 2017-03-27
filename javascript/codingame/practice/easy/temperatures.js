/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

var n = parseInt(readline()); // the number of temperatures to analyse
var temps = readline(); // the n temperatures expressed as integers ranging from -273 to 5526

printErr('Number of temperatures : ' + n);
printErr('Temperatures list : ' + temps);

function sortNumber(a,b) {
    return a - b;
}

var numberOfValues = parseInt(n);
var result = '0';

if (numberOfValues >0 ) {
    var arrayOfTemperatures = temps.split(" ");
    var temperatures = [];
    var temperaturesAbsolute = [];
    var temperaturesByAbsolute = [];

    for(var i=0; i < arrayOfTemperatures.length; i++) {
        temperatures[i] = parseInt(arrayOfTemperatures[i]);
        temperaturesAbsolute[i] = Math.abs(temperatures[i]);
        printErr('Each temperatures: ' + arrayOfTemperatures[i] + ' # ' + temperatures[i] + ' # ' + temperaturesAbsolute[i]);
    }

    temperatures.sort(sortNumber);

    for(var i=0; i < temperatures.length; i++) {
        temperaturesByAbsolute[Math.abs(temperatures[i])+''] = temperatures[i];
        printErr('temperatures[' + i + ']: ' + temperatures[i]);
        printErr('temperaturesByAbsolute[' + Math.abs(temperatures[i]) + ']: ' + temperaturesByAbsolute[Math.abs(temperatures[i])+'']);
    }

    temperaturesAbsolute.sort(sortNumber);

    for(var i=0; i < temperaturesAbsolute.length; i++) {
        printErr('temperaturesAbsolute[' + i + ']: ' + temperaturesAbsolute[i] + '');
    }

    result = temperaturesByAbsolute[temperaturesAbsolute[0] + ''];
}

// Write an action using print()
// To debug: printErr('Debug messages...');

print(result);
