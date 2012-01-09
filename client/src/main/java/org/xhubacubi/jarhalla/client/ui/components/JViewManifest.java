/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xhubacubi.jarhalla.client.ui.components;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import org.xhubacubi.alicante.core.jar.JarUtil;


/**
 *
 * @author rugi
 */
public class JViewManifest extends JPanel {
    /**
     * 
     */
    private JScrollPane scroll;
    
    /**
     * 
     */
    private JTable grid;
    /**
     * 
     */
    private SingleTableModel modeloGrid;

    /**
     * 
     */
    private TitledBorder title;
    
    /**
     * 
     */
    public JViewManifest() {
        super();                
        initComponents();
    }

    /**
     * 
     */
    private void initComponents() {
        title = BorderFactory.createTitledBorder("Propiedades del Manifest.");
        this.setBorder(title);
        modeloGrid = new SingleTableModel();
        grid = new JTable(modeloGrid);
        scroll = new JScrollPane(grid);
        modeloGrid.addColumn("Propiedad");
        modeloGrid.addColumn("Valor:");
        this.setLayout(new BorderLayout());
        this.add(scroll, BorderLayout.CENTER);
    }

    /**
     * 
     */
    private void cleanGrid() {
        int p = grid.getRowCount();
        for (int k = 0; k < p; k++) {
            modeloGrid.removeRow(0);
        }
    }

    /**
     * 
     */
    public void clean(){
        this.cleanGrid();
    }
    /**
     * 
     * @param path 
     */
    public void updateData(String path) {
        SetValueThread sdt = new SetValueThread(path);
        (new Thread(sdt)).start();
    }

    class SetValueThread implements Runnable {

        private String path;

        public SetValueThread(String path) {
            super();
            this.path = path;
        }

        @Override
        public void run() {
            try {
                System.out.println(">>>> Iniciando con " + path);
                cleanGrid();
                JarUtil j = new JarUtil(path);
                // if m != null
                Manifest m = j.getManifest();
                if (m != null) {
                    Map map = m.getEntries();
                    System.out.println("Entradas de " + path + "  " + map.size());
                    Iterator it = map.keySet().iterator();
                    StringBuilder res = new StringBuilder();
                    StringBuilder res2 = new StringBuilder();
                    while (it.hasNext()) {
                        res.delete(0, res.length());
                        res.append(it.next());
                        System.out.println("llave " + res.toString());
                        Attributes at = (Attributes) map.get(res.toString());
                        Iterator llavesAt = at.keySet().iterator();
                        while (llavesAt.hasNext()) {
                            res2.delete(0, res2.length());
                            res2.append(llavesAt.next());
                            //TODO meter un StringBuilder
                            Object[] row = new Object[2];
                            row[0] = "[" + res.toString().toUpperCase() + "]: " + res2.toString();
                            row[1] = at.getValue(res2.toString());
                            modeloGrid.addRow(row);
                        }
                    }
                }
                System.out.println(">>>> Terminando con " + path);
            } catch (IOException ex) {
                System.out.println("Excepcion JViewManifest " + ex);
            }
            //TODO limpiar objetos
        }
    }
}
