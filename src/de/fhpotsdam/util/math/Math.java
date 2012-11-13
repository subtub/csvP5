package de.fhpotsdam.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Math {
	public static float round(float value, int precision) {
	    if (precision <= 0) {
	        throw new IllegalArgumentException("Precision cannot be zero or less.");
	    }
	    BigDecimal decimal = BigDecimal.valueOf(value);
	    return decimal.setScale(precision, RoundingMode.FLOOR).floatValue();
	}
}
