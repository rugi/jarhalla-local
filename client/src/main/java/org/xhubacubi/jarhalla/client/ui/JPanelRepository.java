/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
    private JFileChooser chooser;
    private JTextField directory;
    private JButton buttonSearch;

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
        chooser = new JFileChooser();
        directory = new JTextField();
        //chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Selecciona el directorio a indexar");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);        
        directory.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    directory.setText(chooser.getCurrentDirectory().getAbsolutePath());
                    buttonSearch.requestFocusInWindow();
                    
                } else {
                    System.out.println("No Selection ");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });


        titleDir = BorderFactory.createTitledBorder(
                loweredetched, "Directorio:");
        titleLog = BorderFactory.createTitledBorder(
                loweredetched, "Salida:");        
        panelSelect = new JPanel();
        panelSelect.setLayout(new BorderLayout());
        panelSelect.add(directory, BorderLayout.CENTER);
                              
        
        buttonSearch= new JButton("Buscar");
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
}
