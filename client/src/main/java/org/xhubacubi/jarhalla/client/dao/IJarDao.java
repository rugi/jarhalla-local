/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao;

import java.util.List;
import org.xhubacubi.jarhalla.client.dao.bean.Jarh;

/**
 *
 * @author rugi
 */
public interface IJarDao {
/**
 * 
 * @param idRepo
 * @param pathJar
 * @param nameJar
 * @param size
 * @param lastModif
 * @return 
 */
    public boolean addJar(String idRepo, String pathJar, String nameJar,int size, long lastModif);

    /**
     * 
     * @param idRepo
     * @return 
     */
    public boolean deleteJarByRepo(String idRepo);
    
    /**
     * 
     * @param idRepo
     * @param nameJar
     * @return 
     */
    public boolean existJar(String idRepo, String nameJar);
    
    /**
     * 
     * @param idRepo
     * @param like
     * @return 
     */
    public List<Jarh> getListJarByRepoAndLike(String idRepo, String like);
}
