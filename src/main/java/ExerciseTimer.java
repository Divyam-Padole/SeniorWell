import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciseTimer extends JFrame {
    private Timer timer;
    private int secondsRemaining = 60; // Default initial time in seconds
    private int initialTime = 60; // Initial time in seconds for resetting the timer

    private JLabel timerLabel;
    private JTextField durationField;

    public ExerciseTimer() {
        setTitle("Enhanced Exercise Timer");
        setSize(398, 408); // Set the frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set the background color
        Color bgColor = new Color(0, 255, 204);
        getContentPane().setBackground(bgColor);

        // Create a label to display the timer
        timerLabel = new JLabel(formatTime(secondsRemaining), SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(timerLabel, BorderLayout.CENTER);

        // Create a panel for user input and control
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(bgColor);  // Set background color for controlPanel

        // Create a text field for the user to set the timer duration
        durationField = new JTextField(5);
        durationField.setText(String.valueOf(initialTime));
        controlPanel.add(new JLabel("Set Timer (sec):"));
        controlPanel.add(durationField);

        // Set up a button to start the exercise timer
        JButton startButton = new JButton("Start Exercise");
        startButton.addActionListener(e -> startTimer());
        controlPanel.add(startButton);

        // Set up a button to stop the exercise timer
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stopTimer());
        controlPanel.add(stopButton);

        // Set up a button to pause/resume the exercise timer
        JButton pauseButton = new JButton("Pause/Resume");
        pauseButton.addActionListener(e -> pauseResumeTimer());
        controlPanel.add(pauseButton);

        // Set up a button to reset the exercise timer
        JButton resetButton = new JButton("Reset Timer");
        resetButton.addActionListener(e -> resetTimer());
        controlPanel.add(resetButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Create a timer that fires every second
        timer = new Timer(1000, e -> {
            secondsRemaining--;

            if (secondsRemaining >= 0) {
                // Update the timer label
                timerLabel.setText(formatTime(secondsRemaining));
            } else {
                // Timer has reached zero, stop the timer
                timer.stop();
                showEncouragingMessage();
            }
        });
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private void startTimer() {
        try {
            // Get the user-set timer duration
            int userInput = Integer.parseInt(durationField.getText());

            // Validate and set the timer duration
            if (userInput > 0) {
                secondsRemaining = userInput;
                initialTime = userInput;
                timerLabel.setText(formatTime(secondsRemaining));
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid positive number for the timer duration.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the timer duration.");
            return;
        }

        // Start the timer
        timer.start();
    }

    private void stopTimer() {
        // Stop the timer
        timer.stop();
    }

    private void pauseResumeTimer() {
        // Pause or resume the timer
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    private void resetTimer() {
        // Reset the timer to the initial duration
        secondsRemaining = initialTime;
        timerLabel.setText(formatTime(secondsRemaining));
        timer.stop();
    }

    private void showEncouragingMessage() {
        JOptionPane.showMessageDialog(this, "Great job! Keep it up. You can do it!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExerciseTimer().setVisible(true));
    }
}
