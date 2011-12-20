/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.dao;

/**
 *
 * @author rugi
 */
public interface IJarDao {
    public boolean addJar(byte id, String path);
    public boolean deleteJarByIdRepo(byte id);
}
