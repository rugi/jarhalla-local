/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui.components;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rugi
 */
public class SingleTableModel extends DefaultTableModel {

    public SingleTableModel() {
        super();
    }

    public void removeColumn(int columnIndex) {
        for (int r = 0; r < getRowCount(); r++) {
            Vector row = (Vector) dataVector.elementAt(r);
            row.removeElementAt(columnIndex);
        }
        columnIdentifiers.removeElementAt(columnIndex);
        fireTableStructureChanged();
    }
}
