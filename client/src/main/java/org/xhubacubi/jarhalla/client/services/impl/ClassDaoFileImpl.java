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
import org.xhubacubi.jarhalla.client.util.FileUtil;

/**
 *
 * @author rugi
 */
public class ClassDaoFileImpl implements IClassDao {

    private static final String nameFile = "repo_$ID$_class.jiva";

    @Override
    public boolean addClass(String idRepo,String pathJar , String nameJar, List<String> items) {
        String fileT = FileUtil.getWorkDirectory()
                + File.separatorChar
                + FileUtil.generateNameFile(nameFile, idRepo, "$ID$");
        boolean ready = FileUtil.existOrCreate(fileT);
        if (ready) {
            List<Object> target = new ArrayList<Object>();
            Iterator it = items.iterator();
            while (it.hasNext()) {
                target.add(idRepo + "|"+pathJar+"|" + nameJar + "|" + it.next().toString().replace("/", "."));
            }
            FileUtil.appendFile(fileT, target);
        } else {
            throw new RuntimeException("No se pudo crear el archido de almacenamiento");
        }
        return false;
    }

    @Override
    public boolean addClass(String idRepo,String pathJar , String nameJar, String clazz) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteClassByPathAndNameJar(String idRepo, String nameJar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getListClassByPathAndNameJar(String idRepo, String nameJar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
