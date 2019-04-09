package validation;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests of IsBooleanValidator that checks that a String as a value of true or false")
public class IsBooleanValidatorTest {

    private static final Validator validator;

    static {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }

    private static void printError(
            ConstraintViolation<IsBooleanValidatorTestBean> violation) {
        System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "  true  ", "TRUE", "True", " TRUE ", " True    ", "TrUe", "false", "False", "FalSe", " False   ", " FALSE   "})
    @DisplayName("If a String attribute as a value which is equals with case ignored when trimmed to true or false, then the attribute is valid")
    public void validate_attribute_that_is_ok(String attributeValue) {
        IsBooleanValidatorTestBean isBooleanValidatorTestBean = new IsBooleanValidatorTestBean(attributeValue);
        Set<ConstraintViolation<IsBooleanValidatorTestBean>> violations = validator.validate(isBooleanValidatorTestBean);
        violations.stream().forEach(IsBooleanValidatorTest::printError);
        assertEquals(violations.size(), 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"vrai", "faux", "oui", "non", "yes", "no", "toto", "titi", "tata", "portnawak", "", "    ", "T", "F", "O", "N", "Y"})
    @DisplayName("If a String attribute as a value which is not equals with case ignored when trimmed to true or false, then the attribute is invalid")
    public void validate_attribute_that_is_ko(String attributeValue) {
        IsBooleanValidatorTestBean isBooleanValidatorTestBean = new IsBooleanValidatorTestBean(attributeValue);
        Set<ConstraintViolation<IsBooleanValidatorTestBean>> violations = validator.validate(isBooleanValidatorTestBean);
        violations.stream().forEach(IsBooleanValidatorTest::printError);
        assertEquals(violations.size(), 1);
    }
}

@AllArgsConstructor
class IsBooleanValidatorTestBean {
    @IsBoolean
    private String myBoolean;
}
