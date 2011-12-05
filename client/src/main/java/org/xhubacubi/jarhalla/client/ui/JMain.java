/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
    private StatusBar status;

    public JMain() {
        super();
        initComponents();
    }

    public void initComponents() {
        //primero el menu
        mIExit = new JMenuItem("Exit",
                KeyEvent.VK_E);

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
        menuBar = new JMenuBar();
        menuBar.add(mMain);
        menuBar.add(mExit);
        this.setJMenuBar(menuBar);
        //luego el statusbar
        status = new StatusBar();

        //Y ahora el layout.
        this.setLayout(new BorderLayout());
        this.add(status, BorderLayout.SOUTH);
    }
}
