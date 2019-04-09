package validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsBooleanValidator implements ConstraintValidator<IsBoolean, String> {

    @Override
    public void initialize(IsBoolean isBooleanValue) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        String s = value.trim();
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false");
    }
}
