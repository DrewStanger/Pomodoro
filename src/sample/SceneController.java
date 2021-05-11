package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.sql.Time;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Timeline timeline;
    private Pomodoro pomodoro;


    @FXML
    private Text pomTimer;

//    @FXML
//    private Button pomStart;
//
//    @FXML
//    private Button PomPause;
//
//    @FXML
//    private Button PomReset;

    public SceneController() {
        this.timeline = new Timeline();
        this.pomodoro = new Pomodoro();
    }

    public void switchToSceneOne(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PomodoroScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneTwo(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("BreakTimeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Text getPomTimer(){
        return this.pomTimer;
    }


    public void setPomTimer(int seconds){
        pomTimer.setText(seconds / 60 + ":" + seconds % 60);
    }

    public void countDown(){
        timeline.setCycleCount(Timeline.INDEFINITE);
        if(timeline!=null){
            timeline.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                pomodoro.setSeconds(pomodoro.getSeconds() - 1);
                setPomTimer(pomodoro.getSeconds());
                if(pomodoro.getSeconds() <= 0){
                    timeline.stop();
                    //when complete set up the next time
//                    nextTimer();
                }
            }
        });
        if(timeline.getKeyFrames().isEmpty()){
            timeline.getKeyFrames().add(frame);
        }
        timeline.playFromStart();
    }

    public void startTimer(){
        System.out.println("Pom Start Pressed!");
        // if current seconds less than set minutes resume timer.
        if(pomodoro.getSeconds() < (pomodoro.getMinutes() * 60)){
            timeline.play();
        }
        // else start the countdown.
        else{
            countDown();
        }
    }

    public void resetTimer(){
        System.out.println("Pom Reset Pressed!");
        // reset to 25:00, call a stop function of timeline
        timeline.stop();
        pomodoro.setSeconds(pomodoro.getMinutes() * 60);
        pomTimer.setText(String.valueOf(pomodoro.getMinutes()) + ":00");
    }

    public void pauseTimer(){
        System.out.println("Pom Paused Pressed!");
        timeline.pause();
    }

}
