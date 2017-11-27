package tgits.temperatures.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalUtilities {

    private static final MathContext MC = MathContext.DECIMAL128;
    public static final BigDecimal FIVE = new BigDecimal("5", MC);
    public static final BigDecimal NINE = new BigDecimal("9", MC);
    public static final BigDecimal THIRTY_TWO = new BigDecimal("32", MC);
    public static final BigDecimal ABSOLUTE_ZERO_CELSIUS = new BigDecimal("273.15", MC).negate();
    public static final BigDecimal ABSOLUTE_ZERO_FAHRENHEIT = new BigDecimal("459.67", MC).negate();
    private static final int SCALE = 3;

    private BigDecimalUtilities() {
        super();
    }

    public static BigDecimal createBigDecimal(String value) {
        return new BigDecimal(value, MC).setScale(SCALE, RoundingMode.HALF_EVEN);
    }
}
