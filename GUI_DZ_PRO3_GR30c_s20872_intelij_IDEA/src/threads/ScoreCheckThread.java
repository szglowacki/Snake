package threads;

import board.BoardData;
import board.GameBoard;
import enums.GameStateEnum;

public class ScoreCheckThread extends Thread {
    GameBoard gameBoard;
    public ScoreCheckThread(GameBoard gameBoard)
    {
        this.gameBoard=gameBoard;
    }
    public void run()
    {
        while(BoardData.gameState == GameStateEnum.PLAY) {


            gameBoard.score.setText("Punkty: " + BoardData.getScore());
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
