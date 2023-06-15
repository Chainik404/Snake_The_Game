import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    int rows;
    int cols;
    public GridPanel(){
        this.rows = Settings.ROWS;
        this.cols = Settings.COLS;
        setLayout(new GridLayout(rows,cols));
        for (int i = 0; i < rows * cols; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.black);
            panel.setBorder(BorderFactory.createLineBorder(Color.white));
            add(panel);
        }
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
}
