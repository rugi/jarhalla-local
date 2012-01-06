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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.xhubacubi.jarhalla.client.dao.bean.IArray;
import org.xhubacubi.jarhalla.client.dao.bean.Repo;
import org.xhubacubi.jarhalla.client.services.DemiurgoFacade;
import org.xhubacubi.jarhalla.client.ui.components.JLabelInput;
import org.xhubacubi.jarhalla.client.ui.components.SingleTableModel;
import org.xhubacubi.jarhalla.client.ui.components.StatusBar;
import org.xhubacubi.jarhalla.client.util.StringUtil;

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
    private JLabelInput labelInput;

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
        grid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectionListener listener = new SelectionListener();
grid.getSelectionModel().addListSelectionListener(listener);
grid.getColumnModel().getSelectionModel()
    .addListSelectionListener(listener);

//        grid.getModel().addTableModelListener(new TableModelListener() {
//
//            @Override
//            public void tableChanged(TableModelEvent tme) {
//                System.out.println("get Column" + tme.getColumn());
//                System.out.println("get getFirstRow" + tme.getFirstRow());
//                System.out.println("get getLastRow" + tme.getLastRow() );
//                System.out.println("get getType" + tme.getType() );
//            }
//        });

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
        ActionListener searhActionListener = new searhActionListener();
        labelInput = new JLabelInput("Buscar", "Jar o clase a buscar");
        labelInput.addActionTextInput(searhActionListener);
        tabPanel.add(labelInput);

        updateModelComboRepos();

        tabPanel.add(comboRepo);
        buttonSearch = new JButton("Buscar");
        buttonSearch.addActionListener(searhActionListener);
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

    //class
    class SearchThread implements Runnable {

        @Override
        public void run() {

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
            if (r == null && r.length == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados para su busqueda.");
                return;
            } else {
                for (int k = 0; k < r.length; k++) {
                    modeloGrid.addRow(((IArray) r[k]).toArray());
                }
            }
            buttonSearch.setEnabled(true);
            buttonSearch.setCursor(c1);
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

        public void valueChanged(ListSelectionEvent e) {
            System.out.println("Linea seleccionada."+grid.getSelectedRow());
            // If cell selection is enabled, both row and column change events are fired
            if (e.getSource() == grid.getSelectionModel()
                    && grid.getRowSelectionAllowed()) {
                // Column selection changed
                int first = e.getFirstIndex();
                int last = e.getLastIndex();
                System.out.println(first + "->" + last);
            } else if (e.getSource() == grid.getColumnModel().getSelectionModel()
                    && grid.getColumnSelectionAllowed()) {
                // Row selection changed
                int first = e.getFirstIndex();
                int last = e.getLastIndex();
                System.out.println(first + "->" + last);
            }

            if (e.getValueIsAdjusting()) {
                // The mouse button has not yet been released
            }
        }
    }
    
    class searhActionListener implements ActionListener{

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
