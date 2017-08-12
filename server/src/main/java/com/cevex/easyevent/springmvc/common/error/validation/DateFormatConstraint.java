package com.cevex.easyevent.springmvc.common.error.validation;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidator.class)
public @interface DateFormatConstraint {

    String message() default "Invalid date format";
}
