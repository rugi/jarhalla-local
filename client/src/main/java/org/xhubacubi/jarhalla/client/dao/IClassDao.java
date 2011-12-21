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
public interface IClassDao {

    public boolean addClass(String path, String nameJar, String clazz);

    public boolean deleteClassByPathAndNameJar(String path, String nameJar);

    public List<String> getListClassByPathAndNameJar(String path, String nameJar);
}
