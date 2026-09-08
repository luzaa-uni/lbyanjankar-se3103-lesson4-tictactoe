package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controller.App;
import controller.ButtonActionListener;
import model.PlayStrategy;

public class AppWindow extends JFrame{
    
    // labels
    public static final String VS_HUMAN_ACTION = "vs. human";
    public static final String VS_COMPUTER_ACTION = "vs. computer";
    public static final String NEW_GAME_ACTION = "New Game";
    public static final String EXIT_ACTION = "Exit";

    private final AppCanvas canvas = new AppCanvas();
    
    // buttons
    private final CellButton[] cellButtons = new CellButton[9];
    private final JButton newGameButton = new JButton(NEW_GAME_ACTION);
    private final JButton exitButton = new JButton(EXIT_ACTION);
    private final JRadioButton vsHumanButton = new JRadioButton(VS_HUMAN_ACTION);
    private final JRadioButton vsComputerButton = new JRadioButton(VS_COMPUTER_ACTION);

    public void init() {
        setTitle("Tic Tac Toe");
        setLocation(400, 300);
        var cp = getContentPane();
        cp.add(canvas, BorderLayout.NORTH);

        // create cell buttons and add to canvas
        for (int i = 0; i < cellButtons.length; i++) {
            cellButtons[i] = new CellButton(i);
        }
        JPanel cellButtonPanel = new JPanel();
        cellButtonPanel.setLayout(new GridLayout(3, 3));
        for (var cellButton: cellButtons) {
            cellButtonPanel.add(cellButton);
        }
        cp.add(cellButtonPanel, BorderLayout.CENTER);

        // south panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        cp.add(southPanel, BorderLayout.SOUTH);

        // radio button panel
        JPanel radioButtonPanel = new JPanel();
        southPanel.add(radioButtonPanel);
        radioButtonPanel.setBorder(new TitledBorder("Play Strategy"));
        radioButtonPanel.add(vsHumanButton);
        radioButtonPanel.add(vsComputerButton);
        vsHumanButton.setSelected(App.gameModel.getPlayStrategy() == PlayStrategy.HumanVSHuman);
        vsComputerButton.setSelected(App.gameModel.getPlayStrategy() == PlayStrategy.HumanVSComputer);

        ButtonGroup strategyGroup = new ButtonGroup();
        strategyGroup.add(vsHumanButton);
        strategyGroup.add(vsComputerButton);

        // action button panel
        JPanel actionButtonPanel = new JPanel();
        southPanel.add(actionButtonPanel);
        actionButtonPanel.add(newGameButton);
        actionButtonPanel.add(exitButton);

        // add action listener
        var buttonListener = new ButtonActionListener();
        newGameButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);
        vsHumanButton.addActionListener(buttonListener);
        vsComputerButton.addActionListener(buttonListener);
    }
}
