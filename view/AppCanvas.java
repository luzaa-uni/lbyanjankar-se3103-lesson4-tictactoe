package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import controller.App;
import model.CellMark;
import model.GameState;
import model.TicTacToeGame;

public class AppCanvas extends JPanel{
    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 100;
    public static final int X_OFFSET = 50;


    public AppCanvas() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    }

    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        TicTacToeGame game = App.gameModel;
        GameState state = game.getState();

        switch (state) {
            case INIT -> {
                g2.setColor(Color.BLUE);
                g2.drawString("Press <New Game> to start", X_OFFSET, 50);
            }
            case PLAYING -> {
                g2.setColor(Color.BLUE);
                var str = String.format("Current Player: %s | Move: %d", 
                    game.getCurrentPlayer(), game.getMovesCount());
                g2.drawString(str, X_OFFSET, 90);
            }
            case OVER -> {
                g2.setColor(Color.RED);
                CellMark winner = game.getWinner();
                String resultStr = winner + " has won";
                if (winner == CellMark.U) {
                    resultStr = "It's a draw!";
                }
                g2.drawString("Game Over: " + resultStr, X_OFFSET, 50);
                g2.drawString("Press <New Game> to play again", X_OFFSET, 80);
            }
        }
        
    }
}
