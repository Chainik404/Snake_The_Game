public interface UserEventListener {
    void onUserAction();
    void onGameStart();
    void onGameStopped();
    void onGameResume();
    void onGameEnd();


}
