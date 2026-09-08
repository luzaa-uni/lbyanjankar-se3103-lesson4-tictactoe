package model;

public class TicTacToeGame {
    
    private final CellMark[] board;
    private CellMark currentPlayer;
    private int movesCount;
    private int[] winningLine;
    private GameState state;
    private PlayStrategy playStrategy;
    private CellMark winner; // O, X, U (draw), null (ongoing)

    public TicTacToeGame() {
        board = new CellMark[9];
        for (int i = 0; i < 9; i++) {
            board[i] = CellMark.U;
        }
        state = GameState.INIT;
        playStrategy = PlayStrategy.HumanVSHuman; // default strategy
       
    }

    public void start() {
        for (int i = 0; i < 9; i++) {
            board[i] = CellMark.U;
        }
        currentPlayer = CellMark.X; // X always starts first
        movesCount = 0;
        winningLine = null;
        state = GameState.PLAYING;
        winner = null;
    }
}
