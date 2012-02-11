/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.xhubacubi.jarhalla.client.dao.bean.Repo;
import org.xhubacubi.jarhalla.client.services.DemiurgoFacade;
import org.xhubacubi.jarhalla.client.ui.components.JLabelFileChooser;
import org.xhubacubi.jarhalla.client.ui.components.JSelectRepoPanel;
import org.xhubacubi.jarhalla.client.util.FileUtil;

/**
 *
 * @author rugi
 */
public final class JPanelRepository extends JPanel {

    /**
     *
     */
    private JScrollPane scrollRepos;
    /**
     *
     */
    private JList listRepos;
    /**
     *
     */
    private DefaultListModel reposModel;
    /*
     *
     */
    private JSplitPane split;
    /**
     *
     */
    private Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    /**
     *
     */
    private TitledBorder title;
    /**
     *
     */
    private TitledBorder titleDir;
    /**
     *
     */
    private TitledBorder titleLog;
    /**
     *
     */
    private JScrollPane scrollLog;
    /**
     *
     */
    private JTextArea log;
    /**
     *
     */
    private JProgressBar progress;
    /**
     *
     */
    private JPanel panelDetail;
    /**
     *
     */
    private JPanel panelSelect;
    /**
     *
     */
    private JButton buttonSearch;
    /**
     *
     */
    private JLabelFileChooser chooserDir;
    /**
     *
     */
    private JLabel status;

    /**
     *
     */
    public JPanelRepository() {
        super();
        initComponents();
    }

    /**
     *
     */
    private void updateReposList() {
        reposModel.removeAllElements();
        List<Repo> r = DemiurgoFacade.getInstance().getService().getListRepo();
        Iterator it1 = r.iterator();
        while (it1.hasNext()) {
            reposModel.addElement(it1.next().toString());
        }
    }

    /**
     *
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        
        final List<String> dirs = getDirectorysTools();
        if (dirs.isEmpty()) {
            status = new JLabel("Listo");            
        } else {            
            status = new JLabel("Se ha detectado al menos un repositorio de"
                    + " codigo que puede ser indexado. Click para mas detalle.");   
            status.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent me) {
                    JDialog jdialog = new JDialog(SwingUtilities.getWindowAncestor(status));
                    jdialog.setSize(300, 150);
                    JSelectRepoPanel selector = new JSelectRepoPanel(dirs);
                    jdialog.setContentPane(selector);
                    jdialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    jdialog.setModal(true);
                    jdialog.setVisible(true);                    
                    if (selector.getSelectedOption() != null) {
                        chooserDir.setDirectory(selector.getSelectedOption().toString());
                        buttonSearch.doClick();
                    }
                }
            });
        }
        reposModel = new DefaultListModel();
        updateReposList();
        listRepos = new JList(reposModel);
        listRepos.addKeyListener(new KeyListener() {

            // 8 back spcae
            //127 es delete
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_DELETE
                        || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    int p = listRepos.getSelectedIndex();
                    if (p >= 0) {
                        Object[] options = {"Sí",
                            "No"};
                        int n = JOptionPane.showOptionDialog(null,
                                "¿Desea eliminar el repositorio seleccionado?",
                                "Eliminar repositorio existente.",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[1]);

                        if (n == 0) {
                            List<Repo> r = DemiurgoFacade.getInstance().getService().getListRepo();
                            String id = r.get(p).getId();
                            DemiurgoFacade.getInstance().getService().deleteRepo(id);
                            //se elimina del modelo
                            reposModel.remove(p);
                            //se actualiza la lista
                            listRepos.repaint();
                        }
                    }
                }
            }
        });

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
        //TODO meter la seleccion de carpetas.
        this.add(status, BorderLayout.SOUTH);
    }

    private List<String> getDirectorysTools() {
        List<String> r = new ArrayList<String>();
        if (FileUtil.existMaven2Directory()) {            
            //ahora, validar si existe en los repositorios.
            if (DemiurgoFacade.getInstance().getService().existRepo(FileUtil.getMaven2Directory())) {
                System.out.println("El repositorio ya está indexado.");
            } else {
                //Por ahora est es el caso que manejaremos
                // Debe mostrarse como opcion a indexar.
                System.out.println("Se agrera sugerencia de repositorio" + FileUtil.getMaven2Directory());
                r.add(FileUtil.getMaven2Directory());
            }
        } else {
            System.out.println("No encontre carpeta m2");
        }
        if (FileUtil.existIvyDirectory()) {            
            if (DemiurgoFacade.getInstance().getService().existRepo(FileUtil.getIvyDirectory())) {
                System.out.println("El repositorio ya está indexado.");
            } else {
                //Por ahora est es el caso que manejaremos
                // Debe mostrarse como opcion a indexar.
                System.out.println("Se agrera sugerencia de repositorio" + FileUtil.getIvyDirectory());
                r.add(FileUtil.getIvyDirectory());
            }
        } else {
            System.out.println("No encontre carpeta ivy");
        }

        if (FileUtil.existGradleDirectory()) {            
            if (DemiurgoFacade.getInstance().getService().existRepo(FileUtil.getGradleDirectory())) {
                System.out.println("El repositorio ya está indexado.");
            } else {
                //Por ahora est es el caso que manejaremos
                // Debe mostrarse como opcion a indexar.
                System.out.println("Se agrera sugerencia de repositorio" + FileUtil.getGradleDirectory());
                r.add(FileUtil.getGradleDirectory());
            }
        } else {
            System.out.println("No encontre carpeta Gradle");
        }
        return r;
    }

    class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String dir = chooserDir.getDirectory();
            if (dir.length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe indicar un directorio.");
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

    private void disabledPanels() {
        this.listRepos.setEnabled(false);
        this.buttonSearch.setEnabled(false);
        this.chooserDir.disabledComponents();
    }

    private void enabledPanels() {
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
                log.setText("");
                disabledPanels();
                progress.setIndeterminate(true);
                setTotalJars(0);
                SearchFilesUtil sfu = new SearchFilesUtil();
                List<String> jarsPath = sfu.getPathFilesInFolder(this.path, this.pattern);
                setTotalJars(jarsPath != null ? jarsPath.size() : 0);
                progress.setIndeterminate(false);
                log.append("Total de jars encontrados " + getTotalJars());
                log.append("\n");
                if (getTotalJars() > 0) {
                    String idRepo = null;
                    if (DemiurgoFacade.getInstance().getService().existRepo(this.path)) {
                        Object[] options = {"Sí",
                            "No"};
                        int n = JOptionPane.showOptionDialog(null,
                                "Ya existe esa carpeta como repositorio "
                                + "¿Desea volver a generarlo?",
                                "Repositorio existente.",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[1]);
                        if (n == 0) {
                            //Se recupera id del repositorio
                            idRepo = DemiurgoFacade.getInstance().
                                    getService().
                                    getRepoByPath(this.path).getId();
                            System.out.println("El id del repo seleccionado es:"
                                    + idRepo);
                            // se elimnan los archivos.                             
                            boolean deleteClass = DemiurgoFacade.getInstance().
                                    getService().deleteClassByIdRepo(idRepo);
                            boolean deleteJar = DemiurgoFacade.getInstance().
                                    getService().deleteJarByRepo(idRepo);
                        } else {
                            return;
                        }
                        //return;
                    }//if
                    // si encontro jars, se crea el repositorio
                    if (idRepo == null) {
                        idRepo = DemiurgoFacade.getInstance().getService().addRepo(this.path);
                    }
                    progress.setMinimum(0);
                    progress.setMaximum(getTotalJars());

                    int k = 0;
                    Iterator<String> pathI = jarsPath.iterator();
                    StringBuilder pathS = new StringBuilder();
                    JarUtil ju = new JarUtil();
                    while (pathI.hasNext()) {
                        k++;
                        pathS.delete(0, pathS.length());
                        pathS.append(pathI.next());
                        log.append("Analizando " + k + " de " + getTotalJars());
                        log.append("\n");
                        log.append("\t" + pathS.toString());
                        log.append("\n");
                        ju.setSourceFile(pathS.toString());
                        //solo se procesan los archivos válidos.
                        if (ju.isValid()) {
                            //se van grabando los jars
                            String nameJar = new File(pathS.toString()).getName();
                            String pathJar = pathS.toString().substring(0, pathS.toString().length() - nameJar.length());
                            DemiurgoFacade.getInstance().getService().
                                    addJar(idRepo,
                                    pathJar,
                                    nameJar,
                                    ju.getSize(), ju.getLastModif());
                            List<String> clazz = ju.getClassInside();
                            log.append("\t\t Total de clases encontradas " + clazz.size());
                            log.append("\n");
                            // se graban las clases
                            DemiurgoFacade.getInstance().getService().
                                    addClass(idRepo,
                                    pathJar,
                                    nameJar,
                                    clazz);
                        }else{
                            log.append("\t\t El archivo no pudo ser procesado. Cero Clases agregadas.");
                        }
                        progress.setValue(k);         
                        ju.clear();
                    }
                    ju = null;
                    log.append("Analisis concluido");
                    log.append("\n");
                    updateReposList();
                    JOptionPane.showMessageDialog(null, "Ha concluido el análisis de la carpeta.");
                    SwingUtilities.getWindowAncestor(scrollRepos).dispose();
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
