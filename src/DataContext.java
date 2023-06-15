import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DataContext {
    Snake Snake;
    Map Map;
    Queue<Direction> UserActions;
    public boolean Exit;
    private boolean isChanged;
    private List<CustomEventListener> listeners = new ArrayList<>();

    public DataContext(){
        UserActions = new LinkedList<>();
        this.Snake = new Snake();
        this.Map = new Map();
    }

    public boolean Move(){
        return Snake.move();
    }

    public void addMoveDirection(Direction direction){
        UserActions.add(direction);
        this.isChanged = true;
    }

    public int[][] getMap() {
        return Map.getMap();
    }

    public Direction getUserAction(){
        if(UserActions.size() >0){
            return UserActions.poll();
        }
        return this.Snake.direction;
    }
    public boolean getChanged(){
        return isChanged;
    }
    public ArrayList<Cell> getSnakeBody(){
        return Snake.body;
    }

    public void printMap() {
        this.Map.show();
    }
    public void updateMap(){
        this.Map.updateMap(this.Snake);
    }

    public void addAppleListener(){
        Snake.findNewApple();

    }
}
