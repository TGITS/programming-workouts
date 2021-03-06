package validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsBooleanValidator.class)
public @interface IsBoolean {
    String message () default "must be a valid boolean (true or false value). Found: ${validatedValue}";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}
