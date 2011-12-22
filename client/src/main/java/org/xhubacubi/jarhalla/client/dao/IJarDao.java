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

    public boolean addJar(String path, String nameJar);

    public boolean deleteJarByRepo(String path);
    
    public boolean existJar(String path, String nameJar);
    
    public List<Jarh> getListJarByRepoAndLike(String path, String like);
}
