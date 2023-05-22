// made by Pratik Hajare 

// for Oasis Infobyte Internship

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class NumberGuessingGameGUI extends JFrame {
    private ArrayList<Integer> scoreBoard;
    private JTextField rangeField;
    private JTextArea gameOutputArea;
    private JButton playButton;
    private JButton scoreboardButton;

    public NumberGuessingGameGUI() {
        scoreBoard = new ArrayList<>();
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        gameOutputArea = new JTextArea();
        gameOutputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(gameOutputArea);
        mainPanel.add(outputScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JLabel rangeLabel = new JLabel("Range:");
        buttonPanel.add(rangeLabel);

        rangeField = new JTextField(5);
        buttonPanel.add(rangeField);

        playButton = new JButton("Play Game");
        buttonPanel.add(playButton);

        scoreboardButton = new JButton("Score Board");
        buttonPanel.add(scoreboardButton);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame();
            }
        });

        scoreboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayScoreBoard();
            }
        });
    }

    private void playGame() {
        int numberRange = Integer.parseInt(rangeField.getText());
        int randomNumber = randomNumber(numberRange);
        guessNumber(randomNumber);
    }

    private int randomNumber(int numberRange) {
        Random random = new Random();
        int randomNumber = random.nextInt(numberRange) + 1;
        return randomNumber;
    }

    private void guessNumber(int randomNumber) {
        int userGuess;
        int guess = 0;

        do {
            String guessString = JOptionPane.showInputDialog(
                NumberGuessingGameGUI.this, "Enter your guess:");
            try {
                userGuess = Integer.parseInt(guessString);
                guess++;
                if (userGuess > randomNumber) {
                    gameOutputArea.append("Lower\n");
                } else if (userGuess < randomNumber) {
                    gameOutputArea.append("Higher\n");
                }
            } catch (NumberFormatException e) {
                gameOutputArea.append("Invalid input. Please enter a number.\n");
                userGuess = 0;
            }
        } while (randomNumber != userGuess);

        gameOutputArea.append("\n");
        if (guess == 1) {
            gameOutputArea.append("You answered right in " + guess + " try!\n");
        } else {
            gameOutputArea.append("You answered right in " + guess + " tries!\n");
        }
        scoreBoard.add(guess);
    }

    private void displayScoreBoard() {
        StringBuilder scoreboard = new StringBuilder();
        scoreboard.append("--------------------\n");
        scoreboard.append("Score Board\n");
        scoreboard.append("--------------------\n\n");

        Collections.sort(scoreBoard);
        for (Integer scores : scoreBoard) {
            scoreboard.append("Finished the game in " + scores + " tries\n");
        }
        JOptionPane.showMessageDialog(NumberGuessingGameGUI.this, scoreboard.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}
