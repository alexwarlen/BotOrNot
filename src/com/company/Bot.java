package com.company;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Bot Class
 *
 * A Bot object will be created that takes in options given by a 'GUI' and
 * makes fairly random decisions as to how it will 'click' around on the
 * screen. This is a simulation of a very simple bot working around on a
 * server or application that does not attempt to do any specific job but
 * just 'wanders' around.
 *
 */
public class Bot {
    private String myName;
    private ArrayList<Integer> moves;
    private int currentCount;


    public Bot(String name){

        this.myName = name;

    }

    public void playGame(MathGame game) {

        JButton[] buttons = game.getButtons();

        while(game.getRunning()) {


            int chooseButton = (int) (Math.random() * buttons.length);

            buttons[chooseButton].doClick();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public String getName() {
        return this.myName;
    }
}
