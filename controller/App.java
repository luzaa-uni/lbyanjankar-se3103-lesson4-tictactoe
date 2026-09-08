package controller;

import javax.swing.JFrame;

import model.TicTacToeGame;
import view.AppWindow;

public class App {
    
    public static AppWindow win = new AppWindow();
    public static TicTacToeGame gameModel = new TicTacToeGame();

    public static void main(String[] args) {
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.init();
        win.pack();
        win.setVisible(true);
        
    }
}
