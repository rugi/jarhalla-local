/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.util;

import java.io.File;

/**
 *
 * @author rugi
 */
public class FileUtil {
    private static final String DirectoryName = ".jarhalla";
    
    public static String getUserHomeDirectory(){
        return System.getProperty("user.home").toString();
    }
    
    public static String getWorkDirectory(){
        //validamos
        String workDirectory = getUserHomeDirectory()+File.separatorChar+DirectoryName;
        File temp = new File(workDirectory);
        if(!temp.exists()){
            boolean res = temp.mkdir();
            if(!res){
                throw new RuntimeException("No se pudo crear la carpeta de trabajo");
            }
        }
        //boolean r = new File().
        return workDirectory;
    }
}
