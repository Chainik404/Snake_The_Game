package Tests;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class KeyStrokeExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("KeyStroke Example");
            JPanel panel = new JPanel();

            MyKeyListener keyListener = new MyKeyListener();
            panel.addKeyListener(keyListener);
            panel.setFocusable(true); // Important: set the panel focusable to receive key events

            frame.add(panel);
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
