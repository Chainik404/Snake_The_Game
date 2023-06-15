import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    JPanel topPanel;
    JPanel botomPanel;
    private JTextField usernameTextField;
    public Menu() {



        JLabel titleLabel = new JLabel("Snake Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);


        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout());

        JLabel usernameLabel = new JLabel("Username:");
        formPanel.add(usernameLabel);

        usernameTextField = new JTextField(15);
        formPanel.add(usernameTextField);

        JButton startButton = new JButton("Start");

        formPanel.add(startButton,BorderLayout.CENTER);

        add(formPanel,BorderLayout.CENTER);
////        setPreferredSize(new Dimension(Settings.FrameWidth,Settings.FrameHeight));
//        setBackground(Color.MAGENTA);
//
//        this.topPanel = new JPanel(new BorderLayout());
//        this.botomPanel = new JPanel(new BorderLayout());
//
//
//        this.topPanel.setBackground(Color.BLACK);
//        this.topPanel.setBounds(0,0,getWidth(),getHeight()/2);
//
//        this.botomPanel.setBackground(Color.RED);
////        this.botomPanel.setSize(getWidth(),getHeight()/2);
//
//
//
//        JLabel gameLabel = new JLabel();
//       gameLabel.setText("Snake");
//       gameLabel.setFont(new Font("Serif", Font.ITALIC, 24));
//
//       JTextField textField = new JTextField("Name");
//       textField.setPreferredSize(new Dimension(botomPanel.getWidth()/2,textField.getHeight()));
//
//       JButton button = new JButton("Start");
//
//       this.topPanel.add(gameLabel,BorderLayout.CENTER);
////
//       this.botomPanel.add(textField,BorderLayout.NORTH);
//       this.botomPanel.add(button,BorderLayout.SOUTH);
//
//       add(this.topPanel,BorderLayout.NORTH);
//       add(this.botomPanel, BorderLayout.SOUTH);
    }
}
