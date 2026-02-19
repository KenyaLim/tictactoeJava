package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private TicTacToeLogic game;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private JButton resetButton;
    
    public TicTacToeGUI() {
        game = new TicTacToeLogic();
        buttons = new JButton[3][3];
        
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Create game board panel
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        boardPanel.setBackground(new Color(240, 240, 240));
        
        // Create buttons for the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = createButton();
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(row, col);
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }
        
        // Create status label
        statusLabel = new JLabel("Player X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setForeground(new Color(50, 50, 50));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Create reset button
        resetButton = new JButton("New Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(70, 130, 180));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setBorder(BorderFactory.createRaisedBevelBorder());
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        
        // Add components to main panel
        mainPanel.add(statusLabel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(resetButton, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Set window properties
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
    
    private JButton createButton() {
        JButton button = new JButton(" ");
        button.setFont(new Font("Arial", Font.BOLD, 48));
        button.setPreferredSize(new Dimension(120, 120));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        return button;
    }
    
    private void handleButtonClick(int row, int col) {
        if (game.makeMove(row, col)) {
            updateButton(row, col);
            updateStatus();
        }
    }
    
    private void updateButton(int row, int col) {
        char cell = game.getCell(row, col);
        buttons[row][col].setText(String.valueOf(cell));
        buttons[row][col].setEnabled(false);
        
        // Set color based on player
        if (cell == 'X') {
            buttons[row][col].setForeground(new Color(220, 20, 60)); // Crimson
        } else {
            buttons[row][col].setForeground(new Color(30, 144, 255)); // Dodger Blue
        }
    }
    
    private void updateStatus() {
        if (game.isGameOver()) {
            char winner = game.getWinner();
            if (winner == ' ') {
                statusLabel.setText("It's a Draw!");
                statusLabel.setForeground(new Color(128, 128, 128));
            } else {
                statusLabel.setText("Player " + winner + " Wins!");
                statusLabel.setForeground(new Color(34, 139, 34)); // Forest Green
            }
            disableAllButtons();
        } else {
            statusLabel.setText("Player " + game.getCurrentPlayer() + "'s Turn");
            statusLabel.setForeground(new Color(50, 50, 50));
        }
    }
    
    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    
    private void resetGame() {
        game.reset();
        statusLabel.setText("Player X's Turn");
        statusLabel.setForeground(new Color(50, 50, 50));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setForeground(Color.BLACK);
            }
        }
    }
}
