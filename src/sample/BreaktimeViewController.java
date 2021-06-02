package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BreaktimeViewController implements Initializable {

    private Pomodoro pomodoro;
    private final Timeline timeline;

    @FXML
    private Text pomTimer;

    public BreaktimeViewController() {
//        this.timeline = new Timeline();
        this.timeline = new Timeline();
    }
    //this method accepts the pomodoro to initialize the breaktimer.
    public void initData(Pomodoro pomodoro){
        this.pomodoro = pomodoro;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }

    public void switchToSceneOne(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PomodoroScene.fxml"));
        Parent BreakSceneParent = loader.load();

        Scene scene = new Scene(BreakSceneParent);
        //access the controller and call a method
        SceneController controller = loader.getController();
        controller.initData(pomodoro);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void countDown(){
        timeline.setCycleCount(Timeline.INDEFINITE);
        if(timeline!=null){
            timeline.stop();
        }
        //animation to update every one second (the countdown)
        KeyFrame frame = new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            //update to current time.
            pomodoro.setSeconds(pomodoro.getSeconds() - 1);
            setPomTimer(pomodoro.getSeconds());
            //if timer complete stop and alarm.
            if(pomodoro.getSeconds() <= 0){
                timeline.stop();
                javafx.scene.media.AudioClip pomodoroAlarm = new AudioClip(this.getClass().getResource("telephone-ring-03a.mp3").toString());
                pomodoroAlarm.play();
            }
        });

        if(timeline.getKeyFrames().isEmpty()){
            timeline.getKeyFrames().add(frame);
        }
        timeline.playFromStart();
    }

    public void startTimer(){
        System.out.println("Pom Start Pressed!");
        pomodoro.setMinutes(1);
        pomodoro.setSeconds(pomodoro.getMinutes() * 1);
        // if current seconds less than set minutes resume timer.
        if(pomodoro.getSeconds() < (pomodoro.getMinutes() * 1)){
            timeline.play();
        }
        // else start the countdown.
        else{
            countDown();
        }
    }


    public void setPomTimer(int seconds){
        pomTimer.setText(seconds / 60 + ":" + seconds % 60);
    }
//    public void startBreakTimer(){
//        System.out.println("Pom Start Pressed!");
//        //set timer to 5 minutes for break
//        pomodoro.setSeconds(pomodoro.getBreakMinutes() * 60);
//        // if current seconds less than set minutes resume timer.
//        if(pomodoro.getSeconds() < (pomodoro.getBreakMinutes() * 60)){
//            pomodoro.timelinePlay();
//        }
//        // else start the countdown.
//        else{
//            pomodoro.countDown();
//        }
//    }


}
