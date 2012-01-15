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

    private boolean editable = true;
    /**
     *
     */
    public SingleTableModel() {
        super();
    }

    /**
     *
     * @param columnIndex
     */
    public void removeColumn(int columnIndex) {
        for (int r = 0; r < getRowCount(); r++) {
            Vector row = (Vector) dataVector.elementAt(r);
            row.removeElementAt(columnIndex);
        }
        columnIdentifiers.removeElementAt(columnIndex);
        fireTableStructureChanged();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // set table column uneditable
        return editable;
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
