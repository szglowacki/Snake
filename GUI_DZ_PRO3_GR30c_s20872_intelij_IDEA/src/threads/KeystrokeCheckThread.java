package threads;

import board.BoardData;
import board.GameBoard;
import enums.GameStateEnum;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class KeystrokeCheckThread extends Thread {
   GameBoard gameBoard;
   Stage st;

    public KeystrokeCheckThread(GameBoard gameBoard ,Stage st)
    {
        this.gameBoard=gameBoard;
        this.st=st;
    }
    @Override
    public void run() {
        while (BoardData.gameState == GameStateEnum.PLAY)
        {

            if(BoardData.isShiftDown && BoardData.isCtrlDown && BoardData.isQDown)

            {
                BoardData.gameState = GameStateEnum.STOPPED;
                Platform.runLater(() ->
                {
                    try {
                        Scene startScene = new Scene(FXMLLoader.load(getClass().getResource("/menu/Menu.fxml")));
                       gameBoard.getScene().getWindow().hide();

                        Stage menuStage = new Stage();
                        menuStage.setTitle("Menu");
                        menuStage.setScene(startScene);
                        menuStage.setResizable(false);
                        menuStage.show();
                        BoardData.isQDown=false;
                        BoardData.isCtrlDown=false;
                        BoardData.isShiftDown=false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



        }
    }
}
