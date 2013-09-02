/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhubacubi.jarhalla.client.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author rugi
 */
public class ClassUtil {

    public static ClassInfoUtil getClassInfoFromClassInJar(String pathToJar, String className_) throws IOException, ClassNotFoundException {
        ClassInfoUtil res = null;
        JarFile jarFile = new JarFile(pathToJar);
        Enumeration e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + pathToJar + "!/")};
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = (JarEntry) e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            String className = je.getName().substring(0, je.getName().length() - 6);

            if (className.equals(className_.substring(0, className_.length() - 6))) {

                res = new ClassInfoUtil();
                className = className.replace('/', '.');
                Class c = cl.loadClass(className);

                res.setMethods(c.getDeclaredMethods());
                res.setConstructors(c.getConstructors());
            }
        }
        return res;
    }

  
}
