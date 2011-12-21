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

    public boolean addJar(String path, String nameJar);

    public boolean deleteJarByRepo(String path);
}
