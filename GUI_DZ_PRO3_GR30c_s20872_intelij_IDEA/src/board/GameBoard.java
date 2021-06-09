package board;


import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



public class GameBoard extends Group {

    public  GridPane board = new CenterPane();
    public Text score = new Text("Wynik: ");
    public Text time = new Text("Czas: ");
    public GameBoard()
    {


        BorderPane bl= new BorderPane();
        GridPane info =new GridPane();
        info.setPrefHeight(40);
        this.getChildren().add(bl);
        bl.setTop(info);
        bl.setCenter(board);
        score.setFont(new Font(15));
        time.setFont(new Font(15));
        score.setFill(Color.BLACK);
        time.setFill(Color.BLACK);



        info.add((new Rectangle(BoardData.boardWidth,20,Color.DARKGRAY)),0,0);
        info.add((new Rectangle(BoardData.boardWidth,20,Color.DARKGRAY)),0,1);
        info.add(score,0,0);
        info.add(time,0,1);


      //  BoardControler.setPoint(board);

    }

    public GridPane getBoard()
    {
        return board;
    }



}
