package com.example.ShiroTest.SpringBoot.Core.Common;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String record() default "";
    String type() default "";
}
