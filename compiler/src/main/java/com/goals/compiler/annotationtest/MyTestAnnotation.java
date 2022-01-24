package com.goals.compiler.annotationtest;

import java.lang.reflect.Method;
import java.util.Arrays;

@ItcastAnnotation(metaAnnotation = @MetaAnnotation("zxx"), color = "red",value = "1",arrayAttr = {2,3,4})
public class MyTestAnnotation {

    @ItcastAnnotation(value = "2")
    public static void main(String[] args) {
        System.out.println(MyTestAnnotation.class.getSimpleName());

        System.out.println("============");

        if (MyTestAnnotation.class.isAnnotationPresent(ItcastAnnotation.class)){
            ItcastAnnotation itcastAnnotation
                    = MyTestAnnotation.class.getAnnotation(ItcastAnnotation.class);

            System.out.println(itcastAnnotation);
            System.out.println(itcastAnnotation.color());
            System.out.println(itcastAnnotation.value());
            System.out.println(Arrays.toString(itcastAnnotation.arrayAttr()));
            System.out.println(itcastAnnotation.metaAnnotation());
            System.out.println(itcastAnnotation.metaAnnotation().value());
        }

        System.out.println("============");

        Method[] methods = MyTestAnnotation.class.getMethods();
        for (Method method:methods){
            if (method.isAnnotationPresent(ItcastAnnotation.class)){

               ItcastAnnotation itcastAnnotation = method.getAnnotation(ItcastAnnotation.class);
                System.out.println(itcastAnnotation);
                System.out.println(itcastAnnotation.color());
                System.out.println(itcastAnnotation.value());
                System.out.println(Arrays.toString(itcastAnnotation.arrayAttr()));
                System.out.println(itcastAnnotation.metaAnnotation());
                System.out.println(itcastAnnotation.metaAnnotation().value());
            }
        }
    }
}
