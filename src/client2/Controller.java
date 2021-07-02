package client2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.EventListener;


public class Controller  {


    @FXML
    public TextField name;
    @FXML
    public TextField field_message;
    @FXML
    public TextArea field_msg;
    @FXML
    public Button SendMessage;


    public static String strin;


    public void initialize(){
      //  Main.myControllerHandle.name.setText("ccbbns hnghh");
      //  Tmp.getController();
      //  Tmp.setField_msg("333");
    }

    public static void setStrin(String strin) {
        Controller.strin = strin;

    }



    public static void writeMsgText(String s){
        Platform.runLater(()->{
            Main.myControllerHandle.field_msg.appendText(s);
        });
     }




    public void Send(ActionEvent actionEvent) {

    }



}



