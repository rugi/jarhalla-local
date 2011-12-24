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
    public List<Repo> getListRepo() {
        List<Repo> res = new ArrayList();
        List<String> l = getListFromFileWithPatter(FileUtil.
                getWorkDirectory() + 
                File.separatorChar + nameFile, null);
        if(l!=null){
            Iterator it = l.iterator();            
            while(it.hasNext()){
                res.add(new Repo(it.next().toString()));
            }
        }
        return res;
    }

    @Override
    public boolean existRepo(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean existOrCreate(String fileName) {
        String dirTarger = fileName;
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

    /**
     * Metodo que devuelve una lista de Strings dentro de un archivo
     * siempre que cumplan con el patron indicado.
     * Si es patron es = null, devuelve todas las lineas del archivo
     * @param fileName nombre del archivo
     * @param pattern patron de texto.
     * @return 
     */
    private List<String> getListFromFileWithPatter(String fileName, String pattern) {
        List<String> r = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null) {
                 if(pattern==null||pattern.trim().length()==0){
                     r.add(str);
                 }else{
                     //se evalua el patron
                 }
            }
            in.close();
        } catch (IOException e) {
            //
            
        }
        return r;
    }
}
