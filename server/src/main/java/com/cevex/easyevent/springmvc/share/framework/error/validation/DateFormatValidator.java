package com.cevex.easyevent.springmvc.share.framework.error.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateFormatValidator implements ConstraintValidator<DateFormatConstraint, Date> {

    @Override
    public void initialize(DateFormatConstraint contactNumber) {
    }

    @Override
    public boolean isValid(Date date,
                           ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(date);
        return false;
    }

}