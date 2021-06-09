package board;


import enums.GameStateEnum;
import enums.KeyEnum;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import objects.SnakeField;
import threads.GameStateThread;
import threads.KeystrokeCheckThread;
import threads.ScoreCheckThread;
import threads.TimeRefreshThread;

import java.util.Random;

public class BoardControler extends Stage  {

     Rectangle yellowPoint = new Rectangle(BoardData.squareSize,BoardData.squareSize,Color.YELLOWGREEN);
     Rectangle redPoint = new Rectangle(BoardData.squareSize,BoardData.squareSize,Color.DARKRED);
     Rectangle currentPoint;

     public BoardControler()
    {
        GameBoard gameBoard = new GameBoard();
        BoardData.setScore(0);
        BoardData.setTimeCounter(0);

        setSnakePosition(gameBoard.board);

        Scene boardScene = new Scene(gameBoard,BoardData.boardWidth,BoardData.boardHeight);
        boardScene.setOnKeyPressed(e->
        {
            if(e.getCode()== KeyCode.D || e.getCode()==KeyCode.RIGHT)
            {
                BoardData.key= KeyEnum.RIGHT;
            }
            else if(e.getCode()==KeyCode.W || e.getCode()==KeyCode.UP)
            {
                BoardData.key=KeyEnum.TOP;
            }
            else if(e.getCode()==KeyCode.A || e.getCode()==KeyCode.LEFT)
            {
                BoardData.key=KeyEnum.LEFT;
            }
            else if(e.getCode()==KeyCode.S || e.getCode()==KeyCode.DOWN)
            {
                BoardData.key=KeyEnum.DOWN;
            }
            if(e.getCode() == KeyCode.SHIFT)
            {
                BoardData.isShiftDown=true;
            }
            if(e.getCode() == KeyCode.CONTROL)
            {
                BoardData.isCtrlDown=true;
            }
            if(e.getCode() == KeyCode.Q)
            {
                BoardData.isQDown=true;
            }
        });

        boardScene.setOnKeyReleased(e2->
        {
            if(e2.getCode() == KeyCode.SHIFT)
            {
                BoardData.isShiftDown=false;
            }
            if(e2.getCode() == KeyCode.CONTROL)
            {
                BoardData.isCtrlDown=false;
            }
            if(e2.getCode() == KeyCode.Q)
            {
                BoardData.isQDown=false;

            }
        });
        this.setScene(boardScene);
       new ScoreCheckThread(gameBoard).start();
       new TimeRefreshThread(gameBoard).start();
       new KeystrokeCheckThread(gameBoard,this).start();
       new GameStateThread(gameBoard).start();



    }



    public void setPoint(GridPane board)
    {
        board.getChildren().remove(yellowPoint);
        board.getChildren().remove(redPoint);
        Random rand = new Random();
        int randomColumn = rand.nextInt(BoardData.colCounter);
        int randomRow = rand.nextInt(BoardData.rowCounter);
        int randomPoint = rand.nextInt(10);
        BoardData.PointRow = randomRow;
        BoardData.PointCol= randomColumn;
        if(randomPoint!=1)
        {
            board.add(yellowPoint,randomColumn,randomRow);
            currentPoint=yellowPoint;
        }
        else
        {
            board.add(redPoint,randomColumn,randomRow);
            currentPoint=redPoint;
        }

    }

    public void setSnakePosition(GridPane gp)
    {
        SnakeField.snakeList.clear();
        SnakeField snakeHead = new SnakeField(BoardData.colCounter/2,BoardData.rowCounter/2);
        setPoint(gp);
        BoardData.gameState=GameStateEnum.PLAY;
        Thread thread = new Thread(

            ()->
            {

               while(BoardData.gameState == GameStateEnum.PLAY) {
                   for (int i = SnakeField.snakeList.size() - 1; i >= 0; i--) {
                        SnakeField sf = SnakeField.snakeList.get(i);
                        if(i!=0)
                        {
                            int prevCol = SnakeField.snakeList.get(i-1).getColumn();
                            int prevRow = SnakeField.snakeList.get(i-1).getRow();
                            sf.setColumn(prevCol);
                            sf.setRow(prevRow);
                            removeField(gp,sf.field);
                            changeFieldPosition(sf.getColumn(),sf.getRow(),gp,sf.field);


                        }
                        else
                        {
                            switch(BoardData.key)
                            {
                                case RIGHT: sf.setColumn(sf.getColumn()+1); break;
                                case LEFT: sf.setColumn(sf.getColumn()-1); break;
                                case TOP: sf.setRow(sf.getRow()-1); break;
                                case DOWN: sf.setRow(sf.getRow()+1); break;
                                default:
                            }

                            removeField(gp,sf.field);
                            changeFieldPosition(sf.getColumn(),sf.getRow(),gp,sf.field);
                        }
                    }

                    SnakeField head = SnakeField.snakeList.get(0);
                    SnakeField tail = SnakeField.snakeList.get(SnakeField.snakeList.size()-1);

                    if(head.getColumn()==BoardData.PointCol && head.getRow()==BoardData.PointRow)
                    {
                       BoardData.setScore(BoardData.getScore()+BoardData.pointsForField);

                        if(currentPoint.equals(yellowPoint)) new SnakeField(tail.getColumn(),tail.getRow());
                        else if(currentPoint.equals(redPoint) && SnakeField.snakeList.size()>1)
                        {
                            SnakeField.snakeList.remove(tail);
                            Platform.runLater(()->removeField(gp,tail.field));
                        }
                        Platform.runLater(()->
                                setPoint(gp));
                        System.out.println(SnakeField.snakeList);
                    }

                   for(int j = 1; j<SnakeField.snakeList.size();j++)
                   {
                       if((SnakeField.snakeList.get(0).getRow() == SnakeField.snakeList.get(j).getRow()) && (SnakeField.snakeList.get(0).getColumn()==SnakeField.snakeList.get(j).getColumn() ) && SnakeField.snakeList.size()>2)
                       {
                           BoardData.gameState=GameStateEnum.LOSE;

                       }
                   }

                   if(head.getColumn()>=BoardData.colCounter || head.getColumn()<0 || head.getRow() >= BoardData.rowCounter || head.getRow() <0)
                   {
                       BoardData.gameState = GameStateEnum.LOSE;
                   }

                   if(SnakeField.snakeList.size()==(BoardData.rowCounter*BoardData.colCounter))
                   {
                       BoardData.gameState=GameStateEnum.WIN;
                   }

                   try {
                       Thread.sleep(500);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }




                }


            }
    );
    thread.start();
    }

    public void changeFieldPosition(int col,int row,GridPane gp,Rectangle rec)
    {

      Platform.runLater(()->
            gp.add(rec, col, row));

    }


    public void removeField(GridPane gp,Rectangle rec)
    {
        Platform.runLater(()->gp.getChildren().remove(rec));
    }

}
