package Tests;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGameMenu1 extends JFrame {
    private JLabel gameNameLabel;
    private JTextField usernameField;
    private JButton submitButton;

    public SnakeGameMenu1() {
        // Set up the JFrame
        setTitle("Snake Game Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the components
        gameNameLabel = new JLabel("Snake Game");
        gameNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        usernameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        centerPanel.add(usernameField, gbc);

        submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(submitButton, gbc);

        // Add the components to the JFrame
        add(gameNameLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // Set the JFrame size and make it visible
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeGameMenu1();
            }
        });
    }
}
