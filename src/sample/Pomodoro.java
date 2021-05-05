package sample;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Pomodoro {
    private int pomTime;
    private int breakTime;
    private Boolean paused;

    public Pomodoro() {
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

    public void startCountdown(){
        //if the countdown is not paused run the timer.
        if(!paused){
            while(pomTime > 0){

                try{
                    pomTime--;
                    sleep(1000);
                } catch(Exception e){
                    System.out.println("There has been an error " + e);
                }

            }
        }
        System.out.println("Pom done!");
    }

    public void startBreakCountdown(){
        if(!paused){
            while (breakTime > 0) {
                System.out.println("current time is " + breakTime);
                try {
                    breakTime--;
                    sleep(1000);
                } catch (Exception e) {
                    System.out.println("There has been an error " + e);
                }

            }
        }
        System.out.println("Break done!");
    }

    public void pauseTimer(){
        this.paused = true;
    }

    public void unpauseTimer(){
        this.paused = false;
    }
}

