import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class WaterIntakeTracker extends JFrame {
    private JTextField timeField;
    private JTextField amountField;
    private JTextArea waterLogArea;

    private Map<String, Integer> waterLogMap = new HashMap<>();
    private static final int PREFERRED_INTAKE = 2000; // Set the preferred water intake in milliliters

    public WaterIntakeTracker() {
        setTitle("Water Intake Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set the background color
        Color bgColor = new Color(173, 216, 230); // Light Blue
        getContentPane().setBackground(bgColor);

        // Create a panel for water intake
        JPanel waterPanel = new JPanel(new GridLayout(3, 2));
        waterPanel.setBackground(bgColor);

        addCenteredLabel(waterPanel, "Time:");
        timeField = new JTextField(10);
        waterPanel.add(timeField);

        addCenteredLabel(waterPanel, "Amount (ml):");
        amountField = new JTextField(5);
        waterPanel.add(amountField);

        JButton logWaterButton = new JButton("Log Water");
        logWaterButton.addActionListener(e -> logWaterIntake());
        waterPanel.add(logWaterButton);

        waterLogArea = new JTextArea(10, 20);
        waterLogArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(waterLogArea);
        waterPanel.add(scrollPane);

        add(waterPanel, BorderLayout.CENTER);
    }

    private void addCenteredLabel(Container container, String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        container.add(label);
    }

    private void logWaterIntake() {
        try {
            String time = timeField.getText();
            int amount = Integer.parseInt(amountField.getText());

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid positive amount.");
                return;
            }

            if (waterLogMap.containsKey(time)) {
                JOptionPane.showMessageDialog(this, "Water intake already logged for this time. Update if needed.");
            } else {
                waterLogMap.put(time, amount);
                updateWaterLogArea();
                checkPreferredIntake(amount);
            }

            // Clear the input fields
            timeField.setText("");
            amountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the amount.");
        }
    }

    private void updateWaterLogArea() {
        StringBuilder waterLogText = new StringBuilder("Water Intake Log:\n");
        for (Map.Entry<String, Integer> entry : waterLogMap.entrySet()) {
            waterLogText.append(entry.getKey()).append(": ").append(entry.getValue()).append(" ml\n");
        }

        waterLogArea.setText(waterLogText.toString());
    }

    private void checkPreferredIntake(int amount) {
        if (amount > PREFERRED_INTAKE) {
            JOptionPane.showMessageDialog(this, "Great job! You've exceeded the preferred water intake.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WaterIntakeTracker().setVisible(true));
    }
}
