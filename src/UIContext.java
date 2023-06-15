public class UIContext {
    public Frame frame;
    DataContext DataContext;
    public UIContext(DataContext dataContext){
        frame  = new Frame(dataContext);
        this.DataContext = dataContext;
    }

    public void pocessAction() {
        synchronized (DataContext) {
                var userAction = this.DataContext.getUserAction();
                DataContext.Snake.turn(userAction);
        }
//            frame.update();
        updateGrid();
    }
    public void updateGrid(){
        synchronized (DataContext) {
            this.frame.gridPanel.updateGrid(DataContext.Map.getMap());
        }
    }
}
