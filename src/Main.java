import Tests.Test1;
public class Main {
    public static void main(String[] args) {
        DataContext dataContext  =new DataContext();
        UIContext uiContext = new UIContext(dataContext);

        MotionThread motionThread = new MotionThread(10,5,dataContext,uiContext);
        motionThread.run();

    }
}