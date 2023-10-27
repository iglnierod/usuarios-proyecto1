package gui;

import model.App;

import javax.swing.*;

public class WindowListener extends JFrame {
    public WindowListener(App app) {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                app.logOut();
            }
        });
    }
}
