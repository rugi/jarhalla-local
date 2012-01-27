/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhubacubi.jarhalla.client.cli.util;

import org.xhubacubi.jarhalla.client.services.DemiurgoFacade;
import org.xhubacubi.jarhalla.client.util.FileUtil;

/**
 *
 * @author rugi
 */
public class ConsoleUtil {

    public void status() {
        System.out.println("[status]----------------------------------------------------");
        System.out.println("[status]Carpeta de almacenamiento (user.home)..:"+FileUtil.getWorkDirectory());
        System.out.println("[status]Total de Repositorios..................:"+
                DemiurgoFacade.getInstance().getService().getListRepo().size());            
        System.out.println("[status]----------------------------------------------------");
    }
}
