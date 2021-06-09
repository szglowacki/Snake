package endGameWindow;

import board.BoardData;
import enums.GameStateEnum;
import highscore.Score;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EndGameController implements Initializable {
    String gameState;
    @FXML
    private Pane endGamePane;
     @FXML
    private Text endGameTitle;
     @FXML
    private Text endGameScore;
     @FXML
    private Button endGameButton;
     @FXML
     private TextField chooseNick;

     public void endGame()
     {
     String nick = chooseNick.getText();
      System.out.print(nick);


         if(BoardData.gameState==GameStateEnum.WIN)
             gameState = "WIN";
         else if(BoardData.gameState==GameStateEnum.LOSE)
             gameState="LOSE";

         Score score = new Score(nick,BoardData.finalScore,gameState);
         score.addScore();

         try{
             Scene highscoreScene = new Scene(FXMLLoader.load(getClass().getResource("/highscore/HighscoreWindow.fxml")));
             Stage highscoreStage = new Stage();
             highscoreStage.setTitle("Ranking");
             highscoreStage.setScene(highscoreScene);
             highscoreStage.setResizable(false);
             highscoreStage.show();
             endGamePane.getScene().getWindow().hide();
         } catch (IOException e) {
             e.printStackTrace();
         }




     }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         if(BoardData.gameState== GameStateEnum.WIN)
         {
             endGameTitle.setText("YOU WON");
             endGameTitle.setFill(Color.LIMEGREEN);
         }
        BoardData.setFinalScore();
        endGameScore.setText(""+ BoardData.finalScore);

    }


}
