package validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsIsoCountryCodeValidator.class)
public @interface IsIsoCountryCode {
    String message () default "must be a valid Iso Code Country (ISO 3166-1 alpha-2). Found: ${validatedValue}";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}

