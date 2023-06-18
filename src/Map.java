import java.util.Arrays;

public class Map {
    private int[][] map;
    public Map(){
        this.map = new int[16][26];
    }
    public void addSnake(Snake snake){
        boolean first = true;
        for (Cell cell: snake.body) {
            if (first) {
                map[cell.row][cell.col] = 3;
                first = false;
            } else {
                map[cell.row][cell.col] = 1;
            }
        }
        Cell apple = snake.apple;
        map[apple.row][apple.col] = 2;
    }

    public void show(){
        for (int[] row: map) {
            for (int cell: row) {
                System.out.print(cell + "|");
            }
            System.out.print("\n");
        }
        System.out.println("================================================================================");
    }
    public void vipe(){
        for (int[] row : map) {
            Arrays.fill(row,0);
        }
    }
    public void updateMap(Snake snake){
        vipe();
        addSnake(snake);

    }
    public int[][] getMap(){
        return map;
    }

}
