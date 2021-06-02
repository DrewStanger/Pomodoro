package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.nio.file.Paths;

import static java.lang.Thread.sleep;

public class Pomodoro {

    private int pomTime;
    private int breakTimeMinutes;
    private int breakTimeSeconds;
    private int seconds;
    private int minutes;
    private int pomsCompleted;
    private Boolean paused;

    public Pomodoro() {
        this.minutes = 25;
        this.seconds = minutes * 60;
        this.pomTime = (25 * 60);
        this.breakTimeMinutes = 5;
        this.breakTimeSeconds = breakTimeMinutes * 60;
        this.paused = false;
        this.pomsCompleted = 0;

    }

    public void playAlarm(){

    }

    public void pomIntervals(){
        //this function records the number of pomodoros completed to inform length of break required
        System.out.println(getPomsCompleted());
        if(getPomsCompleted() == 4){
            //do long break
            setBreakMinutes(20);
            setPomsCompleted(0);
            System.out.println(getPomsCompleted());
        }
        else{
            //do short break
            setBreakMinutes(5);
            setPomsCompleted(getPomsCompleted() + 1);
            System.out.println(getPomsCompleted());
        }
    }



    public void setBreakMinutes(int time) {
        this.breakTimeMinutes = time;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }


    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getPomsCompleted(){
        return this.pomsCompleted;
    }

    public void setPomsCompleted(int pomsCompleted){
        this.pomsCompleted = pomsCompleted;
    }



}

