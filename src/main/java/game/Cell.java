package game;

import lombok.Data;

@Data
public class Cell {

    Cell(int color) {
        this.color = color;
    }
    //0 - Empty, 1 - Red, 2 - Green, 3 - Blue, 4 - Yellow
    private int color = 0;

}