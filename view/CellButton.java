package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import model.CellMark;

public class CellButton extends JButton{
    
    private final int index; // index o the cell in the game board

    public CellButton(int index) {
        this.index = index;
        init();
    }

    private void init() {
        setFont(new Font("Courier New", Font.BOLD, 84));
        setForeground(Color.BLUE);
        setMark(CellMark.U);
        setDefaultBorder();
        setHighLightBorder();
    }

    public void setMark(CellMark mark) {
        switch (mark) {
            case X:
                setText("X");
                break;
            case O:
                setText("O");
                break;
            case U:
                setText("\u22a5"); // up tack symbol
                break;
        }
    }

    public void setDefaultBorder() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
    }

    public void setHighLightBorder() {
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 8));
    }

    public int getIndex() {
        return index;
    }
}
