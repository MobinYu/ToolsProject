package com.study.reflact;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CodeToName {
    /**
     * 字典类型
     */
    String type() default "";

    /**
     * 需要赋值的字段
     */
    String field() default "";
}
