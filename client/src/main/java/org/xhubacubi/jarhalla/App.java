package org.xhubacubi.jarhalla;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.xhubacubi.jarhalla.client.ui.JMain;
import org.xhubacubi.jarhalla.client.ui.JPanelRepository;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        JMain main = new JMain();        
        main.setSize(300, 300);
        main.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        main.pack();
        main.setVisible(true);
        
        
        // Para probar el componente de listado de repositorios.
//        JPanelRepository repos = new JPanelRepository();
//        JFrame main = new JFrame();
//        main.add(repos);
//        main.setSize(300, 300);
//        main.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        main.pack();
//        main.setVisible(true);        
    }
}
