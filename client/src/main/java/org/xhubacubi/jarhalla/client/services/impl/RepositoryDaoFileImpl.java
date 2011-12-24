/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.services.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xhubacubi.jarhalla.client.dao.IRepositoryDao;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;
import org.xhubacubi.jarhalla.client.util.FileUtil;

/**
 *
 * @author rugi
 */
public class RepositoryDaoFileImpl implements IRepositoryDao {

    private static final String nameFile = "repos.jiva";

    public RepositoryDaoFileImpl() {
        super();
    }

    @Override
    public String addRepo(String path) {
        boolean res = false;
        String fileT = FileUtil.getWorkDirectory() + File.separatorChar + nameFile;
        boolean ready = FileUtil.existOrCreate(fileT);
        //Solo nos hace falta sacar el id.
        List<Repo> r = getListRepo();
        String idRepo;        
        if(r.size()>0){
            idRepo = ""+(Integer.parseInt(r.get(r.size()-1).getId())+1);
        }else{
            idRepo="1";
        }
        if (ready) {
            FileUtil.appendFile(fileT, idRepo+"|"+path);
        }
        return idRepo;

    }

    @Override
    public boolean deleteRepo(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Repo> getListRepo() {
        List<Repo> res = new ArrayList();
        List<String> l = FileUtil.getListFromFileWithPatter(FileUtil.
                getWorkDirectory() + 
                File.separatorChar + nameFile, null);
        if(l!=null){
            Iterator it = l.iterator();            
            StringBuilder line = new StringBuilder();
            while(it.hasNext()){
                line.delete(0,line.length());
                line.append(it.next().toString());
                System.out.println("numero de tokens"+line.toString().split("\\|").length);
                if(line.toString().split("\\|").length>=2){
                    res.add(new Repo(line.toString().split("\\|")[0], line.toString().split("\\|")[1] ));
                }
            }
        }
        return res;
    }

    @Override
    public boolean existRepo(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
