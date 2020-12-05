package com.OutOfTheBox.Aqua;

import edu.drexel.cs.jah473.util.Input;
import edu.drexel.cs.jah473.util.IterableReader;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class Launcher extends Application {
    static LiveSpeechRecognizer recognizer;

    @Override
    public void start(Stage primaryStage) throws IOException {
        //////////////////////////////////////////////////////////////////////////////////
        Configuration config = new Configuration();
        VoiceRecorder.configure(config, false);

        recognizer = new LiveSpeechRecognizer(config);

        recognizer.startRecognition(true);
        //////////////////////////////////////////////////////////////////////////////////
        IterableReader in;
        try{
            in = Input.fromFile("dictionary.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from dictionary");
            return;
        }
        List<String> corpus = new ArrayList<>();
        for (String word : in) {
            corpus.add(word);
        }
        Input.close(in);
        //////////////////////////////////////////////////////////////////////////////////
        Parent root = FXMLLoader.load(getClass().getResource("Screen.fxml"));
        root.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        primaryStage.setTitle("Aqua");
        primaryStage.setScene(new Scene(root, 800, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
