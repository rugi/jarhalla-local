/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.xhubacubi.jarhalla.client.dao.bean.IArray;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;
import org.xhubacubi.jarhalla.client.services.DemiurgoFacade;
import org.xhubacubi.jarhalla.client.ui.components.JLabelInput;
import org.xhubacubi.jarhalla.client.ui.components.JViewManifest;
import org.xhubacubi.jarhalla.client.ui.components.SingleTableModel;
import org.xhubacubi.jarhalla.client.ui.components.StatusBar;
import org.xhubacubi.jarhalla.client.util.StringUtil;

/**
 *
 * @author rugi
 */
public class JMain extends JFrame {

    /**
     * 
     */
    private JMenuBar menuBar;
    /**
     * 
     */
    private JMenu mMain;
    /**
     * 
     */
    private JMenu mExit;
    /**
     * 
     */
    private JMenuItem mIExit;
    /**
     * 
     */
    private JMenuItem mConfRepos;
    /**
     * 
     */
    private StatusBar status;
    /**
     * 
     */ 
    private JTabbedPane tabbed;
    /**
    * 
    */
    private DefaultComboBoxModel comboRepoModel;
    /**
     * 
     */
    private JComboBox comboRepo;
    /**
     * 
     */
    private JButton buttonSearch;
    /**
     * 
     */
    private JRadioButton jarButton;
    /**
     * 
     */
    private JRadioButton clasButton;
    /**
     * 
     */
    private SingleTableModel modeloGrid;
    /**
     * 
     */
    private JTable grid;
    /**
     * 
     */
    private JLabelInput labelInput;
    /**
     * 
     */
    private JSplitPane panelResult;
    /**
     * 
     */
    private JViewManifest viewManifest;
    
    /**
     * 
     */
    public JMain() {
        super();
        initComponents();
    }

    /**
     * 
     */
    private void initComponents() {
        viewManifest = new JViewManifest();
        comboRepoModel = new DefaultComboBoxModel();
        //primero el menu
        mIExit = new JMenuItem("Exit",
                KeyEvent.VK_E);
        mConfRepos = new JMenuItem("Repositorios",
                KeyEvent.VK_R);
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
        mMain.add(mConfRepos);
        mConfRepos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JDialog jdialog = new JDialog();
                jdialog.setSize(400, 400);
                jdialog.setContentPane(new JPanelRepository());
                jdialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jdialog.setModal(true);
                jdialog.setVisible(true);
                updateModelComboRepos();
            }
        });
        menuBar = new JMenuBar();
        menuBar.add(mMain);
        menuBar.add(mExit);
        this.setJMenuBar(menuBar);
        //luego el statusbar
        status = new StatusBar();
        //El tab superior
        tabbed = new JTabbedPane();
        modeloGrid = new SingleTableModel();
        modeloGrid.setEditable(false);
        grid = new JTable(modeloGrid);
        grid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectionListener listener = new SelectionListener();
        grid.getSelectionModel().addListSelectionListener(listener);
        grid.getColumnModel().getSelectionModel().addListSelectionListener(listener);
        JScrollPane scroll = new JScrollPane(grid);
        //Antes de agregar el tab, lo llenamos
        JPanel tabPanel = new JPanel(new GridLayout(0, 1));
        //Create the radio buttons.
        jarButton = new JRadioButton("Jar");
        jarButton.setMnemonic(KeyEvent.VK_J);
        jarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                removeColumns();
                cleanGrid();
                status.setText("Listo");
                viewManifest.clean();
                modeloGrid.addColumn("Path");
                modeloGrid.addColumn("Jar");
                modeloGrid.addColumn("Size");
                modeloGrid.addColumn("Last Modif");

            }
        });

        clasButton = new JRadioButton("Class");
        clasButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                removeColumns();
                cleanGrid();
                status.setText("Listo");
                viewManifest.clean();
                modeloGrid.addColumn("Path");
                modeloGrid.addColumn("Jar");
                modeloGrid.addColumn("Class");
            }
        });
        clasButton.setMnemonic(KeyEvent.VK_C);
        ButtonGroup group = new ButtonGroup();
        group.clearSelection();
        group.add(jarButton);
        group.add(clasButton);
        JPanel radioPanel = new JPanel();
        radioPanel.add(jarButton);
        radioPanel.add(clasButton);

        tabPanel.add(radioPanel);
        ActionListener searhActionListener = new searhActionListener();
        labelInput = new JLabelInput("Buscar", "");
        labelInput.addActionTextInput(searhActionListener);
        tabPanel.add(labelInput);

        updateModelComboRepos();

        tabPanel.add(comboRepo);
        buttonSearch = new JButton("Buscar");
        buttonSearch.addActionListener(searhActionListener);
        tabPanel.add(buttonSearch);
        tabbed.add("Buscar:", tabPanel);
        //---        
        panelResult = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                           scroll, viewManifest);    
        panelResult.setOneTouchExpandable(true);
        panelResult.setDividerLocation(250);
        //Y ahora el layout Final.
        this.setLayout(new BorderLayout());
        this.add(tabbed, BorderLayout.NORTH);
        this.add(panelResult, BorderLayout.CENTER);
        this.add(status, BorderLayout.SOUTH);
    }

    private void removeColumns() {
        if (this.grid.getColumnCount() == 0) {
            return;
        }
        int pivote = this.grid.getColumnCount();
        for (int k = 0; k < pivote; k++) {
            modeloGrid.removeColumn(0);
        }
        this.grid.repaint();
    }

    private void cleanGrid() {
        int p = grid.getRowCount();
        for (int k = 0; k < p; k++) {
            modeloGrid.removeRow(0);
        }
    }

    private void updateModelComboRepos() {
        comboRepoModel.removeAllElements();
        Object[] repos = DemiurgoFacade.getInstance().getService().getListRepo().toArray();
        for (int k = 0; k < repos.length; k++) {
            comboRepoModel.addElement(repos[k]);
        }
        this.comboRepo = new JComboBox(comboRepoModel);
    }

    //class
    class SearchThread implements Runnable {

        @Override
        public void run() {
            int j = 0;
            buttonSearch.setEnabled(false);
            Cursor c1 = buttonSearch.getCursor();
            buttonSearch.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Object[] r = null;
            String searchText = StringUtil.generatePattern(labelInput.getTextInput().trim());

            if (jarButton.isSelected()) {
                r = DemiurgoFacade.getInstance().getService().
                        getListJarByRepoAndLike(((Repo) comboRepoModel.getSelectedItem()).getId(), searchText).toArray();
            }
            if (clasButton.isSelected()) {
                r = DemiurgoFacade.getInstance().getService().
                        getListClassByIdRepoAndLike(((Repo) comboRepoModel.getSelectedItem()).getId(), searchText).toArray();
            }
            if (r == null || r.length == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados para su busqueda.");                
            } else {
                j = r.length;
                for (int k = 0; k < r.length; k++) {
                    modeloGrid.addRow(((IArray) r[k]).toArray());
                }
            }
            buttonSearch.setEnabled(true);
            buttonSearch.setCursor(c1);
            status.setText("Total de resultados:"+j);
            // Fin de thread
            // se restaura boton.
        }
    }
    //class

    class SelectionListener implements ListSelectionListener {

        // It is necessary to keep the table since it is not possible
        // to determine the table from the event's source
        SelectionListener() {
            super();
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {            
            if (grid.getSelectedRow() >= 0) {
                int fila = grid.getSelectedRow();
                viewManifest.updateData(grid.getModel().getValueAt(fila, 0).toString()+
                         grid.getModel().getValueAt(fila, 1).toString());
            }
        }
    }

    class searhActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            cleanGrid();
            if (!clasButton.isSelected() && !jarButton.isSelected()) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar una opcion de busqueda: jar o class");
                return;
            }
            if (comboRepoModel.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "No existe un repositorio seleccionado.");
                return;
            }
            if (labelInput.getTextInput().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe especificar un criterio de búsqueda.");
                return;
            }
            SearchThread st = new SearchThread();
            (new Thread(st)).start();
        }
    }
}
