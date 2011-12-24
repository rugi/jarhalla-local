/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import org.xhubacubi.jarhalla.client.services.DemiurgoFacade;
import org.xhubacubi.jarhalla.client.ui.components.JLabelInput;
import org.xhubacubi.jarhalla.client.ui.components.StatusBar;

/**
 *
 * @author rugi
 */
public class JMain extends JFrame {

    private JMenuBar menuBar;
    private JMenu mMain;
    private JMenu mExit;
    private JMenuItem mIExit;
    private StatusBar status;
    private JTabbedPane tabbed;
    private DefaultComboBoxModel comboRepoModel;
    private JComboBox comboRepo;
    private JButton buttonSearch;
    
    public JMain() {
        super();
        initComponents();
    }

    public void initComponents() {
                                    
        //primero el menu
        mIExit = new JMenuItem("Exit",
                KeyEvent.VK_E);

        mExit = new JMenu("Exit");
        mExit.setMnemonic(KeyEvent.VK_X);
        mExit.add(mIExit);
        mIExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        mMain = new JMenu("Config");
        mMain.setMnemonic(KeyEvent.VK_R);
        menuBar = new JMenuBar();
        menuBar.add(mMain);
        menuBar.add(mExit);
        this.setJMenuBar(menuBar);
        //luego el statusbar
        status = new StatusBar();
        //El tab superior
        tabbed = new JTabbedPane();        
        //Por ahora simulamos la tabla:
        String[] columnNames = {"Jar",
            "Class",
            "Path",
            "Jar Size",
            "Jar LastModif"};
        Object[][] data = {
            {"uno.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"dos.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"tres.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"cuatro.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"cinco.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"uno.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"dos.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"tres.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"cuatro.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"cinco.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"uno.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"dos.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"tres.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"cuatro.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"cinco.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"uno.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"dos.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"tres.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"cuatro.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"cinco.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"uno.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"dos.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"tres.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"cuatro.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"cinco.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"uno.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"dos.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"tres.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"},
            {"cuatro.jar", "org.com.algo.class",
                "/tmp", new Integer(2342342), "11/11/2011"},
            {"cinco.jar", "org.com.algo.class",
                "/tmp", new Integer(234234), "11/11/2011"}            
        };
        JTable table = new JTable(data, columnNames);
        
        JScrollPane scroll =  new JScrollPane(table);
        //Antes de agregar el tab, lo llenamos
        JPanel tabPanel = new JPanel(new GridLayout(0,1)); 
                //Create the radio buttons.
        JRadioButton jardButton = new JRadioButton("Jar");        
        jardButton.setMnemonic(KeyEvent.VK_J); 
        JRadioButton clasButton = new JRadioButton("Class");
        clasButton.setMnemonic(KeyEvent.VK_C);
        ButtonGroup group = new ButtonGroup();
        group.add(jardButton);
        group.add(clasButton);        
        JPanel radioPanel = new JPanel();
        radioPanel.add(jardButton);
        radioPanel.add(clasButton);        

        tabPanel.add(radioPanel);
        tabPanel.add(new JLabelInput("Buscar","Jar o clase a buscar"));
         comboRepoModel = new DefaultComboBoxModel();
         
        Object[] repos =  DemiurgoFacade.getInstance().getService().getListRepo().toArray();
        for(int k = 0;k<repos.length;k++){
            comboRepoModel.addElement(repos[k]);
        }
        this.comboRepo = new JComboBox(comboRepoModel);
        
        tabPanel.add(comboRepo);
        buttonSearch = new JButton("Buscar"); 
        buttonSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("El repositorio seleccionado es:"+comboRepoModel.getSelectedItem());
            }
        });
        tabPanel.add(buttonSearch);
        tabbed.add("Buscar:", tabPanel);       
        //---
        //Y ahora el layout.
        this.setLayout(new BorderLayout());
        this.add(tabbed, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
        this.add(status, BorderLayout.SOUTH);
    }
}
