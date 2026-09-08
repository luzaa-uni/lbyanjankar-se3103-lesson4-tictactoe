package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AppWindow;

public class ButtonActionListener implements ActionListener{
    
    @Override 
    public void actionPerformed(ActionEvent e) {
        var command = e.getActionCommand();
        
        switch (command) {
            case AppWindow.NEW_GAME_ACTION 
                -> App.gameModel.start();
            case AppWindow.EXIT_ACTION 
                -> System.exit(0);
            case AppWindow.VS_HUMAN_ACTION 
                -> App.gameModel.setPlayStrategy(model.PlayStrategy.HumanVSHuman);
            case AppWindow.VS_COMPUTER_ACTION 
                -> App.gameModel.setPlayStrategy(model.PlayStrategy.HumanVSComputer);
            default 
                -> throw new IllegalArgumentException("Unknown action command: " + command);
        }

        System.out.println("Button clicked:" + command);

        App.win.updateWindow();
    }
}
