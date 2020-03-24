package com.li.missyou.validators;

import com.li.missyou.dto.PersionDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersionDTO> {
    private int min;
    private int max;
    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(PersionDTO persionDTO, ConstraintValidatorContext context) {
        String password1 = persionDTO.getPassword1();
        String password2 = persionDTO.getPassword2();
        if (password1.length() < min || password1.length() > max) {
            return false;
        }
        boolean match = password1.equals(password2);
        return match;
    }
}
