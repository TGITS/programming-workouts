package tgits.temperatures.converter;

import tgits.temperatures.model.Temperature;
import tgits.temperatures.model.TemperatureUnit;

public interface Converter {
    public boolean validateInput(Temperature t);
    public TemperatureUnit expectedUnit();
    public Temperature convert(Temperature t);
}
