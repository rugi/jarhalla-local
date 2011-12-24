/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao.bean;

/**
 *
 * @author rugi
 */
public class Jarh {
  private String idRepo;
  private String jarName;
  private int size;
  private long dateLastModif;

  public Jarh(){
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
}
