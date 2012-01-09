/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao;

import java.util.List;
import org.xhubacubi.jarhalla.client.dao.bean.Clazz;

/**
 *
 * @author rugi
 */
public interface IClassDao {

    /**
     * 
     * @param idRepo
     * @param pathJar
     * @param nameJar
     * @param clazz
     * @return 
     */
    public boolean addClass(String idRepo,String pathJar ,String nameJar, String clazz);
    /**
     * 
     * @param idRepo
     * @param pathJar
     * @param nameJar
     * @param items
     * @return 
     */
    public boolean addClass(String idRepo,String pathJar , String nameJar, List<String> items);

    /**
     * 
     * @param idRepo
     * @return 
     */
    public boolean deleteClassByIdRepo(String idRepo);
    /**
     * 
     * @param idRepo
     * @param nameJar
     * @return 
     */
    public boolean deleteClassByPathAndNameJar(String idRepo, String nameJar);

    /**
     * 
     * @param idRepo
     * @param like
     * @return 
     */
    public List<Clazz> getListClassByIdRepoAndLike(String idRepo, String like);
}
