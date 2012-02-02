/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhubacubi.jarhalla.client.cli.util;

import java.util.List;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;
import org.xhubacubi.jarhalla.client.services.DemiurgoFacade;
import org.xhubacubi.jarhalla.client.util.FileUtil;
import org.xhubacubi.jarhalla.client.util.StringUtil;

/**
 *
 * @author rugi
 */
public class ConsoleUtil {
    private static final int INIT_SIZE= 100;
    private int resultSize = INIT_SIZE;
    public void resultSize(String command) {
        System.out.println("[resultSize]----------------------------------------------------");
        String[] tokens = command.split("=");
        if (tokens.length == 2) {
            int v = StringUtil.toInt(tokens[1].trim(), INIT_SIZE);
            System.out.println("[resultSize] Estableciendo el valor a: "+v);
            resultSize = v;
        } else {
            if(tokens.length >2){
                System.out.println("[resultSize]Demasiados parametros.  ");
                System.out.println("[resultSize]");
            }
            if (tokens.length == 1) {
                System.out.println("[resultSize]resultSize:");
                System.out.println("[resultSize]             valor actual :" + resultSize);
                System.out.println("[resultSize]");
                System.out.println("[resultSize] Muestra o establece la cantidad de resultados que "
                        + "se mostrara en cada busqueda.");
                System.out.println("[resultSize] resultSize=<valor>");
                System.out.println("[resultSize]Donde: ");
               System.out.println("[resultSize]     <valor> es una cantidad entera. ");
               System.out.println("[resultSize]     Si <valor> no cumple con la condición, se usa el valor por default: "+INIT_SIZE);
            }
        }
        System.out.println("[resultSize]----------------------------------------------------");
    }

    public void delete(String command) {
        System.out.println("[deleteRepo]----------------------------------------------------");
        String[] tokens = command.split(" ");
        String idRepo = null;
        //si es un solo token mostramos ayuda
        if (tokens.length == 1) {
            System.out.println("[deleteRepo]deleteRepo:");
            System.out.println("[deleteRepo]           Elimina un repositorio.");
            System.out.println("[deleteRepo]                  deleteRepo <id_repo>");
            System.out.println("[deleteRepo]Donde: ");
            System.out.println("[deleteRepo]           <id_repo> es el identificador  del repositorio a borrar.");
            System.out.println("[deleteRepo]           Para ver los repositorios disponibles use el comando: <<showRepos>>");
            System.out.println("[deleteRepo]----------------------------------------------------");
            return;
        }
        if (tokens.length > 2) {
            System.out.println("[deleteRepo]Se recibieron mas parametros de los disponibles. ");
            System.out.println("[deleteRepo]Se toma el requerido y se ignora el resto ");
        }
        idRepo = tokens[2];
        Repo repo = DemiurgoFacade.getInstance().getService().getRepoById(idRepo);
        if (repo != null) {
            System.out.println("[deleteRepo]    Eliminando respositorio con identificador: " + idRepo);
            System.out.println("[deleteRepo]             " + repo.getPath());
            boolean res = DemiurgoFacade.getInstance().getService().deleteRepo(idRepo);
            if (res) {
                System.out.println("[deleteRepo]    Repositorio eliminado: " + idRepo);
            } else {
            }
        } else {
            System.out.println("[deleteRepo]  El repositorio indicado no existe:" + idRepo);
        }
        System.out.println("[deleteRepo]----------------------------------------------------");
    }

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
