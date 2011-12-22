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
  private String path;
  private String jarName;
  private long size;
  private long dateLastModif;

  public Jarh(){
    super();
  }
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
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
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(long size) {
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
}
