package validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Locale;

public class IsIsoCountryCodeValidator implements ConstraintValidator<IsIsoCountryCode, String> {

    @Override
    public void initialize(IsIsoCountryCode isIsoCountryCodeValue) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return Arrays.stream(Locale.getISOCountries()).anyMatch(s -> s.equals(value));
    }
}
