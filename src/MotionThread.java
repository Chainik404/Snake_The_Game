import java.lang.Thread;

public class MotionThread implements Runnable {


    private long SleepTime;
    private long SpeedTime;
    private DataContext Context;
    private UIContext UIContext;
    public MotionThread(long sleepTime,long speedTime, DataContext context, UIContext uiContext){
        this.Context = context;
        this.UIContext = uiContext;
        this.SleepTime = sleepTime;
        this.SpeedTime = speedTime;
//        this.Context.subscribe(Context);
        this.Context.subscribe(UIContext);
        this.UIContext.subscribe(Context);
    }

    public void run() {
        int time = 0;
        //add move listener;
        while(true){

            synchronized (Context) {
                if (Context.Exit) {
                    return;
                }
                time++;
                //time manage
                if(Context.isGame() && time%this.SpeedTime == 0){
                    Context.snakeMove();
//                    this.isGame = Context.isGame();
                    Context.updateMap();
                    Context.printMap();
                    time=0;
                }
            }
            synchronized (UIContext){
                UIContext.updateGrid();
            }

//                try {
//                    SwingUtilities.invokeLater(new Runnable() {
//                        public void run() {
//                            UIContext.pocessAction();
//                            // Update UI components here
//                            // label.setText("Update complete");
//
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            try {
                Thread.sleep(this.SleepTime);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }
}

