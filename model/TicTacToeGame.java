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

    public void togglePlayerTurn() {
        currentPlayer = (currentPlayer == CellMark.X) ? CellMark.O : CellMark.X;
    }

    public void setWinner() {
        // this.winner = X, O, U (draw), null (ongoing)
        int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };
        
        for (int[] combo : winningCombinations) {
            if (board[combo[0]] != CellMark.U &&
                board[combo[0]] == board[combo[1]] &&
                board[combo[1]] == board[combo[2]]) {
                winner = board[combo[0]];
                winningLine = combo;
                state = GameState.OVER;
                return;
            }
        }
        if (movesCount == 9) {
            winner = CellMark.U; // draw
            state = GameState.OVER;
        }
        // if no winner and moves are still possible, keep the game ongoing
    }


    private void humanMove(int cellIndex) {
        if (board[cellIndex] != CellMark.U) {
            // never happens. UI disables occupied cells, but we check just in case
            throw new IllegalArgumentException("Cell is already occupied");
        }
        board[cellIndex] = currentPlayer;
        movesCount++;
        setWinner();
    }

    public void move(int cellIndex) {

        if (cellIndex < 0 || cellIndex >= board.length) {
            // should never happen if the UI is correctly implemented, but we check just in case
            throw new IllegalArgumentException("Invalid cell index");
        }
        humanMove(cellIndex); // human move first
        
        // if vsComputer, let the computer play after the human move
        if (playStrategy == PlayStrategy.HumanVSComputer) {
            if (state == GameState.PLAYING) { // Only computer plays if game is still active
                computerMove();
                if (state == GameState.PLAYING) { // Toggle back only if game continues
                    togglePlayerTurn();
                }
            }
        }

    }

    private void computerMove() {
        // pick a random empty cell for the computer's move
        int pick = -1;
        int nthUmark = (int) (Math.random() * (9 - movesCount)); // pick a random number between 0 and the number of empty cells - 1
        int countU = -1;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == CellMark.U) {
                countU++;
                if (countU == nthUmark) {
                    pick = i;
                    break;
                }
            }
        }
        if (pick < 0 || pick >= board.length) {
            // this should never happen, but we check just in case
            throw new IllegalStateException("No valid moves left for computer");
        }
        this.board[pick] = currentPlayer; // computer' move
        movesCount++;
        setWinner();
    }


    // getters and setters
    public CellMark[] getBoard() {
        return board;
    }

    public CellMark getCurrentPlayer() {
        return currentPlayer;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public int[] getWinningLine() {
        return winningLine;
    }

    public GameState getState() {
        return state;
    }
     
    public PlayStrategy getPlayStrategy() {
        return playStrategy;
    }

    public CellMark getWinner() {
        return winner;
    }

    public void setCurrentPlayer(CellMark currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setPlayStrategy(PlayStrategy playStrategy) {
        this.playStrategy = playStrategy;
    }

    public void setState(GameState state) {
        this.state = state;
    }



}
