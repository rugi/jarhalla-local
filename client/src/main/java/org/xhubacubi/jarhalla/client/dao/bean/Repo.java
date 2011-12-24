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
public class Repo {
    private StringBuilder path;
    
    public Repo(){
        this("");
    }
    public Repo(String path){
        super();
        this.path = new StringBuilder(path);        
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
        this.path.delete(0, this.path.length());
        this.path.append(path);
    }
    
    public String toString(){
        return this.path.toString();
    }
}
