package highscore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HighscoreWindowController implements Initializable {
    ObservableList<String> scoresList =  FXCollections.observableArrayList();

    @FXML
    private ListView<String> rankingList;
    @FXML
    private Button backToMenu;
    @FXML
     private VBox highscoreVBox;



    public void goBack()
    {

        try {
            Scene menuScene = new Scene(FXMLLoader.load(getClass().getResource("/menu/Menu.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Menu");
            menuStage.setScene(menuScene);
            menuStage.setResizable(false);
            menuStage.show();
            highscoreVBox.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        Score.sortedScores.clear();

        Score.sortScores();
        loadData();
        rankingList.setItems(scoresList);

    }

    private void loadData()
    {
        int i = 0;


        for(Score s : Score.sortedScores)
        {
            System.out.println(Score.sortedScores.get(i));
            String temp = (i+1)+". "+s.toString();
            scoresList.add(temp);
            i++;
        }
    }


}
