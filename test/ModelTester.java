package test;

import model.*;

public class ModelTester {
    
    static int testsPassed = 0;
    static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("========== TicTacToe Game Test Suite ==========\n");
        
        // Test Group 1: Initialization Tests
        testInitialization();
        
        // Test Group 2: Game Start Tests
        testGameStart();
        
        // Test Group 3: Player Turn Toggle
        testPlayerToggle();
        
        // Test Group 4: Valid Moves (HumanVSHuman)
        testValidMovesHumanVsHuman();
        
        // Test Group 5: Invalid Moves
        testInvalidMoves();
        
        // Test Group 6: Win Conditions - Rows
        testWinConditionsRows();
        
        // Test Group 7: Win Conditions - Columns
        testWinConditionsColumns();
        
        // Test Group 8: Win Conditions - Diagonals
        testWinConditionsDiagonals();
        
        // Test Group 9: Draw Condition
        testDrawCondition();
        
        // Test Group 10: HumanVSComputer Mode
        testHumanVSComputerMode();
        
        // Test Group 11: All Winning Line Combinations
        testAllWinningLineCombinations();
        
        // Test Group 12: Game State Transitions
        testGameStateTransitions();
        
        // Test Group 13: Edge Cases
        testEdgeCases();
        
        // Summary
        System.out.println("\n========== Test Summary ==========");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
    }
    
    // ========== Helper Method ==========
    static void assertTrue(String testName, boolean condition) {
        if (condition) {
            System.out.println("✓ PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("✗ FAIL: " + testName);
            testsFailed++;
        }
    }
    
    static void assertEquals(String testName, Object expected, Object actual) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            System.out.println("✓ PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("✗ FAIL: " + testName + " [Expected: " + expected + ", Got: " + actual + "]");
            testsFailed++;
        }
    }
    
    // ========== Test Group 1: Initialization ==========
    static void testInitialization() {
        System.out.println("\n--- Test Group 1: Initialization ---");
        TicTacToeGame game = new TicTacToeGame();
        
        // Check board is initialized with all U (unmarked)
        CellMark[] board = game.getBoard();
        boolean allUnmarked = true;
        for (CellMark mark : board) {
            if (mark != CellMark.U) {
                allUnmarked = false;
                break;
            }
        }
        assertTrue("Board initialized with all unmarked cells", allUnmarked);
        
        // Check initial state
        assertEquals("Initial game state is INIT", GameState.INIT, game.getState());
        
        // Check default play strategy
        assertEquals("Default play strategy is HumanVSHuman", PlayStrategy.HumanVSHuman, game.getPlayStrategy());
        
        // Check initial winner is null
        assertEquals("Initial winner is null", null, game.getWinner());
        
        // Check board size is 9
        assertEquals("Board size is 9", 9, board.length);
    }
    
    // ========== Test Group 2: Game Start ==========
    static void testGameStart() {
        System.out.println("\n--- Test Group 2: Game Start ---");
        TicTacToeGame game = new TicTacToeGame();
        game.start();
        
        // Check state changes to PLAYING
        assertEquals("State changes to PLAYING", GameState.PLAYING, game.getState());
        
        // Check X starts first
        assertEquals("X starts first", CellMark.X, game.getCurrentPlayer());
        
        // Check moves count is 0
        assertEquals("Initial moves count is 0", 0, game.getMovesCount());
        
        // Check winning line is null
        assertEquals("Winning line is null at start", null, game.getWinningLine());
        
        // Check board is cleared
        CellMark[] board = game.getBoard();
        boolean allUnmarked = true;
        for (CellMark mark : board) {
            if (mark != CellMark.U) {
                allUnmarked = false;
                break;
            }
        }
        assertTrue("Board cleared before game starts", allUnmarked);
    }
    
    // ========== Test Group 3: Player Toggle ==========
    static void testPlayerToggle() {
        System.out.println("\n--- Test Group 3: Player Toggle ---");
        TicTacToeGame game = new TicTacToeGame();
        game.start();
        
        assertEquals("Initially X's turn", CellMark.X, game.getCurrentPlayer());
        
        game.togglePlayerTurn();
        assertEquals("After toggle, O's turn", CellMark.O, game.getCurrentPlayer());
        
        game.togglePlayerTurn();
        assertEquals("After second toggle, X's turn", CellMark.X, game.getCurrentPlayer());
        
        game.togglePlayerTurn();
        game.togglePlayerTurn();
        assertEquals("After even toggles, back to X", CellMark.X, game.getCurrentPlayer());
    }
    
    // ========== Test Group 4: Valid Moves (HumanVSHuman) ==========
    static void testValidMovesHumanVsHuman() {
        System.out.println("\n--- Test Group 4: Valid Moves (HumanVSHuman) ---");
        TicTacToeGame game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        
        // Move 1: X plays at index 0
        game.move(0);
        assertEquals("Move 1: X placed at index 0", CellMark.X, game.getBoard()[0]);
        assertEquals("After move 1, moves count is 1", 1, game.getMovesCount());
        assertEquals("After move 1, O's turn", CellMark.O, game.getCurrentPlayer());
        
        // Move 2: O plays at index 1
        game.move(1);
        assertEquals("Move 2: O placed at index 1", CellMark.O, game.getBoard()[1]);
        assertEquals("After move 2, moves count is 2", 2, game.getMovesCount());
        assertEquals("After move 2, X's turn", CellMark.X, game.getCurrentPlayer());
        
        // Move 3: X plays at index 2
        game.move(2);
        assertEquals("Move 3: X placed at index 2", CellMark.X, game.getBoard()[2]);
        assertEquals("After move 3, moves count is 3", 3, game.getMovesCount());
        assertEquals("After move 3, O's turn", CellMark.O, game.getCurrentPlayer());
    }
    
    // ========== Test Group 5: Invalid Moves ==========
    static void testInvalidMoves() {
        System.out.println("\n--- Test Group 5: Invalid Moves ---");
        TicTacToeGame game = new TicTacToeGame();
        game.start();
        
        // Try to place at index 0
        game.move(0);
        
        // Try to place at same index (should throw)
        try {
            game.move(0);
            System.out.println("✗ FAIL: Should throw IllegalArgumentException for occupied cell");
            testsFailed++;
        } catch (IllegalArgumentException e) {
            System.out.println("✓ PASS: Throws exception for occupied cell");
            testsPassed++;
        }
        
        // Try index out of bounds (negative)
        try {
            game.move(-1);
            System.out.println("✗ FAIL: Should throw IllegalArgumentException for negative index");
            testsFailed++;
        } catch (IllegalArgumentException e) {
            System.out.println("✓ PASS: Throws exception for negative index");
            testsPassed++;
        }
        
        // Try index out of bounds (too large)
        try {
            game.move(9);
            System.out.println("✗ FAIL: Should throw IllegalArgumentException for index >= 9");
            testsFailed++;
        } catch (IllegalArgumentException e) {
            System.out.println("✓ PASS: Throws exception for index >= 9");
            testsPassed++;
        }
    }
    
    // ========== Test Group 6: Win Conditions - Rows ==========
    static void testWinConditionsRows() {
        System.out.println("\n--- Test Group 6: Win Conditions - Rows ---");
        
        // Test Row 1 (indices 0, 1, 2)
        TicTacToeGame game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        game.move(0); // X at 0
        game.move(3); // O at 3
        game.move(1); // X at 1
        game.move(4); // O at 4
        game.move(2); // X at 2 - X wins!
        assertEquals("X wins with top row (0,1,2)", CellMark.X, game.getWinner());
        assertEquals("Game state is OVER", GameState.OVER, game.getState());
        
        // Test Row 2 (indices 3, 4, 5)
        game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        game.move(0); // X at 0
        game.move(3); // O at 3
        game.move(1); // X at 1
        game.move(4); // O at 4
        game.move(6); // X at 6
        game.move(5); // O at 5 - O wins!
        assertEquals("O wins with middle row (3,4,5)", CellMark.O, game.getWinner());
        assertEquals("Game state is OVER", GameState.OVER, game.getState());
        
        // Test Row 3 (indices 6, 7, 8)
        game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        game.move(0); // X at 0
        game.move(6); // O at 6
        game.move(1); // X at 1
        game.move(7); // O at 7
        game.move(3); // X at 3
        game.move(8); // O at 8 - O wins!
        assertEquals("O wins with bottom row (6,7,8)", CellMark.O, game.getWinner());
        assertEquals("Game state is OVER", GameState.OVER, game.getState());
    }
    
    // ========== Test Group 7: Win Conditions - Columns ==========
    static void testWinConditionsColumns() {
        System.out.println("\n--- Test Group 7: Win Conditions - Columns ---");
        
        // Test Column 1 (indices 0, 3, 6)
        TicTacToeGame game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        game.move(0); // X at 0
        game.move(1); // O at 1
        game.move(3); // X at 3
        game.move(2); // O at 2
        game.move(6); // X at 6 - X wins!
        assertEquals("X wins with left column (0,3,6)", CellMark.X, game.getWinner());
        assertEquals("Game state is OVER", GameState.OVER, game.getState());
        
        // Test Column 2 (indices 1, 4, 7)
        game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        game.move(0); // X at 0
        game.move(1); // O at 1
        game.move(2); // X at 2
        game.move(4); // O at 4
        game.move(3); // X at 3
        game.move(7); // O at 7 - O wins!
        assertEquals("O wins with middle column (1,4,7)", CellMark.O, game.getWinner());
        assertEquals("Game state is OVER", GameState.OVER, game.getState());
        
        // Test Column 3 (indices 2, 5, 8)
        game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        game.move(0); // X at 0
        game.move(2); // O at 2
        game.move(1); // X at 1
        game.move(5); // O at 5
        game.move(3); // X at 3
        game.move(8); // O at 8 - O wins!
        assertEquals("O wins with right column (2,5,8)", CellMark.O, game.getWinner());
        assertEquals("Game state is OVER", GameState.OVER, game.getState());
    }
    
    // ========== Test Group 8: Win Conditions - Diagonals ==========
    static void testWinConditionsDiagonals() {
        System.out.println("\n--- Test Group 8: Win Conditions - Diagonals ---");
        
        // Test Diagonal 1 (indices 0, 4, 8)
        TicTacToeGame game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        game.move(0); // X at 0
        game.move(1); // O at 1
        game.move(4); // X at 4
        game.move(2); // O at 2
        game.move(8); // X at 8 - X wins!
        assertEquals("X wins with main diagonal (0,4,8)", CellMark.X, game.getWinner());
        assertEquals("Game state is OVER", GameState.OVER, game.getState());
        
        // Test Diagonal 2 (indices 2, 4, 6)
        game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        game.move(0); // X at 0
        game.move(2); // O at 2
        game.move(1); // X at 1
        game.move(4); // O at 4
        game.move(3); // X at 3
        game.move(6); // O at 6 - O wins!
        assertEquals("O wins with anti-diagonal (2,4,6)", CellMark.O, game.getWinner());
        assertEquals("Game state is OVER", GameState.OVER, game.getState());
    }
    
    // ========== Test Group 9: Draw Condition ==========
    static void testDrawCondition() {
        System.out.println("\n--- Test Group 9: Draw Condition ---");
        TicTacToeGame game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        
        // Create a true draw - no winner with all 9 cells filled
        // Board: X X O
        //        O O X
        //        X O X
        game.move(0); // X
        game.move(4); // O
        game.move(1); // X
        game.move(3); // O
        game.move(5); // X
        game.move(2); // O
        game.move(6); // X
        game.move(7); // O
        game.move(8); // X
        
        assertEquals("Winner is U (draw)", CellMark.U, game.getWinner());
        assertEquals("Game state is OVER", GameState.OVER, game.getState());
        assertEquals("Moves count is 9", 9, game.getMovesCount());
    }
    
    // ========== Test Group 10: HumanVSComputer Mode ==========
    static void testHumanVSComputerMode() {
        System.out.println("\n--- Test Group 10: HumanVSComputer Mode ---");
        
        // Note: Computer moves are random, so we test the pattern
        TicTacToeGame game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSComputer);
        game.start();
        
        // Human (X) plays at index 0
        game.move(0);
        
        // After human move:
        // - Board[0] should be X
        assertEquals("Human move: X at index 0", CellMark.X, game.getBoard()[0]);
        
        // - One additional move should be played (computer's move)
        assertEquals("After human move, 2 moves total", 2, game.getMovesCount());
        
        // - One cell (other than 0) should be marked with O
        int oCount = 0;
        for (int i = 0; i < 9; i++) {
            if (game.getBoard()[i] == CellMark.O) {
                oCount++;
            }
        }
        assertEquals("Exactly one O on board (computer's move)", 1, oCount);
        
        // - It should be X's turn again
        assertEquals("After computer move, X's turn", CellMark.X, game.getCurrentPlayer());
    }
    
    // ========== Test Group 11: All Winning Line Combinations ==========
    static void testAllWinningLineCombinations() {
        System.out.println("\n--- Test Group 11: All Winning Line Combinations ---");
        
        // Row 0: [0, 1, 2]
        testWinningLineWithAssert(new int[]{0, 1, 2}, CellMark.X, "Row 0 (0,1,2)");
        
        // Row 1: [3, 4, 5]
        testWinningLineWithAssert(new int[]{3, 4, 5}, CellMark.X, "Row 1 (3,4,5)");
        
        // Row 2: [6, 7, 8]
        testWinningLineWithAssert(new int[]{6, 7, 8}, CellMark.X, "Row 2 (6,7,8)");
        
        // Column 0: [0, 3, 6]
        testWinningLineWithAssert(new int[]{0, 3, 6}, CellMark.X, "Column 0 (0,3,6)");
        
        // Column 1: [1, 4, 7]
        testWinningLineWithAssert(new int[]{1, 4, 7}, CellMark.X, "Column 1 (1,4,7)");
        
        // Column 2: [2, 5, 8]
        testWinningLineWithAssert(new int[]{2, 5, 8}, CellMark.X, "Column 2 (2,5,8)");
        
        // Diagonal 1: [0, 4, 8]
        testWinningLineWithAssert(new int[]{0, 4, 8}, CellMark.X, "Diagonal 1 (0,4,8)");
        
        // Diagonal 2: [2, 4, 6]
        testWinningLineWithAssert(new int[]{2, 4, 6}, CellMark.X, "Diagonal 2 (2,4,6)");
    }
    
    // Helper method to test winning line and verify the indices match
    static void testWinningLineWithAssert(int[] winningCombination, CellMark expectedWinner, String testName) {
        TicTacToeGame game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.start();
        
        // Strategy: Interleave X moves on winning cells with O moves on other cells
        // Find 2 cells that are NOT in the winning combination for O to play
        int[] nonWinningCells = new int[2];
        int nonWinningIndex = 0;
        for (int i = 0; i < 9 && nonWinningIndex < 2; i++) {
            boolean isInWinning = false;
            for (int j = 0; j < winningCombination.length; j++) {
                if (i == winningCombination[j]) {
                    isInWinning = true;
                    break;
                }
            }
            if (!isInWinning) {
                nonWinningCells[nonWinningIndex++] = i;
            }
        }
        
        // Play moves: X, O, X, O, X (X wins on 5th move)
        game.move(winningCombination[0]); // X
        game.move(nonWinningCells[0]);    // O
        game.move(winningCombination[1]); // X
        game.move(nonWinningCells[1]);    // O
        game.move(winningCombination[2]); // X wins!
        
        // Verify winner
        assertEquals("Winner for " + testName + " is X", CellMark.X, game.getWinner());
        
        // Verify winning line is set
        int[] actualWinningLine = game.getWinningLine();
        assertTrue("Winning line is not null for " + testName, actualWinningLine != null);
        
        // Verify winning line matches expected combination
        boolean winningLineMatch = false;
        if (actualWinningLine != null && actualWinningLine.length == 3) {
            int matchCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (actualWinningLine[i] == winningCombination[j]) {
                        matchCount++;
                        break;
                    }
                }
            }
            winningLineMatch = (matchCount == 3);
        }
        
        assertEquals("Winning line indices correct for " + testName, true, winningLineMatch);
    }
    
    // ========== Test Group 12: Game State Transitions ==========
    static void testGameStateTransitions() {
        System.out.println("\n--- Test Group 11: Game State Transitions ---");
        TicTacToeGame game = new TicTacToeGame();
        
        // INIT -> PLAYING
        assertEquals("Initial state is INIT", GameState.INIT, game.getState());
        game.start();
        assertEquals("After start(), state is PLAYING", GameState.PLAYING, game.getState());
        
        // PLAYING -> OVER (win)
        game.setPlayStrategy(PlayStrategy.HumanVSHuman);
        game.move(0); // X
        game.move(3); // O
        game.move(1); // X
        game.move(4); // O
        game.move(2); // X wins
        assertEquals("After win, state is OVER", GameState.OVER, game.getState());
        
        // Test restart after game over
        game.start();
        assertEquals("After restart, state is PLAYING", GameState.PLAYING, game.getState());
        assertEquals("After restart, winner is null", null, game.getWinner());
    }
    
    // ========== Test Group 12: Edge Cases ==========
    static void testEdgeCases() {
        System.out.println("\n--- Test Group 12: Edge Cases ---");
        
        // Test setting play strategy before game starts
        TicTacToeGame game = new TicTacToeGame();
        game.setPlayStrategy(PlayStrategy.HumanVSComputer);
        assertEquals("Play strategy set correctly", PlayStrategy.HumanVSComputer, game.getPlayStrategy());
        
        // Test multiple games (reset)
        game.start();
        game.move(0);
        game.start();
        assertEquals("After reset, board[0] is U", CellMark.U, game.getBoard()[0]);
        assertEquals("After reset, moves count is 0", 0, game.getMovesCount());
        
        // Test current player setter
        game.setCurrentPlayer(CellMark.O);
        assertEquals("Current player set to O", CellMark.O, game.getCurrentPlayer());
        
        // Test state setter
        game.setState(GameState.OVER);
        assertEquals("State set to OVER", GameState.OVER, game.getState());
        
        // Test that moves don't occur after game is over
        game = new TicTacToeGame();
        game.start();
        game.move(0);
        game.move(1);
        game.move(2);
        game.move(3);
        game.move(4);
        game.move(5);
        game.move(6);
        game.move(7);
        game.move(8);
        // Game should be over (draw)
        assertEquals("Final state is OVER", GameState.OVER, game.getState());
    }
}
