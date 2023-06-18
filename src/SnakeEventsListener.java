public interface SnakeEventsListener {
    void onChanged(Object sender);
    void onUserAction(Object sender, KeyStrokes stroke);
    void onSnakeTurn(Object sender) ; // Direciotn argument
    void onSnakeBlock(Object sender);
    void onSnakeGrow(Object sender);
    void onNewApple(Object sender);
    void onMove(Object sender);
}
