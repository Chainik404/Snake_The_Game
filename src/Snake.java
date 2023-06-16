import java.util.List;
import java.util.ArrayList;

public class Snake{ //implements IMoveAble {
    ArrayList<Cell> body = new ArrayList<>(3);
    Direction direction = Direction.UP;
    Cell apple = new Cell(10,10,CellType.Apple);
    public Snake(){
        int headRow = Settings.ROWS-4;
        int headCol = Settings.COLS/2;
        body.add(new Cell(headRow, headCol, CellType.Snake));
        body.add(new Cell(headRow+1, headCol, CellType.Snake));
        body.add(new Cell(headRow+2, headCol, CellType.Snake));
    }


    public boolean move() {
        int len1 = this.body.size();

        boolean isApple = this.apple.same(this.body.get(0)); // len1 - 1

        for (int i = 0; i < len1 - 1; i++) {
            Cell cell_d = this.body.get(len1 - 1 - i);
            Cell cell_u = this.body.get(len1 - 2 - i);
            cell_d.changeCords(cell_u);
        }

        // Change head cell position based on direction
        int shift_row = 0;
        int shift_col = 0;

        if (this.direction == Direction.UP) {
            shift_row = -1;
        }
        if (this.direction == Direction.Down) {
            shift_row = 1;
        }
        if (this.direction == Direction.Left) {
            shift_col = -1;
        }
        if (this.direction == Direction.Right) {
            shift_col = 1;
        }

        this.body.get(0).shift(shift_row, shift_col);
        if (checkColision()){
            return false;
        }
        if (isApple) {
            Cell c_new = new Cell(this.apple.row, this.apple.col,CellType.Snake);
            this.body.add(c_new);
            boolean ff = this.findNewApple();

            while (!ff) {
                ff = this.findNewApple();
            }
        }
        return true;
    }
    public void turn(Direction direction){
        this.direction = direction;
    }
    public boolean findNewApple() {

        int cc = (int)(Math.random()*Settings.COLS);
        int rr = (int)(Math.random()*Settings.ROWS);
        boolean find = true;
        int len1 = this.body.size();

        for (int i = 0; i < len1 - 1; i++) {
            if (this.body.get(i).same(cc, rr)) {
                find = false;
            }
        }

        if (find) {
            this.apple.col = cc;
            this.apple.row = rr;
        }

        return find;
    }
    public boolean checkColision(){
        for (int i = 1; i < body.size(); i++) {
            Cell head = body.get(0);
            if (this.body.get(i).same(head)){
                return true;
            }
        }
        return false;
    }

    //#region OnChanged listeners routines
    private List<SnakeEventsListener> snakesListeners = new ArrayList<>();

    // Method to subscribe to event
    public void addOnChangeListener(SnakeEventsListener listener) {
        snakesListeners.add(listener);
    }

    // Method to unsubscribe from event
    public void delOnChangeListener(SnakeEventsListener listener) {
        snakesListeners.remove(listener);
    }

    // Method to fire event
    private void OnChangedData() {
        for (var listener : snakesListeners) {
            listener.onChanged(this);
        }
    }

    private void OnAppleAdded(){
        for (var listener : snakesListeners) {
            listener.onNewApple(this);
        }
    }
    //#endregion
}
