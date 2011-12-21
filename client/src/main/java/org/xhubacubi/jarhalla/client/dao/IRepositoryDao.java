/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao;

import java.util.List;

/**
 *
 * @author rugi
 */
public interface IRepositoryDao {

    public boolean addRepo(String path);

    public boolean deleteRepo(String path);

    public List<String> getListRepo(String path);
}
