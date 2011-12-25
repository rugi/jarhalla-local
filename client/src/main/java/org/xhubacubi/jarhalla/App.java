package org.xhubacubi.jarhalla;

import javax.swing.WindowConstants;
import org.xhubacubi.jarhalla.client.ui.JMain;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        JMain main = new JMain();        
        main.setSize(500, 400);
        main.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        main.pack();
        main.setVisible(true);           
    }
}
