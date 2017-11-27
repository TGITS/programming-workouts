package tgits.temperatures.converter.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.IsEqual;
import tgits.temperatures.converter.Converter;
import tgits.temperatures.converter.DegreeToFahrenheitConverter;
import tgits.temperatures.model.Temperature;
import tgits.temperatures.model.TemperatureUnit;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DegreeToFahrenheitConverterSteps {

    private Converter converter;
    private Temperature[] providedInput = new Temperature[]{
        new Temperature("-273.15", TemperatureUnit.DEGREE)
                ,new Temperature("-40", TemperatureUnit.DEGREE)
                ,new Temperature("-17.778", TemperatureUnit.DEGREE)
                ,new Temperature("-15", TemperatureUnit.DEGREE)
                ,new Temperature("0", TemperatureUnit.DEGREE)
                ,new Temperature("10", TemperatureUnit.DEGREE)
                ,new Temperature("37", TemperatureUnit.DEGREE)
                ,new Temperature("38", TemperatureUnit.DEGREE)
                ,new Temperature("99.975", TemperatureUnit.DEGREE)
                ,new Temperature("100", TemperatureUnit.DEGREE)
    };

    Temperature[] expectedResult = new Temperature[]{
            new Temperature("-459.67", TemperatureUnit.FAHRENHEIT)
            ,new Temperature("-40", TemperatureUnit.FAHRENHEIT)
            ,new Temperature("0", TemperatureUnit.FAHRENHEIT)
            ,new Temperature("5", TemperatureUnit.FAHRENHEIT)
            ,new Temperature("32", TemperatureUnit.FAHRENHEIT)
            ,new Temperature("50", TemperatureUnit.FAHRENHEIT)
            ,new Temperature("98.6", TemperatureUnit.FAHRENHEIT)
            ,new Temperature("100.400", TemperatureUnit.FAHRENHEIT)
            ,new Temperature("211.955", TemperatureUnit.FAHRENHEIT)
            ,new Temperature("212", TemperatureUnit.FAHRENHEIT)
    };

    Temperature[] computedResult;

    Temperature temperatureInKelvin;
    boolean illegalArgumentExceptionCatched = false;

    @Given("^my converter exists$")
    public void myConverterExists() throws Throwable {
        converter = new DegreeToFahrenheitConverter();
    }

    @When("^I call my converter$")
    public void iCallMyConverter() throws Throwable {
        computedResult = new Temperature[providedInput.length];
        for (int i = 0; i < providedInput.length; i++) {
            computedResult[i] = converter.convert(providedInput[i]);
        }
    }

    @Then("^it should correctly convert the temperature$")
    public void itShouldCorrectlyConvertTheTemperature(){
        for (int i = 0; i < computedResult.length; i++) {
            System.out.println("Input value : " + providedInput[i].toString() + " - Expected result : " + expectedResult[i].toString() + " - Converted value : " + computedResult[i].toString());
            assertThat(computedResult[i], IsEqual.equalTo(expectedResult[i]));
        }
    }

    @When("^I call my converter on a temperature in Kelvin$")
    public void iCallMyConverterOnATemperatureInKelvin() throws Throwable {
        temperatureInKelvin = new Temperature("50", TemperatureUnit.KELVIN);
        try {
            converter.convert(temperatureInKelvin);
        }
        catch(IllegalArgumentException iae){
            illegalArgumentExceptionCatched = true;
            System.out.println("IllegalArgumentException catched");
        }
    }

    @Then("^it should raise an IllegalArgumentException$")
    public void itShouldRaiseAnIllegalArgumentException() throws Throwable {
        assertTrue(illegalArgumentExceptionCatched);
    }
}
