package io.polybius.phonevalidator;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MobilePhoneNumberValidatorTest {

    private final MobilePhoneNumberValidator validator = new MobilePhoneNumberValidator();

    @Test
    public void validateLT() {
        ValidationResultDto result = validator.validate(List.of("+370(6)123 45-67"));
        assertEquals(List.of("+370(6)123 45-67"), result.validPhonesByCountry.get("LT"));
    }

    @Test
    public void invalidateLT() {
        ValidationResultDto result = validator.validate(List.of("+37091234567"));
        assertEquals(List.of("+37091234567"), result.invalidPhones);

        result = validator.validate(List.of("+3706123456"));
        assertEquals(List.of("+3706123456"), result.invalidPhones);

        result = validator.validate(List.of("+370(6123456"));
        assertEquals(List.of("+370(6123456"), result.invalidPhones);

        result = validator.validate(List.of("+370+6123+456"));
        assertEquals(List.of("+370+6123+456"), result.invalidPhones);

        result = validator.validate(List.of("370655555(("));
        assertEquals(List.of("370655555(("), result.invalidPhones);
    }

    @Test
    public void validateBE() {
        ValidationResultDto result = validator.validate(List.of("32456123456"));
        assertEquals(List.of("32456123456"), result.validPhonesByCountry.get("BE"));

        result = validator.validate(List.of("+324(5)612-34 56"));
        assertEquals(List.of("+324(5)612-34 56"), result.validPhonesByCountry.get("BE"));

        result = validator.validate(List.of("+3247 12-34 5(6)7"));
        assertEquals(List.of("+3247 12-34 5(6)7"), result.validPhonesByCountry.get("BE"));

        result = validator.validate(List.of("+324(5)612-34 56"));
        assertEquals(List.of("+324(5)612-34 56"), result.validPhonesByCountry.get("BE"));
    }

    @Test
    public void invalidateBE() {
        ValidationResultDto result = validator.validate(List.of("33456123456"));
        assertEquals(List.of("33456123456"), result.invalidPhones);

        result = validator.validate(List.of("+324561234560"));
        assertEquals(List.of("+324561234560"), result.invalidPhones);
    }

    @Test
    public void validateLV() {
        ValidationResultDto result = validator.validate(List.of("371(2)456-12 34"));
        assertEquals(List.of("371(2)456-12 34"), result.validPhonesByCountry.get("LV"));

        result = validator.validate(List.of("+37124561234"));
        assertEquals(List.of("+37124561234"), result.validPhonesByCountry.get("LV"));
    }

    @Test
    public void validateEE() {
        ValidationResultDto result = validator.validate(List.of("372-(5)-561 2345"));
        assertEquals(List.of("372-(5)-561 2345"), result.validPhonesByCountry.get("EE"));

        result = validator.validate(List.of("+372-(5)-561 2345"));
        assertEquals(List.of("+372-(5)-561 2345"), result.validPhonesByCountry.get("EE"));
    }
}