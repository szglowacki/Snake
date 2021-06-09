package settings;

import board.BoardControler;
import board.BoardData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SettingsControler {
    @FXML
    private Pane settingsPane;
    @FXML
    private Button settingsButton;
    @FXML
    private Slider rowSlider;
    @FXML
    private Slider colSlider;


    public void acceptSettings()
    {
       BoardData.colCounter = (int)colSlider.getValue();
       BoardData.rowCounter = (int)rowSlider.getValue();
       BoardData.countDimensions();
       settingsPane.getScene().getWindow().hide();
       Stage gameStage = new BoardControler();
       gameStage.setResizable(false);
       gameStage.setTitle("Snake");
       gameStage.show();
    }

}
