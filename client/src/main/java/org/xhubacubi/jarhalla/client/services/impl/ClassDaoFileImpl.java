/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xhubacubi.jarhalla.client.dao.IClassDao;
import org.xhubacubi.jarhalla.client.dao.bean.Clazz;
import org.xhubacubi.jarhalla.client.util.FileUtil;

/**
 *
 * @author rugi
 */
public class ClassDaoFileImpl implements IClassDao {

    public ClassDaoFileImpl(){
        super();
    }
    /**
     * 
     */
    private static final String nameFile = "repo_$ID$_class.jiva";

    @Override
    public boolean addClass(String idRepo, String pathJar, String nameJar, List<String> items) {
        String fileT = FileUtil.getWorkDirectory()
                + File.separatorChar
                + FileUtil.generateNameFile(nameFile, idRepo, "$ID$");
        boolean ready = FileUtil.existOrCreate(fileT);
        if (ready) {
            List<Object> target = new ArrayList<Object>();
            Iterator it = items.iterator();
            while (it.hasNext()) {
                target.add(idRepo + "|" + pathJar + "|" + nameJar + "|" + it.next().toString().replace("/", "."));
            }
            FileUtil.appendFile(fileT, target);
        } else {
            throw new RuntimeException("No se pudo crear el archido de almacenamiento");
        }
        return false;
    }

    @Override
    public boolean addClass(String idRepo, String pathJar, String nameJar, String clazz) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteClassByPathAndNameJar(String idRepo, String nameJar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Clazz> getListClassByIdRepoAndLike(String idRepo, String like) {
        List<Clazz> r = new ArrayList<Clazz>();
        String fileT = FileUtil.getWorkDirectory()
                + File.separatorChar
                + FileUtil.generateNameFile(nameFile, idRepo, "$ID$");        
        List<String> t = FileUtil.getListFromFileWithPatter(fileT, like);        
        Iterator it1 = t.iterator();
        StringBuilder tmp = new StringBuilder();
        while (it1.hasNext()) {
            tmp.delete(0, tmp.length());
            tmp.append(it1.next());
            String[] s = tmp.toString().split("\\|");
            Clazz j1 = new Clazz();
            j1.setIdRepo(s[0]);
            j1.setPathJar(s[1]);
            j1.setJarName(s[2]);
            j1.setClassName(s[3]);
            r.add(j1);
            s = null;
        }
        return r;
    }

    @Override
    public boolean deleteClassByIdRepo(String idRepo) {
        boolean res = false;
        String fileT = FileUtil.getWorkDirectory()
                + File.separatorChar
                + FileUtil.generateNameFile(nameFile, idRepo, "$ID$");               
        return new File(fileT).delete();
    }
}
