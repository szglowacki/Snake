package threads;

import board.BoardData;
import board.GameBoard;
import enums.GameStateEnum;

public class TimeRefreshThread extends Thread {
    GameBoard gb;
    public TimeRefreshThread(GameBoard gb)
    {
        this.gb=gb;
    }

    @Override
    public void run() {
        while(BoardData.gameState== GameStateEnum.PLAY)
        {
            BoardData.setTimeCounter(BoardData.getTimeCounter()+1);
            gb.time.setText("Czas: "+BoardData.getTimeCounter());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
