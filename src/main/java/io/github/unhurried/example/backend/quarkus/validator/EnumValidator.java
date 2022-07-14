package io.github.unhurried.example.backend.quarkus.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumString, String> {
    private Class<? extends Enum<?>> enumeration;

    @Override
    public void initialize(EnumString annotation) {
        this.enumeration = annotation.enumeration();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (var e: enumeration.getEnumConstants()) {
            if (e.toString().equals(value)) return true;
        }
        return false;
    }
}
