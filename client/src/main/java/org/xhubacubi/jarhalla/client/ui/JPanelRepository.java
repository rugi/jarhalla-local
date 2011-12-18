/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.ui;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author rugi
 */
public class JPanelRepository extends JPanel {

    private JScrollPane scrollRepos;
    private JList listRepos;
    private DefaultListModel reposModel;
    private JSplitPane split;

    private Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    private TitledBorder  title;
    public JPanelRepository() {
        super();
        initComponents();
    }

    public void initComponents() {
        this.setLayout(new BorderLayout());

        reposModel = new DefaultListModel();
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");
        reposModel.addElement("/user/alfa/repo1");

        listRepos = new JList(reposModel);

        listRepos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollRepos = new JScrollPane(listRepos);


        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollRepos, null);
        split.setOneTouchExpandable(true);
        split.setDividerLocation(150);
        this.add(split, BorderLayout.CENTER);
        title=BorderFactory.createTitledBorder(
                       loweredetched, "Repository List:");
        title.setTitleJustification(TitledBorder.LEFT);
        this.setBorder(title);
    }
}
