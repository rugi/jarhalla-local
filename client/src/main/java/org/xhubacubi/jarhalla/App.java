package org.xhubacubi.jarhalla;

import java.io.IOException;
import javax.swing.WindowConstants;
import org.xhubacubi.jarhalla.client.cli.Shell;
import org.xhubacubi.jarhalla.client.ui.JMain;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        // Por default es GUI, si viene -cli
        if (args.length == 1 && args[0].equals("-cli")) {
		Shell shell = new Shell();
		shell.init();
		shell.run();            
        } else {
            JMain main = new JMain();
            main.setSize(500, 400);
            main.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            main.pack();
            main.setVisible(true);
        }
    }
}
