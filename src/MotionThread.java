import javax.swing.*;
import java.lang.Thread;
import java.lang.reflect.InvocationTargetException;
import java.util.Queue;

public class MotionThread implements Runnable {


    private long SleepTime;
    private long SpeedTime;
    private DataContext Context;
    private UIContext UIContext;
    private boolean isGame;
    public MotionThread(long sleepTime,long speedTime, DataContext context, UIContext uiContext){
        this.Context = context;
        this.UIContext = uiContext;
        this.SleepTime = sleepTime;
        this.SpeedTime = speedTime;
        this.isGame = true;

    }

    public void run() {
        int time = 0;
        while(isGame){
            synchronized (Context) {
                if (Context.Exit) {
                    return;
                }
                time++;
                //time manage
                if(time == this.SpeedTime){
                    this.isGame = Context.Move();
                    Context.updateMap();
//                    Context.printMap();
                    time=0;
                }
            }


                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            UIContext.pocessAction();
                            // Update UI components here
                            // label.setText("Update complete");

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            try {
                Thread.sleep(this.SleepTime);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }
}

