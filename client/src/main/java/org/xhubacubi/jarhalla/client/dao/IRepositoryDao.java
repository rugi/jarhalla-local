/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao;

import java.util.List;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;

/**
 *
 * @author rugi
 */
public interface IRepositoryDao {

    /**
     * 
     * @param path
     * @return 
     */
    public String addRepo(String path);
/**
 * 
 * @param id
 * @return 
 */
    public boolean deleteRepo(String id);

    /**
     * 
     * @return 
     */
    public List<Repo> getListRepo();

    /**
     * 
     * @param path
     * @return 
     */
    public boolean existRepo(String path);
    
    /**
     * 
     * @param path
     * @return 
     */
    public Repo getRepoByPath(String path);
    /**
     * 
     * @param path
     * @return 
     */
    public Repo getRepoById(String id);    
    
    
}
