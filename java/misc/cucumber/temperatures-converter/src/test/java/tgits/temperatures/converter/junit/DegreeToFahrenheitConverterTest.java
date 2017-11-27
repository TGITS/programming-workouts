/**
 *
 */
package tgits.temperatures.converter.junit;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import tgits.temperatures.converter.Converter;
import tgits.temperatures.converter.DegreeToFahrenheitConverter;
import tgits.temperatures.model.Temperature;
import tgits.temperatures.model.TemperatureUnit;

import static org.junit.Assert.assertThat;

/**
 * @author cvaudry
 */
public class DegreeToFahrenheitConverterTest {

    @Test
    public void testDegreeToFahrenheitConverter() {
        Temperature[] providedInput = new Temperature[]{
                new Temperature("-273.15", TemperatureUnit.DEGREE)
                , new Temperature("-40", TemperatureUnit.DEGREE)
                , new Temperature("-17.778", TemperatureUnit.DEGREE)
                , new Temperature("-15", TemperatureUnit.DEGREE)
                , new Temperature("0", TemperatureUnit.DEGREE)
                , new Temperature("10", TemperatureUnit.DEGREE)
                , new Temperature("37", TemperatureUnit.DEGREE)
                , new Temperature("38", TemperatureUnit.DEGREE)
                , new Temperature("99.975", TemperatureUnit.DEGREE)
                , new Temperature("100", TemperatureUnit.DEGREE)
        };
        Temperature[] expectedResult = new Temperature[]{
                new Temperature("-459.67", TemperatureUnit.FAHRENHEIT)
                , new Temperature("-40", TemperatureUnit.FAHRENHEIT)
                , new Temperature("0", TemperatureUnit.FAHRENHEIT)
                , new Temperature("5", TemperatureUnit.FAHRENHEIT)
                , new Temperature("32", TemperatureUnit.FAHRENHEIT)
                , new Temperature("50", TemperatureUnit.FAHRENHEIT)
                , new Temperature("98.6", TemperatureUnit.FAHRENHEIT)
                , new Temperature("100.400", TemperatureUnit.FAHRENHEIT)
                , new Temperature("211.955", TemperatureUnit.FAHRENHEIT)
                , new Temperature("212", TemperatureUnit.FAHRENHEIT)
        };

        Converter converter = new DegreeToFahrenheitConverter();

        for (int i = 0; i < providedInput.length; i++) {
            System.out.println("Input value : " + providedInput[i].toString() + " - Expected result : " + expectedResult[i].toString() + " - Converted value : " + converter.convert(providedInput[i]).toString());
            assertThat(converter.convert(providedInput[i]), IsEqual.equalTo(expectedResult[i]));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDegreeToFahrenheitConverterWithKelvinTemperature() {
        Temperature temperatureInKelvin = new Temperature("50", TemperatureUnit.KELVIN);
        Converter converter = new DegreeToFahrenheitConverter();
        Temperature result = converter.convert(temperatureInKelvin);
    }

}
