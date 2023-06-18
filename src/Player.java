public class Player {
    private int id;
    private String name;
    private int bestScore;
    public Player(int id,String name){
        this.id = id;
        this.name = name;
    }
    public void setBestScore(int score){
        this.bestScore = score;
    }

}
