package com.cevex.easyevent.springmvc.share.framework.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Manipulate class declare as generic.
 * - Instanciate parameter class
 * - Retrieve parameter type
 */
public class GenericClassUtils {

    /**
     * Given a generic class, return the class parameter at given position
     *
     * @param genericClass      - The generic class we cant to extract parameter
     * @param parameterPosition - The parameter position
     * @return The type of the
     */
    public static Type getParameterClass(Class genericClass, int parameterPosition) {
        return ((ParameterizedType) genericClass.getGenericSuperclass()).getActualTypeArguments()[parameterPosition];
    }

}
