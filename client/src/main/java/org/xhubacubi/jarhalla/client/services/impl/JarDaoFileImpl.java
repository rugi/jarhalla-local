/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.services.impl;

import java.util.List;
import org.xhubacubi.jarhalla.client.dao.IJarDao;
import org.xhubacubi.jarhalla.client.dao.bean.Jarh;

/**
 *
 * @author rugi
 */
public class JarDaoFileImpl implements IJarDao {

    @Override
    public boolean addJar(String idRepo, String nameJar, int size, long lastModif) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteJarByRepo(String idRepo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existJar(String idRepo, String nameJar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Jarh> getListJarByRepoAndLike(String idRepo, String like) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
