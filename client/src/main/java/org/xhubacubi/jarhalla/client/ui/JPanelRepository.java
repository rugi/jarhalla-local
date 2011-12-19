/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import org.xhubacubi.alicante.core.SearchFilesUtil;
import org.xhubacubi.alicante.core.jar.JarUtil;
import org.xhubacubi.jarhalla.client.ui.components.JLabelFileChooser;

/**
 *
 * @author rugi
 */
public class JPanelRepository extends JPanel {

    private JScrollPane scrollRepos;
    private JList listRepos;
    private DefaultListModel reposModel;
    private JSplitPane split;
    private Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    private TitledBorder title;
    private TitledBorder titleDir;
    private TitledBorder titleLog;
    private JScrollPane scrollLog;
    private JTextArea log;
    private JProgressBar progress;
    private JPanel panelDetail;
    private JPanel panelSelect;
    private JButton buttonSearch;
    private JLabelFileChooser chooserDir;

    public JPanelRepository() {
        super();
        initComponents();
    }
    

    public void initComponents() {
        this.setLayout(new BorderLayout());

        reposModel = new DefaultListModel();
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");

        listRepos = new JList(reposModel);

        listRepos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollRepos = new JScrollPane(listRepos);

        // panel seleccion
        chooserDir = new JLabelFileChooser();
        titleDir = BorderFactory.createTitledBorder(
                loweredetched, "Directorio:");
        titleLog = BorderFactory.createTitledBorder(
                loweredetched, "Salida:");
        panelSelect = new JPanel();
        panelSelect.setLayout(new BorderLayout());
        //panelSelect.add(directory, BorderLayout.CENTER);
        panelSelect.add(chooserDir, BorderLayout.CENTER);


        buttonSearch = new JButton("Buscar");
        buttonSearch.addActionListener(new SearchListener());
        panelSelect.add(buttonSearch, BorderLayout.SOUTH);
        panelSelect.setBorder(titleDir);
        // panel Details
        log = new JTextArea();
        log.setEditable(false);

        scrollLog = new JScrollPane(log);
        scrollLog.setBorder(titleLog);
        progress = new JProgressBar();
        panelDetail = new JPanel();
        panelDetail.setLayout(new BorderLayout());
        panelDetail.add(panelSelect, BorderLayout.NORTH);
        panelDetail.add(scrollLog, BorderLayout.CENTER);
        panelDetail.add(progress, BorderLayout.SOUTH);
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollRepos, panelDetail);
        split.setOneTouchExpandable(true);
        split.setDividerLocation(150);
        this.add(split, BorderLayout.CENTER);
        title = BorderFactory.createTitledBorder(
                loweredetched, "Repository List:");
        title.setTitleJustification(TitledBorder.LEFT);
        this.setBorder(title);
    }

    class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String dir = chooserDir.getDirectory();
            if (dir.length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe inficar un directorio.");
                return;
            }
            // Directorio contiene algo.
            if (!new File(dir).isDirectory()) {
                JOptionPane.showMessageDialog(null, "Debe inficar un directorio válido.");
                return;
            }
            //Hasta aqui todo ok, tenemos directorio vàlido.
            SearchJarThread sjt = new SearchJarThread(dir, "(.*?).jar");
            (new Thread(sjt)).start();
        }
    }
    
    private void disabledPanels(){
        this.listRepos.setEnabled(false);
        this.buttonSearch.setEnabled(false);
        this.chooserDir.disabledComponents();                
    }
    
    private void enabledPanels(){
        this.listRepos.setEnabled(true);
        this.buttonSearch.setEnabled(true);
        this.chooserDir.enabledComponents();                
    }    

    //class
    class SearchJarThread implements Runnable {

        private int totalJars;
        private String path;
        private String pattern;

        public SearchJarThread(String path, String pattern) {
            super();
            this.path = path;
            this.pattern = pattern;
        }

        @Override
        public void run() {
            try {
                disabledPanels();
                progress.setIndeterminate(true);
                setTotalJars(0);
                SearchFilesUtil sfu = new SearchFilesUtil();
                List<String> jarsPath = sfu.getPathFilesInFolder(this.path, this.pattern);
                setTotalJars(jarsPath != null ? jarsPath.size() : 0);
                progress.setIndeterminate(false);
                System.out.println("Total de jars encontrados " + getTotalJars());
                log.append("Total de jars encontrados " + getTotalJars());
                log.append("\n");
                if (getTotalJars() > 0) {
                    progress.setMinimum(0);
                    progress.setMaximum(getTotalJars());

                    int k = 0;
                    Iterator<String> pathI = jarsPath.iterator();
                    StringBuilder pathS = new StringBuilder();
                    while (pathI.hasNext()) {
                        k++;
                        pathS.delete(0, pathS.length());
                        pathS.append(pathI.next());
                        System.out.println("Analizando " + pathS.toString());
                        log.append("Analizando "+ k+" de "+ getTotalJars());
                        log.append("\n");
                        log.append("\t"+ pathS.toString());
                        log.append("\n");

                        JarUtil ju = new JarUtil(pathS.toString());
                        List<String> clazz = ju.getClassInside();
                        log.append("\t\t Total de clases encontradas " + clazz.size());
                        log.append("\n");

                        progress.setValue(k);
                        ju = null;
                    }
                    log.append("Analisis concluido"); 
                    log.append("\n");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraon JARs.");
                    return;
                }
            } catch (IOException ex) {
                Logger.getLogger(JPanelRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
             enabledPanels();
        }//run

        /**
         * @return the totalJars
         */
        public int getTotalJars() {
            return totalJars;
        }

        /**
         * @param totalJars the totalJars to set
         */
        public void setTotalJars(int totalJars) {
            this.totalJars = totalJars;
        }
    }
    //class
}
