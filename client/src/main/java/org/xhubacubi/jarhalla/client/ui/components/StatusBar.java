/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui.components;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author rugi
 */
public class StatusBar extends JLabel {
    
    /** Creates a new instance of StatusBar */
    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 16));
        setMessage("Ready");
    }
    
    /**
     * 
     * @param message 
     */
    public void setMessage(String message) {
        setText(" "+message);        
    }        
}
