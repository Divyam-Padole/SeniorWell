import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FoodIntakeTracker extends JFrame {
    private JTextField foodNameField;
    private JTextField calorieField;
    private JTextArea foodTextArea;

    private int totalCalories = 0;
    private Map<String, Integer> foodMap = new HashMap<>();

    public FoodIntakeTracker() {
        setTitle("Food Intake Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set the background color to [102, 255, 153]
        Color bgColor = new Color(102, 255, 153);
        getContentPane().setBackground(bgColor);

        // Create a panel for food intake
        JPanel foodPanel = new JPanel(new GridLayout(3, 2));
        foodPanel.setBackground(bgColor);

        addCenteredLabel(foodPanel, "Food Name:");
        foodNameField = new JTextField(10);
        foodPanel.add(foodNameField);

        addCenteredLabel(foodPanel, "Calories:");
        calorieField = new JTextField(5);
        foodPanel.add(calorieField);

        JButton addFoodButton = new JButton("Add Food");
        addFoodButton.addActionListener(e -> addFood());
        foodPanel.add(addFoodButton);

        foodTextArea = new JTextArea(10, 20);
        foodTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(foodTextArea);
        foodPanel.add(scrollPane);

        add(foodPanel, BorderLayout.CENTER);
    }

    private void addCenteredLabel(Container container, String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        container.add(label);
    }

    private void addFood() {
        try {
            String foodName = foodNameField.getText();
            int calories = Integer.parseInt(calorieField.getText());

            if (foodMap.containsKey(foodName)) {
                JOptionPane.showMessageDialog(this, "Food item already added. Update the quantity if needed.");
            } else {
                foodMap.put(foodName, calories);
                totalCalories += calories;
                updateFoodTextArea();
            }

            // Clear the input fields
            foodNameField.setText("");
            calorieField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numerical values for calories.");
        }
    }

    private void updateFoodTextArea() {
        StringBuilder foodText = new StringBuilder("Food Intake:\n");
        for (Map.Entry<String, Integer> entry : foodMap.entrySet()) {
            foodText.append(entry.getKey()).append(": ").append(entry.getValue()).append(" calories\n");
        }
        foodText.append("\nTotal Calories: ").append(totalCalories);

        foodTextArea.setText(foodText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FoodIntakeTracker().setVisible(true));
    }
}
