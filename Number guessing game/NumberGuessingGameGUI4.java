// made by Pratik Hajare 

// for Oasis Infobyte Internship

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class NumberGuessingGameGUI4 extends JFrame {
    private ArrayList<Integer> scoreBoard; // Stores the scores of each game
    private JTextField rangeField; // Input field for specifying the range
    private JTextArea gameOutputArea; // Displays the game output
    private JButton playButton; // Button for starting a new game
    private JButton scoreboardButton; // Button for displaying the scoreboard
    private int randomNumber; // The randomly generated number to guess
    private int guessCount; // Number of guesses made in the current game

    public NumberGuessingGameGUI4() {
        scoreBoard = new ArrayList<>();
        guessCount = 0;

        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponents();

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(400, 300));
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // Create the game output area
        gameOutputArea = new JTextArea();
        gameOutputArea.setEditable(false);
        gameOutputArea.setBackground(Color.BLACK);
        gameOutputArea.setForeground(Color.WHITE);
        gameOutputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        JScrollPane outputScrollPane = new JScrollPane(gameOutputArea);
        mainPanel.add(outputScrollPane, BorderLayout.CENTER);

        // Create the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Create the range label and input field
        JLabel rangeLabel = new JLabel("Range:");
        rangeLabel.setForeground(Color.WHITE);
        buttonPanel.add(rangeLabel);

        rangeField = new JTextField(5);
        buttonPanel.add(rangeField);

        // Create the Play Game button
        playButton = new JButton("Play Game");
        playButton.setBackground(Color.GREEN.darker());
        playButton.setForeground(Color.WHITE);
        buttonPanel.add(playButton);

        // Create the Score Board button
        scoreboardButton = new JButton("Score Board");
        scoreboardButton.setBackground(Color.BLUE.darker());
        scoreboardButton.setForeground(Color.WHITE);
        buttonPanel.add(scoreboardButton);

        // Add action listeners to the buttons
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
        randomNumber = generateRandomNumber(numberRange);
        gameOutputArea.setText("");
        guessCount = 0;
        appendToGameOutput("New game started! Guess a number between 1 and " + numberRange);
    }

    private int generateRandomNumber(int numberRange) {
        Random random = new Random();
        return random.nextInt(numberRange) + 1;
    }

    private void appendToGameOutput(String text) {
        gameOutputArea.append(text + "\n");
    }

    private void guessNumber(int guess) {
        guessCount++;

        if (guess == randomNumber) {
            appendToGameOutput("Congratulations! You guessed the number " + guess + " correctly in " + guessCount + " tries.");
            scoreBoard.add(guessCount);
            rangeField.setEnabled(true);
            playButton.setEnabled(true);
        } else if (guess < randomNumber) {
            appendToGameOutput("Too low! Try again.");
        }         else {
            appendToGameOutput("Too high! Try again.");
        }
    }

    private void displayScoreBoard() {
        StringBuilder scoreboard = new StringBuilder();
        scoreboard.append("--------------------\n");
        scoreboard.append("Score Board\n");
        scoreboard.append("--------------------\n\n");

        Collections.sort(scoreBoard);
        for (Integer score : scoreBoard) {
            scoreboard.append("Finished the game in " + score + " tries\n");
        }

        JOptionPane.showMessageDialog(this, scoreboard.toString(), "Score Board", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}

