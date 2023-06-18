import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame implements UserEventListener{
    private GridPanel gridPanel;
    private Menu menu;
    private DataContext DataContext;
    public Frame(DataContext dataContext){
        this.DataContext = dataContext;

        setPreferredSize(new Dimension(Settings.FrameWidth,Settings.FrameHeight));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                DataContext.savePlayers();
            }
        });

        setTitle("Snake Game");

        this.gridPanel = new GridPanel(DataContext);

        this.menu = new Menu(DataContext);

        add(this.menu,BorderLayout.CENTER);

        add(this.gridPanel, BorderLayout.CENTER);

//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
//                int pressed = e.getKeyCode();
//                UserAction userAction;
//                switch (pressed){
//                    case(37): {
//                        userAction = UserAction.Left;
//                        break;
//                    }
//                    case (38):{
//                        userAction = UserAction.UP;
//                        break;
//                    }
//                    case (39):{
//                        userAction = UserAction.Right;
//                        break;
//                    }
//                    case (40):{
//                        userAction = UserAction.Down;
//                        break;
//                    }
//                    default: userAction =null;
//                }
//                OnUserAction();
//                synchronized (dataContext){
//                    dataContext.addMoveDirection(userAction);
//                }
//            }
//        });

        pack();
        setVisible(true);

    }
    public void showGame(){
        this.menu.setVisible(false);
        this.gridPanel.setVisible(true);
    }
    public void showMenu(){
        this.gridPanel.setVisible(false);
        this.menu.setVisible(true);
    }

    public void focusGame(){
        this.gridPanel.Focus();
    }

    public void focusMenu(){

    }



    public void updateGrid(DataContext dataContext){
        this.gridPanel.updateGrid(dataContext.getMap());
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

    private List<UserEventListener> userEventListeners = new ArrayList<>();

    // Method to subscribe to event
    public void subscribe(UserEventListener listener) {
        userEventListeners.add(listener);
        this.menu.subscribe(this);
    }

    // Method to unsubscribe from event
    public void unsubscribe(UserEventListener listener) {
        userEventListeners.remove(listener);
        this.menu.unsubscribe(this);
    }
    private void OnUserAction(){
        for (var listener : userEventListeners) {
            listener.onGameStart();
        }
    }
    private void onGameStarted(){
        for (var listener : userEventListeners) {
            listener.onGameStart();
        }
    }
    @Override
    public void onUserAction() {

    }

    @Override
    public void onGameStart() {
        showGame();
        onGameStarted();


    }

    @Override
    public void onGameStopped() {
        showMenu();
    }

    @Override
    public void onGameResume() {
        showGame();
    }

    @Override
    public void onGameEnd() {
        showMenu();
    }
}
