package client2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static String ipAddr = "localhost";
    public static int port = 8080;
    public static volatile Controller myControllerHandle;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        loader.setController(myControllerHandle);
        loader.setRoot(myControllerHandle);
        Parent root =(Parent) loader.load();
       myControllerHandle =(Controller) loader.getController();
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
          new ClientSomthing("localhost", 3443);
        launch(args);
    }
}
