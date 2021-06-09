package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene startScene = new Scene(FXMLLoader.load(getClass().getResource("/menu/Menu.fxml")));
        primaryStage = new Stage();
        primaryStage.setTitle("Menu");
        primaryStage.setScene(startScene);
        primaryStage.setResizable(false);
        primaryStage.show();





    }
    public void stop()
    {
        System.out.println("Koniec");

    }


    public static void main(String[] args) {
        launch(args);

    }
}
