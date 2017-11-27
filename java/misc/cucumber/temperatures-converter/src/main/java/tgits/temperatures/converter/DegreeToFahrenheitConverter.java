package tgits.temperatures.converter;

import tgits.temperatures.model.Temperature;
import tgits.temperatures.model.TemperatureUnit;
import tgits.temperatures.utils.BigDecimalUtilities;

import java.math.BigDecimal;

public class DegreeToFahrenheitConverter implements Converter {

    @Override
    public boolean validateInput(Temperature t) {
        return this.expectedUnit() == t.getUnit();
    }

    @Override
    public TemperatureUnit expectedUnit() {
        return TemperatureUnit.DEGREE;
    }

    @Override
    public Temperature convert(Temperature t) {
        if(!validateInput(t)){
            throw new IllegalArgumentException("You must provide a temperature of the expected unit");
        }

        BigDecimal result = t.getValue().multiply(BigDecimalUtilities.NINE).divide(BigDecimalUtilities.FIVE).add(BigDecimalUtilities.THIRTY_TWO);

        return new Temperature(result.toPlainString(),TemperatureUnit.FAHRENHEIT);
    }
}
