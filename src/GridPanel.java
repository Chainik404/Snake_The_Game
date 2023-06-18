import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GridPanel extends JPanel {
    int rows;
    int cols;
    DataContext dataContext;
    public GridPanel(DataContext dataContext){
        this.dataContext = dataContext;
        this.rows = Settings.ROWS;
        this.cols = Settings.COLS;
        setLayout(new GridLayout(rows,cols));
        for (int i = 0; i < rows * cols; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.black);
            panel.setBorder(BorderFactory.createLineBorder(Color.white));
            add(panel);
        }
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int pressed = e.getKeyCode();
                UserAction userAction;
                switch (pressed){
                    case(37): {
                        userAction = UserAction.Left;
                        break;
                    }
                    case (38):{
                        userAction = UserAction.UP;
                        break;
                    }
                    case (39):{
                        userAction = UserAction.Right;
                        break;
                    }
                    case (40):{
                        userAction = UserAction.Down;
                        break;
                    }
                    default: userAction =null;
                }
//                OnUserAction();
                synchronized (dataContext){
                    dataContext.addMoveDirection(userAction);
                }
            }
        });
    }
    public void vipe(){
        for (Component c: getComponents()) {
            c.setBackground(Color.BLACK);
        }
    }
    public void updateCords(IDrawable object){
        int row = object.getRow();
        int col = object.getCol();
        Component component = getComponent(row*cols + col);
        component.setBackground(object.getColor());
    }
    public void updateGrid(int[][] map){
        vipe();
        Component[] components = getComponents();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                var panel = components[i*this.cols + j];
                switch (map[i][j]){
                    case 1:{
                         panel.setBackground(Settings.SnakeColor);
                        break;
                    }
                    case 2:{
                        panel.setBackground(Settings.AppleColor);
                        break;
                    }
                }
            }
        }
    }
    public void Focus(){
        grabFocus();
    }
}
