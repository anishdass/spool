package spool.movie.annotation;

import spool.movie.validator.RatingStepValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RatingStepValidator.class)
public @interface IsHalfStep {
    String message() default "Ratings must be multiple of 0.5";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
