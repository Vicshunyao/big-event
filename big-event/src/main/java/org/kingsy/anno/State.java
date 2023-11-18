package org.kingsy.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.kingsy.validation.StateValidation;

import java.lang.annotation.*;

@Documented//元注释
@Constraint(
        validatedBy = {StateValidation.class}//指定提供校验规则的类
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface State {
//提供校验失败的信息
    String message() default "{state参数的值只能是已发布或草稿}";
//指定分组
    Class<?>[] groups() default {};
//负载，获取state的注释附加信息
    Class<? extends Payload>[] payload() default {};
}
