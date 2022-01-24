package com.goals.compiler.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 字符串、集合判空工具
 */
public class ProcessorUtils {
    public static boolean isEmpty(CharSequence charSequence){
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(final Map<?,?> map){
        return map == null || map.isEmpty();
    }
}
