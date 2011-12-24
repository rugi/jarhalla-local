/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.services.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xhubacubi.jarhalla.client.dao.IRepositoryDao;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;
import org.xhubacubi.jarhalla.client.util.FileUtil;

/**
 *
 * @author rugi
 */
public class RepositoryDaoFileImpl implements IRepositoryDao {

    private static final String nameFile = "repos.jiva";
    public RepositoryDaoFileImpl(){
        super();
    }
    @Override
    public boolean addRepo(String path) {
        boolean res = false;
        String fileT = FileUtil.getWorkDirectory() + File.separatorChar + nameFile;
        boolean ready = existOrCreate(fileT);
        if (ready) {
            appendFile(fileT, path);
        }
        return res;

    }

    @Override
    public boolean deleteRepo(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Repo> getListRepo(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existRepo(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean existOrCreate(String fileName) {
        String dirTarger =  fileName;
        File temp = new File(dirTarger);
        boolean res = false;
        if (!temp.exists()) {
            try {
                res = temp.createNewFile();
            } catch (IOException ex) {
                res = false;
            }
        }
        return res;
    }

    private static boolean appendFile(String fileName, String line) {
        boolean res = true;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(line);
            out.close();
        } catch (IOException e) {
           res = false; 
        }
        return res;
    }
}
