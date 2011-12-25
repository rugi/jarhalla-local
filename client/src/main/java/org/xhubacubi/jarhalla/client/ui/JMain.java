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
import org.xhubacubi.jarhalla.client.dao.bean.IArray;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;
import org.xhubacubi.jarhalla.client.services.DemiurgoFacade;
import org.xhubacubi.jarhalla.client.ui.components.JLabelInput;
import org.xhubacubi.jarhalla.client.ui.components.SingleTableModel;
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
    private JMenuItem mConfRepos;
    private StatusBar status;
    private JTabbedPane tabbed;
    private DefaultComboBoxModel comboRepoModel;
    private JComboBox comboRepo;
    private JButton buttonSearch;
    private JRadioButton jarButton;
    private JRadioButton clasButton;
    private SingleTableModel modeloGrid;
    private JTable grid;

    public JMain() {
        super();
        initComponents();
    }

    public void initComponents() {
        comboRepoModel = new DefaultComboBoxModel();
        //primero el menu
        mIExit = new JMenuItem("Exit",
                KeyEvent.VK_E);
        mConfRepos = new JMenuItem("Repositorys",
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

        grid = new JTable(modeloGrid);

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
        tabPanel.add(new JLabelInput("Buscar", "Jar o clase a buscar"));

        updateModelComboRepos();

        tabPanel.add(comboRepo);
        buttonSearch = new JButton("Buscar");
        buttonSearch.addActionListener(new ActionListener() {

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
                Object[] r = null;
                if (jarButton.isSelected()) {
                    r = DemiurgoFacade.getInstance().getService().
                            getListJarByRepoAndLike(((Repo) comboRepoModel.getSelectedItem()).getId(), null).toArray();
                }
                if (clasButton.isSelected()) {
                    r = DemiurgoFacade.getInstance().getService().
                            getListClassByIdRepoAndLike(((Repo) comboRepoModel.getSelectedItem()).getId(), null).toArray();
                }
                System.out.println("Tamaño de respuesta " + r.length);
                if (r == null && r.length == 0) {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados para su busqueda.");
                    return;
                } else {
                    for (int k = 0; k < r.length; k++) {
                        modeloGrid.addRow(((IArray) r[k]).toArray());
                    }
                }

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
}
