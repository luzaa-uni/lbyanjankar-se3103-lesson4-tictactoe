package view;

import java.awt.Dimension;

import javax.swing.JPanel;

public class AppCanvas extends JPanel{
    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 100;

    public AppCanvas() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    }
}
