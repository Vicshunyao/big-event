package org.kingsy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.kingsy.anno.State;

public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        提供校验规则
//        String value = null;
        if(value ==null){
            return false;
        }
        if(value.equals("已发布")||value.equals("草稿")){
            return true;


        }
        return false;
    }
}
