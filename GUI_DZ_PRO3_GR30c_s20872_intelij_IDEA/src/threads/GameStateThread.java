package threads;

import board.BoardData;
import board.GameBoard;
import enums.GameStateEnum;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStateThread extends Thread {
   private boolean continueChecking = true;
   GameBoard gb;

   public GameStateThread(GameBoard gb)
   {
       this.gb = gb;
   }

    @Override
    public void run() {
        while (continueChecking) {


            if (BoardData.gameState == GameStateEnum.LOSE || BoardData.gameState == GameStateEnum.WIN) {
                Platform.runLater(() ->
                {
                    gb.getScene().getWindow().hide();
                    try {
                        Scene endGameScene = new Scene(FXMLLoader.load(getClass().getResource("/endGameWindow/EndGame.fxml")));
                        Stage endGameStage = new Stage();
                        endGameStage.setScene(endGameScene);
                        endGameStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                continueChecking = false;


            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    }
