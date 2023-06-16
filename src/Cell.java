import java.awt.*;

enum CellType{
    Snake,
    Apple,

}

public class Cell implements IDrawable {

    int row, col;
    CellType type;
    Color color;
    public Cell(int row , int col, CellType type){
        this.col = col;
        this.row = row;
        this.type = type;
        switch (this.type){
            case Apple -> {color = Settings.AppleColor;}
            case Snake -> {color = Settings.SnakeColor;}
        }
    }

    public void changeCords(Cell cell){
        this.col = cell.col;
        this.row = cell.row;
    }
    public void shift(int shiftRow,int shiftCol){
        this.col += shiftCol;
        this.row += shiftRow;
        if (row >= Settings.ROWS){
            row = 0;
        }
        else if (row < 0){
            row = Settings.ROWS-1;
        }
        if (col >= Settings.COLS){
            col = 0;
        }
        else if (col<0) {
            col = Settings.COLS-1;
        }
    }
    public boolean same(Cell cell){
        return (this.col == cell.col && this.row == cell.row);
    }

    public boolean same(int row, int col){
        return (this.col == col && this.row == row);
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public int getCol() {
        return this.col;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
