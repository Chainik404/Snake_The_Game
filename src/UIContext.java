import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UIContext implements SnakeEventsListener, UserEventListener{
    public Frame frame;
    DataContext DataContext;
    public UIContext(DataContext dataContext){
        frame  = new Frame(dataContext);
        frame.showMenu();
        this.DataContext = dataContext;
    }

    public void pocessAction() {
        synchronized (DataContext) {
            if (DataContext.ischanged()) {
                var userAction = this.DataContext.getUserAction();
                DataContext.Snake.turn(userAction);
            }
        }
//            frame.update();
        updateGrid();
    }
    public void updateGrid(){
//        synchronized (DataContext) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    frame.updateGrid(DataContext);
                    pocessAction();
                    // Update UI components here
                    // label.setText("Update complete");

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

//        }
    }

    @Override
    public void onChanged(Object sender) {
        updateGrid();
    }

    @Override
    public void onUserAction(Object sender, KeyStrokes stroke) {
        updateGrid();
    }

    @Override
    public void onSnakeTurn(Object sender) {
        updateGrid();
    }

    @Override
    public void onSnakeBlock(Object sender) {
        updateGrid();


    }

    @Override
    public void onSnakeGrow(Object sender) {
        updateGrid();
    }

    @Override
    public void onNewApple(Object sender) {
        updateGrid();
    }

    @Override
    public void onMove(Object sender) {
        updateGrid();
    }



    private List<UserEventListener> userEventListeners = new ArrayList<>();

    // Method to subscribe to event
    public void subscribe(UserEventListener listener) {
        userEventListeners.add(listener);
        this.frame.subscribe(this);
    }

    // Method to unsubscribe from event
    public void unsubscribe(UserEventListener listener) {
        userEventListeners.remove(listener);
        this.frame.unsubscribe(this);
    }

    public void OnGameStoped() {
        for (var listener : userEventListeners) {
            listener.onGameStopped();
        }
    }
    private void OnGameStarted(){
        for (var listener : userEventListeners) {
            listener.onGameStart();
        }
    }

    @Override
    public void onUserAction() {

    }

    @Override
    public void onGameStart() {
        OnGameStarted();
        this.frame.focusGame();
    }

    @Override
    public void onGameStopped() {

    }

    @Override
    public void onGameResume() {

    }

    @Override
    public void onGameEnd() {

    }
}
