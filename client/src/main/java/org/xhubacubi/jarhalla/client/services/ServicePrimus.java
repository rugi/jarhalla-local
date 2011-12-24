/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.services;

import java.util.List;
import org.xhubacubi.jarhalla.client.dao.IClassDao;
import org.xhubacubi.jarhalla.client.dao.IJarDao;
import org.xhubacubi.jarhalla.client.dao.IRepositoryDao;
import org.xhubacubi.jarhalla.client.dao.bean.Jarh;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;

/**
 * Clase para implementar los servicios principales.
 *
 * @author rugi
 */
public class ServicePrimus implements IClassDao, IJarDao, IRepositoryDao {

    IClassDao clazz;
    IJarDao jar;
    IRepositoryDao repo;

    public ServicePrimus(IRepositoryDao repo, IJarDao jar, IClassDao clazz) {
        this.clazz = clazz;
        this.jar = jar;
        this.repo = repo;
    }

    @Override
    public boolean addClass(String path, String nameJar, String clazz) {
        return this.clazz.addClass(path, nameJar, clazz);
    }

    @Override
    public boolean deleteClassByPathAndNameJar(String path, String nameJar) {
       return this.clazz.deleteClassByPathAndNameJar(path, nameJar);
    }

    @Override
    public List<String> getListClassByPathAndNameJar(String path, String nameJar) {
       return this.clazz.getListClassByPathAndNameJar(path, nameJar);
    }

    @Override
    public boolean addJar(String path, String nameJar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteJarByRepo(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existJar(String path, String nameJar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addRepo(String path) {
       return  this.repo.addRepo(path);
    }

    @Override
    public boolean deleteRepo(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    @Override
    public boolean existRepo(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Jarh> getListJarByRepoAndLike(String path, String like) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Repo> getListRepo() {
        return  this.repo.getListRepo();
    }
}
