package objects;

import board.BoardData;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class SnakeField {
    public static ArrayList<SnakeField> snakeList = new ArrayList<>(1);
    public Rectangle field = new Rectangle(BoardData.squareSize,BoardData.squareSize, Color.FORESTGREEN);
    int column;
    int row;

    public SnakeField(int c,int r)
    {

        column=c;
        row=r;
        snakeList.add(this);

    }

    public void setColumn(int c)
    {
        column = c;
    }
    public void setRow(int r)
    {
        row = r;
    }

    public int getColumn()
    {
        return  column;
    }

    public int getRow()
    {
        return row;
    }



}
