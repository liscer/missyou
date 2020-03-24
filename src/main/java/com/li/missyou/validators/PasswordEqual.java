package com.li.missyou.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordEqual {
    int min() default 4;
    int max() default 6;
    String message() default "passwoeds are not equal";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
