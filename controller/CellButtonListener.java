package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameState;
import view.CellButton;

public class CellButtonListener implements ActionListener{
    
    @Override 
    public void actionPerformed(ActionEvent e) {
        CellButton button = (CellButton) e.getSource();
        int index = button.getIndex();
        System.out.println("Cell button " + index + " clicked.");

        if (index < 0 || index > 8) {
            // should never happen since CellButton only allows valid indices, but just in case
            throw new IllegalArgumentException("Invalid cell index: " + index);
        }
        App.gameModel.move(index);
        if (App.gameModel.getState() == GameState.OVER) {
            System.out.println("Game over! Winner: " + App.gameModel.getWinner());
        }
        else {
            App.gameModel.togglePlayerTurn();
        }
        App.win.updateWindow();
    }
}
