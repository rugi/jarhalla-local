/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhubacubi.jarhalla.client.cli.util;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;
import org.xhubacubi.jarhalla.client.services.DemiurgoFacade;
import org.xhubacubi.jarhalla.client.ui.JMain;
import org.xhubacubi.jarhalla.client.util.FileUtil;
import org.xhubacubi.jarhalla.client.util.StringUtil;

/**
 *
 * @author rugi
 */
public class ConsoleUtil {

    private static final int INIT_SIZE = 100;
    private int resultSize = INIT_SIZE;
    private boolean buscando = false;

    public void searchJar(String command) {
        System.out.println("[searchJar]----------------------------------------------------");
        String[] tokens = command.split("\\s+");
        if (tokens.length == 1) {
            showInfoSearch("searchJar", "Jars", "axis2.jar, WSDL*.jar, *conn*, etc");
            return;
        }
        if (tokens.length > 1) {
            if (validaSintaxis(command)) {
                //validamos repo
                String query = tokens[1].trim();
                String idRepo = tokens[3].trim();
                System.out.println("Repo: " + idRepo);
                Repo repo = DemiurgoFacade.getInstance().getService().getRepoById(idRepo);
                if (repo == null) {
                    System.out.println("[searchJar]   El repositorio que indicas "
                            + idRepo + ": No existe");
                    System.out.println("[searchJar]");
                    System.out.println("[searchJar]     Revisa los repositorios existentes con: showRepos");
                } else {
                    System.out.println("[searchJar] Buscando: " + query);
                    System.out.println("[searchJar] En      : " + repo.getPath());
                    int k = 0;
                    Object[] r = null;
                    buscando = true;
                    SearchTaskThread st = new SearchTaskThread();
                    (new Thread(st)).start();
                    r = DemiurgoFacade.getInstance().getService().
                            getListJarByRepoAndLike(idRepo, StringUtil.generatePattern(query.trim())).toArray();
                    buscando = false;
                    System.out.println("[searchJar]  Busqueda Terminada. Resultados: "+ r.length);

                }
            } else {
                System.out.println("[searchJar]     Sintaxis no reconocida.");
                showInfoSearch("searchJar", "Jars", "axis2.jar, WSDL*.jar, *conn*, etc");
            }
        }
        //si llega aca es que tiene sintaxis
        System.out.println("[searchJar]----------------------------------------------------");
    }

    /**
     * No vale que inicien y terminen con *, eso lo hace el buscador.
     * @param s
     * @return 
     */
    private String validateString(String s){        
        String t1 = null;
        String t2 = null;
        if(s.indexOf("*")==0){
            t1 = s.substring(1);
        }else{
            t1 =  s;
        }        
        if(t1.endsWith("*")){
            t2 = t1.substring(0, t1.length()-1);
        }else{
            t2 = t1;
        }        
        return t2;
    }
    private boolean validaSintaxis(String command) {
        boolean r = false;
        String[] tokens = command.split("\\s+");
        if ((tokens.length > 1 && tokens.length < 4) || (tokens.length > 4)) {
            r = false;
        } else {
            r = true;
        }//
        return r;
    }

    private void showInfoSearch(String command, String nameItem, String examples) {
        System.out.println("[" + command + "]" + command + ":");
        System.out.println("[" + command + "]           Realiza una busqueda de " + nameItem + " segun el criterio dado sobre el repositorio indicado.");
        System.out.println("[" + command + "]                  searchJar <criterio> in <idRepo>");
        System.out.println("[" + command + "]Donde: ");
        System.out.println("[" + command + "]           <criterio> Es el criterio de busqueda. Por ejemplo:");
        System.out.println("[" + command + "]                      " + examples);
        System.out.println("[" + command + "] ");
        System.out.println("[" + command + "]           <idRepo>   Identificador del repositorio sobre el cual se realiza la busqueda.");
        System.out.println("[" + command + "]                      Para ver los repositorios existentes: showRepos ");
        System.out.println("[" + command + "]----------------------------------------------------");
    }

    public void resultSize(String command) {
        System.out.println("[resultSize]----------------------------------------------------");
        String[] tokens = command.split("=");
        if (tokens.length == 2) {
            int v = StringUtil.toInt(tokens[1].trim(), INIT_SIZE);
            System.out.println("[resultSize] Estableciendo el valor a: " + v);
            resultSize = v;
        } else {
            if (tokens.length > 2) {
                System.out.println("[resultSize]Demasiados parametros.  ");
                System.out.println("[resultSize]    ");
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
                System.out.println("[resultSize]     Si <valor> no cumple con la condición, se usa el valor por default: " + INIT_SIZE);
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

    private boolean validaSyntaxisSearch(String body) {
        return true;
    }

    //--- Clases
    class SearchTaskThread implements Runnable {

        @Override
        public void run() {
            while (buscando) {
                try {
                    System.out.println(".");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConsoleUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //---
}
