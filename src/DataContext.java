import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class DataContext implements SnakeEventsListener,UserEventListener {
    Snake Snake;
    Map Map;
    Queue<UserAction> UserActions;
    public boolean Exit;
    private boolean isChanged;
    private boolean isGame;
    private List<Player> players = new ArrayList<>();
    private Player curentPlayer;
    private int currentScore = 0;

    public UserAction getSnakeOpositeDirection(){
        return this.Snake.getOpositeDirection();
    }
    public void setSnakeOpositeDirection(UserAction direction){
        this.Snake.setOpositeDirection(direction);
    }

    public void setGame(boolean game) {
        isGame = game;
    }

    public boolean isGame() {
        return isGame;
    }


    public DataContext(){
        UserActions = new LinkedList<>();
        this.Snake = new Snake();
        this.Map = new Map();
        loadPlayers();
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
        Player p = new Player(name);
        this.players.add(p);
        this.curentPlayer = p;
    }
    public void removePlayer(Player player){
        this.players.remove(player);
    }
    public void savePlayers(){
        Collections.sort(this.players);

        try {
            var folder =Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            var filePath = folder + "data.bin";
            FileOutputStream fos = new FileOutputStream(filePath);
            for (int i = 0; i < (players.size()>10? 9:players.size()); i++) {
                players.get(i).save(fos);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private void loadPlayers(){
        try {
            var folder =Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            var filePath = folder + "data.bin";
            File file = new File(filePath);        
            if (file.exists()) {
                FileInputStream fis  = new FileInputStream(filePath);
                while (fis.available()>0){
                    var player = Player.load(fis);
                    this.players.add(player);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Player p : players) {
            System.out.println(p);
        }
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
    private void OnSnakeBlock(){
        for (var listener : snakesListeners) {
            listener.onSnakeBlock(this);
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
        this.isGame = false;
        curentPlayer.setBestScore(currentScore);
        OnSnakeBlock();

        updateMap();

    }

    @Override
    public void onSnakeGrow(Object sender) {
        System.out.println("snake growth");
        currentScore++;
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
        this.currentScore = 0;
        this.Snake.reset();
        this.curentPlayer = players.get(players.size());

    }

    @Override
    public void onGameStopped() {
        this.isGame = false;
    }

    @Override
    public void onGameResume() {
        this.isGame =true;
    }

    @Override
    public void onGameEnd() {
        this.isGame = false;
    }
}
