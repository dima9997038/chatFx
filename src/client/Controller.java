package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;


/**
 * Created by dima on 15.11.2018.
 */
public class Controller {
   @FXML
   public TextField name;
   @FXML
   public TextField field_message;


    @FXML
   public TextArea field_msg;

    public static String strout;

    public Controller() {

    }

    public void Send(ActionEvent actionEvent) {
        String nick=name.getText();
        System.out.println(nick);
        String message=field_message.getText();
        System.out.println(message);

        new ClientSomthing("localhost", 3443, nick,message);
        wrt(strout);
    }

    public static void writeMsgText(String s){
       strout=strout+"\n"+s;
        System.out.println(s+"???");

    }

    public void wrt(String s){
        field_msg.setText(s);
    }
}
