import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DataContext implements SnakeEventsListener,UserEventListener {
    Snake Snake;
    Map Map;
    Queue<UserAction> UserActions;
    public boolean Exit;
    private boolean isChanged;
    private boolean isGame;

    public void setGame(boolean game) {
        isGame = game;
    }

    public boolean isGame() {
        return isGame;
    }

    private ArrayList<Player> players = new ArrayList<>();


    public DataContext(){
        UserActions = new LinkedList<>();
        this.Snake = new Snake();
        this.Map = new Map();
    }

    public boolean snakeMove(){
         this.isGame = Snake.move();
         return this.isGame;
    }

    public void addMoveDirection(UserAction userAction){

        UserActions.add(userAction);
        this.isChanged = true;
    }

    public int[][] getMap() {
        return Map.getMap();
    }

    public UserAction getUserAction(){
        if(UserActions.size() >0){
            return UserActions.poll();
        }
        this.isChanged = false;
        return this.Snake.direction;
    }
    public boolean ischanged(){
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

    public void addPlayer(String name){
        this.players.add(new Player(players.size(),name));
    }
    public void removePlayer(Player player){
        this.players.remove(player);
    }


    private List<SnakeEventsListener> snakesListeners = new ArrayList<>();

    // Method to subscribe to event
    public void subscribe(SnakeEventsListener listener) {
        snakesListeners.add(listener);
        this.Snake.subscribe(this);
    }

    // Method to unsubscribe from event
    public void unsubscribe(SnakeEventsListener listener) {
        snakesListeners.remove(listener);
        this.Snake.unsubscribe(this);

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
    private void OnSnakeGrow(){
        for (var listener : snakesListeners) {
            listener.onSnakeGrow(this);
        }
    }
    private void OnSnakeTurn(){
        for (var listener : snakesListeners) {
            listener.onSnakeTurn(this);
        }
    }
    private void OnSnakeMove(){
        for (var listener : snakesListeners) {
            listener.onMove(this);
        }
    }

    @Override
    public void onChanged(Object sender) {
        updateMap();
    }

    @Override
    public void onUserAction(Object sender, KeyStrokes stroke) {
        updateMap();
    }

    @Override
    public void onSnakeTurn(Object sender) {
        System.out.println("turned");
        updateMap();

    }

    @Override
    public void onSnakeBlock(Object sender) {
        System.out.println("DEAD!!!");
        updateMap();

    }

    @Override
    public void onSnakeGrow(Object sender) {
        System.out.println("growed");

        updateMap();
    }

    @Override
    public void onNewApple(Object sender) {
        System.out.println("Aple Relocated");

        updateMap();

    }

    @Override
    public void onMove(Object sender) {
        System.out.print("+");

        updateMap();

    }

    @Override
    public void onUserAction() {

    }

    @Override
    public void onGameStart() {
        this.isGame = true;
    }

    @Override
    public void onGameStopped() {
        this.isGame = false;
    }

    @Override
    public void onGameResume() {
        this.isGame =false;
    }

    @Override
    public void onGameEnd() {

    }
}
