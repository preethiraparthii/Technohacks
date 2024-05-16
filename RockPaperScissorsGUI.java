import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsGUI extends JFrame {
    private JButton rockButton, paperButton, scissorsButton, resetButton;
    private JLabel resultLabel;

    public RockPaperScissorsGUI() {
        setTitle("Rock Paper Scissors");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1));

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        resetButton = new JButton("Reset");
        resultLabel = new JLabel("", JLabel.CENTER);

        rockButton.addActionListener(new ButtonClickListener());
        paperButton.addActionListener(new ButtonClickListener());
        scissorsButton.addActionListener(new ButtonClickListener());
        resetButton.addActionListener(new ButtonClickListener());

        panel.add(rockButton);
        panel.add(paperButton);
        panel.add(scissorsButton);
        panel.add(resetButton);
        panel.add(resultLabel);

        panel.setBackground(Color.LIGHT_GRAY); // Set panel background color

        add(panel);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == resetButton) {
                resetGame();
                return;
            }

            String userChoice = e.getActionCommand();
            if (!userChoice.equals("Reset")) {
                String computerChoice = getRandomChoice();
                String result = determineWinner(userChoice, computerChoice);
                resultLabel.setText(result);
            }
        }
    }

    private String getRandomChoice() {
        String[] options = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        return options[random.nextInt(options.length)];
    }

    private String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if (userChoice.equals("Rock")) {
            return computerChoice.equals("Scissors") ? "You win!" : "Computer wins!";
        } else if (userChoice.equals("Paper")) {
            return computerChoice.equals("Rock") ? "You win!" : "Computer wins!";
        } else { // userChoice is "Scissors"
            return computerChoice.equals("Paper") ? "You win!" : "Computer wins!";
        }
    }

    private void resetGame() {
        resultLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RockPaperScissorsGUI();
            }
        });
    }
}