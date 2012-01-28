/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhubacubi.jarhalla.client.cli.util;

import java.util.List;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;
import org.xhubacubi.jarhalla.client.services.DemiurgoFacade;
import org.xhubacubi.jarhalla.client.util.FileUtil;

/**
 *
 * @author rugi
 */
public class ConsoleUtil {

    public void status() {
        System.out.println("[status]----------------------------------------------------");
        System.out.println("[status]Carpeta de almacenamiento (user.home)..:" + FileUtil.getWorkDirectory());
        System.out.println("[status]Total de Repositorios..................:"
                + DemiurgoFacade.getInstance().getService().getListRepo().size());
        System.out.println("[status]----------------------------------------------------");
    }

    public void showRepos() {
        System.out.println("[showRepos]  --------------------------------------------------------------");
        int k = DemiurgoFacade.getInstance().getService().getListRepo().size();

        if (k == 0) {
            System.out.println("[showRepos] No existen repositorios registrados.");
        } else {

            System.out.println("[showRepos]  ==============================================================");
            System.out.println("[showRepos]    ID\t\tRuta");
            System.out.println("[showRepos]  ==============================================================");
            List<Repo> l = DemiurgoFacade.getInstance().getService().getListRepo();
            for (int i = 0; i < k; i++) {
                System.out.println("[showRepos]    " + l.get(i).getId() + "\t\t" + l.get(i).getPath());
            }
            System.out.println("[showRepos]  ==============================================================");
        }
        System.out.println("[showRepos]  --------------------------------------------------------------");
    }
}
