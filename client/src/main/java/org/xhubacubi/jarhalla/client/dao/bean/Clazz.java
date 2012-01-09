/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao.bean;

/**
 *
 * @author rugi
 */
public class Clazz implements IArray {

    /**
     * 
     */
    private String idRepo;
    /**
     * 
     */
    private String pathJar;
    /**
     * 
     */
    private String jarName;
    /**
     * 
     */
    private String className;

    /**
     * 
     */
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

    /**
     * 
     * @return 
     */
    @Override
    public Object[] toArray() {
        Object [] r = new  Object [3];
        r[0] = this.pathJar;
        r[1] = this.jarName;
        r[2] = this.className;
        return r;
    }

    /**
     * @return the pathJar
     */
    public String getPathJar() {
        return pathJar;
    }

    /**
     * @param pathJar the pathJar to set
     */
    public void setPathJar(String pathJar) {
        this.pathJar = pathJar;
    }
}
