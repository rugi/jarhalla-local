/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao.bean;

/**
 *
 * @author rugi
 */
public class Repo {

    /**
     * 
     */
    private String id;
    /**
     * 
     */
    private String path;

    /**
     * 
     */
    public Repo() {
        this("", "");
    }

    /**
     * 
     * @param id
     * @param path 
     */
    public Repo(String id, String path) {
        super();
        this.path = path;
        this.id = id;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path.toString();
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 
     * @return 
     */
    public String toString() {
        return this.path;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        if (this.id.equals(((Repo) other).id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
