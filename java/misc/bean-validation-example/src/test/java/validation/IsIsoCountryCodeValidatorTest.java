package validation;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests of IsIsoCountryCodeValidator that checks that a String as a value that is a correct ISO 3166-1 alpha-2 code country")
public class IsIsoCountryCodeValidatorTest {

    private static final Validator validator;

    static {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }

    private static void printError(
            ConstraintViolation<IsIsoCountryCodeValidatorTestBean> violation) {
        System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/iso-country-code-list.csv", numLinesToSkip = 0)
    @DisplayName("If a String attribute as a value which is equals with case ignored when trimmed to true or false, then the attribute is valid")
    public void validate_attribute_that_is_ok(String attributeValue) {
        IsIsoCountryCodeValidatorTestBean isIsoCountryCodeValidatorTestBean = new IsIsoCountryCodeValidatorTestBean(attributeValue);
        Set<ConstraintViolation<IsIsoCountryCodeValidatorTestBean>> violations = validator.validate(isIsoCountryCodeValidatorTestBean);
        violations.stream().forEach(IsIsoCountryCodeValidatorTest::printError);
        assertEquals(violations.size(), 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"fr", "be", "tata", "titi", "toto", "", "    "})
    @DisplayName("If a String attribute as a value which is not equals with case ignored when trimmed to true or false, then the attribute is invalid")
    public void validate_attribute_that_is_ko(String attributeValue) {
        IsIsoCountryCodeValidatorTestBean isIsoCountryCodeValidatorTestBean = new IsIsoCountryCodeValidatorTestBean(attributeValue);
        Set<ConstraintViolation<IsIsoCountryCodeValidatorTestBean>> violations = validator.validate(isIsoCountryCodeValidatorTestBean);
        violations.stream().forEach(IsIsoCountryCodeValidatorTest::printError);
        assertEquals(violations.size(), 1);
    }
}

@AllArgsConstructor
class IsIsoCountryCodeValidatorTestBean {
    @IsIsoCountryCode
    private String codeCountry;
}