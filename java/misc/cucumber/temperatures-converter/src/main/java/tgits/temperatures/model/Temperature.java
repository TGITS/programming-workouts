/**
 *
 */
package tgits.temperatures.model;

import tgits.temperatures.utils.BigDecimalUtilities;

import java.math.BigDecimal;

/**
 * @author cvaudry
 */
public class Temperature {

    private final BigDecimal value;
    private final TemperatureUnit unit;

    public Temperature(String value, TemperatureUnit unit) {
        this.unit = unit;
        this.value = BigDecimalUtilities.createBigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    public TemperatureUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temperature that = (Temperature) o;

        if (!value.equals(that.value)) return false;
        return unit == that.unit;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + unit.hashCode();
        return result;
    }
}


