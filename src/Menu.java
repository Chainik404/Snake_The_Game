import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JPanel {
    private JLabel gameNameLabel;
    private JTextField usernameField;
    private JButton submitButton;

    private DataContext dataContext;

    public Menu(DataContext dataContext) {
        this.dataContext = dataContext;
        // Set up the JFrame
//        setTitle("Snake Game Menu");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        submitButton.addActionListener(e -> {

        dataContext.addPlayer(getUsername());
        onGameStarted();

        });


                gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 0, 0);
        centerPanel.add(submitButton, gbc);

        // Add the components to the JFrame
        add(gameNameLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // Set the JFrame size and make it visible
        setSize(400, 200);
//        setLocationRelativeTo(null);
        setVisible(true);
    }
    private String getUsername(){
        return this.usernameField.getText();
    }
    private List<UserEventListener> userEventListeners = new ArrayList<>();

    // Method to subscribe to event
    public void subscribe(UserEventListener listener) {
        userEventListeners.add(listener);
    }

    // Method to unsubscribe from event
    public void unsubscribe(UserEventListener listener) {
        userEventListeners.remove(listener);
    }

    private void onGameStarted(){
        for (var listener : userEventListeners) {
            listener.onGameStart();
        }
    }
}
