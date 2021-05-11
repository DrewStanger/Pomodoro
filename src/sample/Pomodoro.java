package sample;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Pomodoro {
    private int pomTime;
    private int breakTime;
    private int seconds;
    private int minutes;
    private Boolean paused;

    public Pomodoro() {
        this.minutes = 25;
        this.seconds = minutes * 60;
        this.pomTime = (25 * 60);
        this.breakTime = 5;
        this.paused = false;
    }

    public void setPomTime(int time){
        this.pomTime = time;
    }

    public int getPomTime(){
        return this.pomTime;
    }

    public void setBreakTime(int time) {
        this.breakTime = time;
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

    public void pauseTimer(){
        this.paused = true;
    }

    public void unpauseTimer(){
        this.paused = false;
    }


}

