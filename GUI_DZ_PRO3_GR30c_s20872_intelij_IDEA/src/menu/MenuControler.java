package menu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuControler {
    @FXML
    private Button newGameButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button highScoresButton;
    @FXML
    private BorderPane menuPane;

    public void startNewGame()
    {
        try {
            Scene settingsScene = new Scene(FXMLLoader.load(getClass().getResource("/settings/ChooseSettings.fxml")));
            Stage settingsStage = new Stage();
            settingsStage.setTitle("Ustawienia");
            settingsStage.setScene(settingsScene);
            settingsStage.setResizable(false);

            settingsStage.show();
            menuPane.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void openHighScores()
    {

        try {
            Scene highscoreScene = new Scene(FXMLLoader.load(getClass().getResource("/highscore/HighscoreWindow.fxml")));
            Stage highscoreStage = new Stage();
            highscoreStage.setTitle("Ranking");
            highscoreStage.setScene(highscoreScene);
            highscoreStage.setResizable(false);
            highscoreStage.show();
            menuPane.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exitGame()
    {
        Platform.exit();
    }


}
