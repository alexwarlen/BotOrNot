package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Main {
    public static final int ENTER_BUTTON = 1000;
    public static final int EXIT_BUTTON = -1000;
    public static void main(String[] args) {
	// write your code here

        MathGame game = new MathGame();

        Bot myBot = new Bot("My Bot");

        for (int i = 0; i < 10; i++){
            int play = (int)(Math.random() * 2);

            if (play == 0){

                System.out.println("Playing as Human:\n");
                ArrayList<Integer> moves = game.startGame();
                System.out.println("Target Number: "+ game.targetNum);

                String botOrNot;
                if (isBot(moves, game.getTargetNum())){
                    botOrNot = "Bot";
                }
                else {
                    botOrNot = "Human";
                }
                System.out.println("We think that you are a: " + botOrNot + "\n\n");
            }
            else {
                System.out.println("Playing as Bot:\n");
                ArrayList<Integer> moves = game.startGameBot(myBot);
                System.out.println("Target Number: "+ game.targetNum);

                String botOrNot;
                if (!isBot(moves, game.getTargetNum())){
                    botOrNot = "Human";
                }
                else {
                    botOrNot = "Bot";
                }

                System.out.println("We think that you are a: " + botOrNot+ "\n\n");
            }
        }


        System.out.println(game.getDifference());
        System.exit(0);
        return;
    }

    public static boolean isBot(ArrayList<Integer> moves, int targetNumber){
        int sum = 0;

        for (int i : moves){
            if (i >= -2 && i < 1000){
                sum += i;
            }
        }

        int difference = Math.abs(targetNumber - sum);

        if ((moves.get(0) == ENTER_BUTTON && targetNumber != 0) || moves.get(0) == EXIT_BUTTON){
            return true;
        }

        if (difference > 2){
            return true;
        }

        return false;
    }
}
