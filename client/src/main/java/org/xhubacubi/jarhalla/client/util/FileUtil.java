    /*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author rugi
 */
public class FileUtil {

    /**
     * 
     */
    private static final String DirectoryName = ".jarhalla";

    /**
     * 
     * @return 
     */
    public static String getUserHomeDirectory() {
        return System.getProperty("user.home").toString();
    }

    /**
     * 
     * @return 
     */
    public static String getWorkDirectory() {
        //validamos
        String workDirectory = getUserHomeDirectory() + File.separatorChar + DirectoryName;
        File temp = new File(workDirectory);
        if (!temp.exists()) {
            boolean res = temp.mkdir();
            if (!res) {
                throw new RuntimeException("No se pudo crear la carpeta de trabajo");
            }
        }
        //boolean r = new File().
        return workDirectory;
    }

    /**
     * 
     * @param fileName
     * @return 
     */
    public static boolean exist(String fileName) {
        String dirTarger = fileName;
        File temp = new File(dirTarger);
        boolean res = false;
        if (temp.exists()) {
            res = true;
        } 
        return res;
    }  
    
    /**
     * 
     * @param fileName
     * @return 
     */
    public static boolean existOrCreate(String fileName) {
        String dirTarger = fileName;
        File temp = new File(dirTarger);
        boolean res = false;
        if (!temp.exists()) {
            try {
                res = temp.createNewFile();
            } catch (IOException ex) {
                res = false;
            }
        } else {
            res = true;
        }
        return res;
    }

    
    /**
     * 
     * @param fileName
     * @param line
     * @return 
     */
    public static boolean appendFile(String fileName, String line) {
        boolean res = true;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(line);
            out.write("\n");
            out.close();
        } catch (IOException e) {
            res = false;
        }
        return res;
    }

    
    /**
     * 
     * @param fileName
     * @return 
     */
    public static boolean cleanFile(String fileName) {
        boolean res = true;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write("");            
            out.close();
        } catch (IOException e) {
            res = false;
        }
        return res;
    }  
    
    
    /**
     * 
     * @param fileName
     * @param lines
     * @return 
     */
    public static boolean appendFile(String fileName, List<Object> lines) {
        boolean res = true;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            Iterator it = lines.iterator();
            while(it.hasNext()){
                out.write(it.next().toString());
                out.write("\n");            
            }
            out.close();
        } catch (IOException e) {
            res = false;
        }
        return res;
    }    
    
    /**
     * Metodo que devuelve una lista de Strings dentro de un archivo
     * siempre que cumplan con el patron indicado.
     * Si es patron es = null, devuelve todas las lineas del archivo
     * @param fileName nombre del archivo
     * @param pattern patron de texto.
     * @return 
     */
    public static List<String> getListFromFileWithPatter(String fileName, String pattern) {
        List<String> r = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null) {
                if (pattern == null || pattern.trim().length() == 0) {
                    r.add(str);
                } else {
                    if(str.matches(pattern)){
                        r.add(str);
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("excepcion:"+e);
        }
        return r;
    }
    
    /**
     * 
     * @param nameFile
     * @param idRepo
     * @param sufix
     * @return 
     */
    public static String generateNameFile(String nameFile, String idRepo, String sufix) {
        return nameFile.replace(sufix, idRepo);
    }    
}
