/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhubacubi.jarhalla.client.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 *
 * @author rugi
 */
public class ClassInfoUtil {
    
    private Constructor[] constructors;
    private Method[] methods;
    
    public ClassInfoUtil(){
        super();
    }

    /**
     * @return the constructors
     */
    public Constructor[] getConstructors() {
        return constructors;
    }

    /**
     * @param constructors the constructors to set
     */
    public void setConstructors(Constructor[] constructors) {
        this.constructors = constructors;
    }

    /**
     * @return the methods
     */
    public Method[] getMethods() {
        return methods;
    }

    /**
     * @param methods the methods to set
     */
    public void setMethods(Method[] methods) {
        this.methods = methods;
    }
}
