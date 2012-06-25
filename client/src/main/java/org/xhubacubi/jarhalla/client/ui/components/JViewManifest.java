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
import javax.swing.*;
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
    private JLabel nameJar;
    /**
     *
     */
    private JLabel status;

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
        status = new JLabel("Listo.");
        nameJar = new JLabel();
        modeloGrid = new SingleTableModel();
        modeloGrid.setEditable(false);
        grid = new JTable(modeloGrid);
        scroll = new JScrollPane(grid);
        modeloGrid.addColumn("Propiedad");
        modeloGrid.addColumn("Valor:");
        this.setLayout(new BorderLayout());
        this.add(nameJar, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
        this.add(status, BorderLayout.SOUTH);
    }

    /**
     *
     */
    private void cleanGrid() {
        int p = grid.getRowCount();
        for (int k = 0; k < p; k++) {
            if (modeloGrid.getRowCount() > 0) {
                modeloGrid.removeRow(0);
            }
        }
    }

    /**
     *
     */
    public void clean() {
        this.nameJar.setText("");
        this.cleanGrid();
        this.status.setText("Listo");
    }

    /**
     *
     * @param path
     */
    public void updateData(String path) {
        this.nameJar.setText(path);
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
                cleanGrid();
                int k = 0;
                JarUtil j = new JarUtil(path);
                if (j.isValid()) {
                    Map<String, String> mnf = j.getManifestContentInMap();
                    Iterator iterator1 = mnf.keySet().iterator();
                    StringBuilder key = new StringBuilder();
                    StringBuilder content = new StringBuilder();
                    while (iterator1.hasNext()) {
                        key.delete(0, key.length());
                        content.delete(0, content.length());
                        key.append(iterator1.next().toString());
                        content.append(mnf.get(key.toString()));
                        Object[] row = new Object[2];
                        row[0] = key.toString();
                        row[1] = content.toString();
                        modeloGrid.addRow(row);
                        k++;
                    }                    
                }// if valid
                j.clear();
                j = null;
                status.setText(k + " propiedades encontradas.");
            } catch (Exception ex) {
                System.out.println("Excepcion JViewManifest " + ex);
            }
            //TODO limpiar objetos
        }
    }
}
