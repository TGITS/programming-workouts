/**
 *
 */
package tgits.temperatures.model;

import lombok.Data;
import lombok.NonNull;
import tgits.temperatures.utils.BigDecimalUtilities;

import java.math.BigDecimal;

/**
 * @author cvaudry
 */
@Data
public class Temperature {

    @NonNull
    private final BigDecimal value;
    @NonNull
    private final TemperatureUnit unit;

    public Temperature(String value, TemperatureUnit unit) {
        this.unit = unit;
        this.value = BigDecimalUtilities.createBigDecimal(value);
    }

}


