import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PillReminder extends JFrame {
    private int timeZones;
    private JLabel reminderLabel;

    public PillReminder(int pillCount) {
        this.timeZones = calculateTimeZones(pillCount);

        setTitle("Pill Intake Reminder");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set the background color
        Color bgColor = new Color(240, 240, 240); // Light Gray
        getContentPane().setBackground(bgColor);

        // Create a panel for pill intake
        JPanel pillPanel = new JPanel(new GridLayout(3, 2));
        pillPanel.setBackground(bgColor);

        addCenteredLabel(pillPanel, "Pill Count:");
        JTextField pillCountField = new JTextField(5);
        pillCountField.setText(String.valueOf(pillCount)); // Set the initial value
        pillPanel.add(pillCountField);

        addCenteredLabel(pillPanel, "Time Zones:");
        JTextField timeZoneField = new JTextField(5);
        timeZoneField.setText(String.valueOf(timeZones));
        timeZoneField.setEditable(false); // Make it non-editable
        pillPanel.add(timeZoneField);

        JButton setReminderButton = new JButton("Set Pill Reminder");
        setReminderButton.addActionListener(e -> setPillReminder(pillCountField.getText(), timeZoneField));
        pillPanel.add(setReminderButton);

        reminderLabel = new JLabel("", SwingConstants.CENTER);
        pillPanel.add(reminderLabel);

        add(pillPanel, BorderLayout.CENTER);
    }

    private void addCenteredLabel(Container container, String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        container.add(label);
    }

    private int calculateTimeZones(int pillCount) {
        // Use the modulus operator to check divisibility
        return (pillCount % 3 == 0) ? 3 : 2;
    }

    private void setPillReminder(String pillCountText, JTextField timeZoneField) {
        try {
            int pillsToTake = Integer.parseInt(pillCountText);

            if (pillsToTake <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid positive pill count.");
                return;
            }

            int remindersPerZone = pillsToTake / timeZones;

            StringBuilder reminderMessage = new StringBuilder("Pill Intake Reminders:\n");

            for (int zone = 1; zone <= timeZones; zone++) {
                String timeOfDay = getTimeOfDay(zone);
                reminderMessage.append(timeOfDay).append(": Take ").append(remindersPerZone).append(" pills\n");
            }

            reminderLabel.setText(reminderMessage.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numerical value for the pill count.");
        }
    }

    private String getTimeOfDay(int zone) {
        switch (zone) {
            case 1:
                return "Morning";
            case 2:
                return "Evening";
            case 3:
                return "Night";
            default:
                return "Unknown";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PillReminder(7).setVisible(true));
    }
}
