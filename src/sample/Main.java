package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

import java.util.Timer;

public class Main extends Application {
    private int minutes;
    private int seconds;
    private Timeline timeline;
    private Text pomTimer;
    private Boolean currentlyBreak;


    public Main() {
        this.minutes = 1;
        this.seconds = minutes * 60;
        this.timeline = new Timeline();
        this.currentlyBreak = false;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

//        Parent root = FXMLLoader.load(getClass().getResource("PomodoroScene.fxml"));
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        //Layouts
        //Pom Layout
        BorderPane pomodoroLayout = new BorderPane();
        //Break Layout
//        BorderPane breakTimeLayout = new BorderPane();

        HBox counterLayout = new HBox(50);
        HBox buttonLayout = new HBox(50);

        //Add Label that shows time
        pomTimer = new Text();
        pomTimer.setText(String.valueOf(minutes) + ":00");
        pomTimer.setFont(Font.font(40));
        counterLayout.getChildren().add(pomTimer);

        //Add buttons
        Button pomStart = new Button("Start");
        Button pomPause = new Button("Pause");
        Button pomReset = new Button("Reset");
        //Add buttons to Hbox buttonLayout
        buttonLayout.getChildren().add(pomStart);
        buttonLayout.getChildren().add(pomPause);
        buttonLayout.getChildren().add(pomReset);

        //Scene to display
        pomodoroLayout.setCenter(counterLayout);
        pomodoroLayout.setBottom(buttonLayout);
//        breakTimeLayout.setCenter(counterLayout);
//        breakTimeLayout.setCenter(buttonLayout);
        Scene view = new Scene(pomodoroLayout);
//        Scene view2 = new Scene(breakTimeLayout);

        //set up primary stage
        primaryStage.setTitle("Pomodoro");
        primaryStage.setScene(view);
        primaryStage.show();

        //Button action monitoring
        //pomStart
        pomStart.setOnAction((event -> {
            System.out.println("Pom Start Pressed!");
            // if current seconds less than set minutes resume timer.
            if(seconds < (minutes * 60)){
                timeline.play();
            }
            // else start the countdown.
            else{
                countDown();
            }

        }));
        //pomPause
        pomPause.setOnAction((event -> {
            System.out.println("Pom Paused Pressed!");
            timeline.pause();
        }));
        //pomReset
        pomReset.setOnAction((event -> {
            System.out.println("Pom Reset Pressed!");
            // reset to 25:00, call a stop function of timeline
            timeline.stop();
            seconds = minutes * 60;
            pomTimer.setText(String.valueOf(minutes) + ":00");
        }));
    }

    public void countDown(){
        timeline.setCycleCount(Timeline.INDEFINITE);
        if(timeline!=null){
            timeline.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                seconds--;
                pomTimer.setText(String.valueOf(seconds / 60) + ":" + String.valueOf(seconds % 60));
                if(seconds <= 0){
                    timeline.stop();
                    //when complete set up the next time
                    nextTimer();
                }
            }
        });
        if(timeline.getKeyFrames().isEmpty()){
            timeline.getKeyFrames().add(frame);
        }
        timeline.playFromStart();
    }

    public void nextTimer(){
        //DOES NOT WORK FIX THIS BIT.
        //Use to Switch Scene
        if(currentlyBreak = false){
            this.minutes = 5;
            pomTimer.setText(String.valueOf(minutes) + ":00");

        }else{
            this.minutes = 25;
            pomTimer.setText(String.valueOf(minutes) + ":00");
        }
        this.currentlyBreak = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
