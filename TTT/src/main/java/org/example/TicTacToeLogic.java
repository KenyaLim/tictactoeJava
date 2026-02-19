package org.example;

public class TicTacToeLogic {
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;
    private char winner;
    
    public TicTacToeLogic() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        winner = ' ';
        initializeBoard();
    }
    
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
    public boolean makeMove(int row, int col) {
        if (gameOver || row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            return false;
        }
        
        board[row][col] = currentPlayer;
        
        if (checkWinner(row, col)) {
            gameOver = true;
            winner = currentPlayer;
        } else if (isBoardFull()) {
            gameOver = true;
            winner = ' ';
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        
        return true;
    }
    
    private boolean checkWinner(int row, int col) {
        // Check row
        if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
            return true;
        }
        
        // Check column
        if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
            return true;
        }
        
        // Check main diagonal
        if (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        
        // Check anti-diagonal
        if (row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        
        return false;
    }
    
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public char getWinner() {
        return winner;
    }
    
    public char getCell(int row, int col) {
        return board[row][col];
    }
    
    public void reset() {
        initializeBoard();
        currentPlayer = 'X';
        gameOver = false;
        winner = ' ';
    }
}
