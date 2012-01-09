/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao.bean;

import java.util.Date;

/**
 *
 * @author rugi
 */
public class Jarh implements IArray {

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
    private int size;
    /**
     * 
     */
    private long dateLastModif;

    /**
     * 
     */
    public Jarh() {
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
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the dateLastModif
     */
    public long getDateLastModif() {
        return dateLastModif;
    }

    /**
     * @param dateLastModif the dateLastModif to set
     */
    public void setDateLastModif(long dateLastModif) {
        this.dateLastModif = dateLastModif;
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

    @Override
    public Object[] toArray() {
        Object [] r = new  Object [4];
        r[0] = this.pathJar;
        r[1] = this.jarName;
        r[2] =  new Integer(this.size);
        r[3] = new Date(this.dateLastModif);
        return r;
    }
}
