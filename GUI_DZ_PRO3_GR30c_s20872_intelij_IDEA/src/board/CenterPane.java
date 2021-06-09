package board;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class CenterPane extends GridPane {
    public CenterPane()
    {
        for(int i = 0; i <BoardData.rowCounter;i++)
        {
            for(int j = 0; j < BoardData.colCounter;j++)
            {


                Pane p = new Pane();

                p.setPrefSize(BoardData.squareSize,BoardData.squareSize);

                p.setBorder(new Border(new BorderStroke(Color.GREY,BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));
                p.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


                this.add(p,j,i);
            }
        }

    }


}
