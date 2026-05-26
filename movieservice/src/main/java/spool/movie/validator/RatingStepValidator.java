package spool.movie.validator;

import spool.movie.annotation.IsHalfStep;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingStepValidator implements ConstraintValidator<IsHalfStep, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context){
        if(value==null) return true;
        return (value*2)%1 == 0;
    }
}
