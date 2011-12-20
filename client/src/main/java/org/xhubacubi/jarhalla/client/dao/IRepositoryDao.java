/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao;

/**
 *
 * @author rugi
 */
public interface IRepositoryDao {
   
    public boolean addRepo(byte id, String path);
    public boolean deleteRepo(byte id);
}
