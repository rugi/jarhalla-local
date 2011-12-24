/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.services.impl;

import java.io.File;
import java.util.List;
import org.xhubacubi.jarhalla.client.dao.IJarDao;
import org.xhubacubi.jarhalla.client.dao.bean.Jarh;
import org.xhubacubi.jarhalla.client.util.FileUtil;

/**
 *
 * @author rugi
 */
public class JarDaoFileImpl implements IJarDao {

    private static final String nameFile = "repo_$ID$_jars.jiva";

    @Override
    public boolean addJar(String idRepo, String pathJar, int size, long lastModif) {
        String fileT =  FileUtil.getWorkDirectory() + 
                        File.separatorChar + 
                        FileUtil.generateNameFile(nameFile, idRepo,"$ID$");        
        boolean ready = FileUtil.existOrCreate(fileT);
        if(ready){
             FileUtil.appendFile(fileT, idRepo+"|"+pathJar+"|"+size+"|"+lastModif);
        }else{
            throw new RuntimeException("No se pudo crear el archido de almacenamiento");
        }
        return false;
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
