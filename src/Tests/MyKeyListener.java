package Tests;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key press event
        int keyCode = e.getKeyCode();
//        System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode));
        System.out.println("Key pressed: " + keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key release event
        int keyCode = e.getKeyCode();
//        System.out.println("Key released: " + KeyEvent.getKeyText(keyCode));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed event
        char keyChar = e.getKeyChar();
//        System.out.println("Key typed: " + keyChar);
    }
}
