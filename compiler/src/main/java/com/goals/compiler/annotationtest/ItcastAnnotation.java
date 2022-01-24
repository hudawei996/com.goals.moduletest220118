package com.goals.compiler.annotationtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解保留时机
@Retention(RetentionPolicy.RUNTIME)
//注解应该在的位置
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ItcastAnnotation {
    String color() default  "blue";
    String value() ;
    int[] arrayAttr() default {1,2,3};
    //新建一个注解的对象，并赋值@MetaAnnotation("goals")
    MetaAnnotation metaAnnotation() default @MetaAnnotation("goals");
}
