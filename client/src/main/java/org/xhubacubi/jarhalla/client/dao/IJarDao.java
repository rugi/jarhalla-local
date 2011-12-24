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

    public boolean addJar(String idRepo, String nameJar, int size, long lastModif);

    public boolean deleteJarByRepo(String idRepo);
    
    public boolean existJar(String idRepo, String nameJar);
    
    public List<Jarh> getListJarByRepoAndLike(String idRepo, String like);
}
