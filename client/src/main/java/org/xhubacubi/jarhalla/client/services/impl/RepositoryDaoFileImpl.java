/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.services.impl;

import java.io.File;
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

    /**
     *
     */
    private static final String nameFile = "repos.jiva";

    /**
     *
     */
    public RepositoryDaoFileImpl() {
        super();
    }

    @Override
    public String addRepo(String path) {
        String fileT = FileUtil.getWorkDirectory() + File.separatorChar + nameFile;
        boolean ready = FileUtil.existOrCreate(fileT);
        //Solo nos hace falta sacar el id.
        List<Repo> r = getListRepo();
        String idRepo;
        if (r.size() > 0) {
            idRepo = "" + (Integer.parseInt(r.get(r.size() - 1).getId()) + 1);
        } else {
            idRepo = "1";
        }
        if (ready) {
            FileUtil.appendFile(fileT, idRepo + "|" + path);
        }
        return idRepo;
    }

    @Override
    public boolean deleteRepo(String id) {
        String fileT = FileUtil.getWorkDirectory() + File.separatorChar + nameFile;
        boolean ready = FileUtil.existOrCreate(fileT);
        List<Repo> temp = getListRepo();
        Repo dele = new Repo();
        FileUtil.cleanFile(fileT);
        dele.setId(id);
        temp.remove(dele);
        Iterator<Repo> it4 = temp.iterator();
        while (it4.hasNext()) {
            Repo t = it4.next();
            FileUtil.appendFile(fileT, t.getId() + "|" + t.getPath());
        }
        return false;
    }

    @Override
    public List<Repo> getListRepo() {
        List<Repo> res = new ArrayList();
        List<String> l = FileUtil.getListFromFileWithPatter(FileUtil.getWorkDirectory()
                + File.separatorChar + nameFile, null);
        if (l != null) {
            Iterator it = l.iterator();
            StringBuilder line = new StringBuilder();
            while (it.hasNext()) {
                line.delete(0, line.length());
                line.append(it.next().toString());
                if (line.toString().split("\\|").length >= 2) {
                    res.add(new Repo(line.toString().split("\\|")[0], line.toString().split("\\|")[1]));
                }
            }
        }
        return res;
    }

    @Override
    public boolean existRepo(String path) {
        boolean res = false;
        List<Repo> t = getListRepo();
        Iterator<Repo> it1 = t.iterator();
        while (it1.hasNext()) {
            if (it1.next().getPath().equals(path)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Repo getRepoByPath(String path) {
        Repo res = null;
        List<Repo> t = getListRepo();
        Iterator<Repo> it1 = t.iterator();
        while (it1.hasNext()) {
            Repo r = it1.next();
            if (r.getPath().equals(path)) {
                res = new Repo();
                res.setId(r.getId());
                res.setPath(r.getPath());
                break;
            }
        }
        return res;
    }

    @Override
    public Repo getRepoById(String id) {
        Repo res = null;
        List<Repo> t = getListRepo();
        Iterator<Repo> it1 = t.iterator();
        while (it1.hasNext()) {
            Repo r = it1.next();
            if (r.getId().equals(id)) {
                res = new Repo();
                res.setId(r.getId());
                res.setPath(r.getPath());
                break;
            }
        }
        return res;
    }
}
