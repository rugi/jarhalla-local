/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao;

import java.util.List;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;

/**
 *
 * @author rugi
 */
public interface IRepositoryDao {

    public String addRepo(String path);

    public boolean deleteRepo(String path);

    public List<Repo> getListRepo();

    public boolean existRepo(String path);
}
