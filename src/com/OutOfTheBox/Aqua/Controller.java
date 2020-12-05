package com.OutOfTheBox.Aqua;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Controller {
    static String input;

    @FXML
    public void mousePressInTextField(MouseEvent event){
        if(event.getButton()== MouseButton.SECONDARY){
            //Start recording
            input=VoiceRecorder.record(Launcher.recognizer);
        }
    }

    @FXML
    public void mouseReleaseInTextField(MouseEvent event){
        if(event.getButton()== MouseButton.SECONDARY){
            //Stop recording
            TextField source=(TextField) event.getSource();
            source.setText(source.getText()+input);
            source.positionCaret(source.getText().length());
        }
    }


}

