/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao.bean;

/**
 *
 * @author rugi
 */
public class Clazz {

    private String idRepo;
    private String jarName;
    private String className;

    
    public Clazz(){
        super();
    }


    /**
     * @return the jarName
     */
    public String getJarName() {
        return jarName;
    }

    /**
     * @param jarName the jarName to set
     */
    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the idRepo
     */
    public String getIdRepo() {
        return idRepo;
    }

    /**
     * @param idRepo the idRepo to set
     */
    public void setIdRepo(String idRepo) {
        this.idRepo = idRepo;
    }
}
