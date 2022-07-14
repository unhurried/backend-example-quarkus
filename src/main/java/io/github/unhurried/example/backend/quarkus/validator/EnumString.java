package io.github.unhurried.example.backend.quarkus.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumString {
    Class<? extends Enum<?>> enumeration();
    String message() default "must match one of enumerated values";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
