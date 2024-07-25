package com.example.ShiroTest.SpringBoot.Core.Common;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NoAuth {
}
