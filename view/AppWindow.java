package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.border.Border;

public class AppWindow extends JFrame{
    
    private final AppCanvas canvas = new AppCanvas();

    public void init() {
        setTitle("Tic Tac Toe");
        setLocation(400, 300);
        var cp = getContentPane();
        cp.add(canvas, BorderLayout.NORTH);
        
    }
}
