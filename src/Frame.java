import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class Frame extends JFrame {
    GridPanel gridPanel;
    Menu menu;
    DataContext DataContext;
    public Frame(DataContext dataContext){
        this.DataContext = dataContext;

        setPreferredSize(new Dimension(Settings.FrameWidth,Settings.FrameHeight));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Snake Game");

        this.gridPanel = new GridPanel();

        this.menu = new Menu();

        add(this.menu,BorderLayout.CENTER);

//        add(this.gridPanel, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int pressed = e.getKeyCode();
                Direction direction;
                switch (pressed){
                    case(37): {
                        direction = Direction.Left;
                        break;
                    }
                    case (38):{
                        direction = Direction.UP;
                        break;
                    }
                    case (39):{
                        direction = Direction.Right;
                        break;
                    }
                    case (40):{
                        direction = Direction.Down;
                        break;
                    }
                    default: direction =null;
                }
                synchronized (dataContext){
                    dataContext.addMoveDirection(direction);
                }
            }
        });

        pack();
        setVisible(true);

    }

    public void update(){
        synchronized (DataContext) {
//            if(DataContext.getChanged()) {
                this.gridPanel.vipe();
                for (IDrawable comp : DataContext.getSnakeBody()) {
                    this.gridPanel.updateCords(comp);
                }
//            }
        }
    }
}
