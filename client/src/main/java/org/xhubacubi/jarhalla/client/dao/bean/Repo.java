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
    private String id;
    private String path;
    
    public Repo(){
        this("", "");
    }

    
    public Repo(String id, String path){
        super();
        this.path =path; 
        this.id = id;
        System.out.println("Asignando "+ id + ":"+this.path);
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
    
    public String toString(){
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
}
