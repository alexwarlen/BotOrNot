package com.company;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Alexandra on 4/5/16.
 */
public class MathGame extends JFrame{

    public static final int ENTER_BUTTON = 1000;
    public static final int EXIT_BUTTON = -1000;
    public static ArrayList<Integer> moves = new ArrayList<Integer>();
    public static boolean winner = false;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private static boolean running = true;
    private JButton exitB, enterB, oneB, twoB, fiveB, tenB, twentyB, negTwoB;
    private int currentNumber = 0;
    public static int targetNum;
    private static final int GAME_TIME = 7000;
    //Button handlers:

    private NumberButtonHandler nbHandler;
    private ExitButtonHandler ebHandler;
    private EnterButtonHandler enterBHandler;
    private final Container pane;

    private JButton[] buttons = new JButton[8];

    public MathGame() {

        //Specify handlers for each button and add (register) ActionListeners to each button.
        currentNumber = 0;

        pane = getContentPane();
        pane.setLayout(new GridLayout(4, 2));

        exitB = new JButton("Exit");
        enterB = new JButton("Done");
        enterBHandler = new EnterButtonHandler();
        ebHandler = new ExitButtonHandler();
        nbHandler = new NumberButtonHandler();

        // Number Buttons
        oneB = new JButton("1");
        oneB.addActionListener(nbHandler);

        twoB = new JButton("2");
        twoB.addActionListener(nbHandler);

        fiveB = new JButton("5");
        fiveB.addActionListener(nbHandler);

        tenB = new JButton("10");
        tenB.addActionListener(nbHandler);

        twentyB = new JButton("20");
        twentyB.addActionListener(nbHandler);

        negTwoB = new JButton("-2");
        negTwoB.addActionListener(nbHandler);

        // Enter Button
        enterB.addActionListener(enterBHandler);


        pane.add(oneB);
        pane.add(twoB);
        pane.add(fiveB);
        pane.add(tenB);
        pane.add(twentyB);
        pane.add(negTwoB);
        pane.add(exitB);
        pane.add(enterB);

        exitB.addActionListener(ebHandler);

        buttons[0] = oneB;
        buttons[1] = twoB;
        buttons[2] = fiveB;
        buttons[3] = tenB;
        buttons[4] = twentyB;
        buttons[5] = negTwoB;
        buttons[6] = exitB;
        buttons[7] = enterB;

        setSize(WIDTH, HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public ArrayList<Integer> startGame() {
        targetNum = (int) (Math.random()*50);

        currentNumber = 0;
        winner = false;
        moves = new ArrayList<Integer>();
        running = true;
        JOptionPane.showMessageDialog(null, "Get ready to play the math game! Click the buttons to add up to the special number: " + targetNum + "\nClick done when you have reached " + targetNum + ".", null, JOptionPane.PLAIN_MESSAGE);
        setTitle("MathGame - Bot or Not?");
        setVisible(true);

        try {
            Thread.sleep(GAME_TIME);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

            endGame();


        return moves;
    }

    public ArrayList<Integer> startGameBot(Bot thisBot) {
        targetNum = (int) (Math.random()*50);

        currentNumber = 0;
        winner = false;
        moves = new ArrayList<Integer>();
        running = true;

        setVisible(true);


        thisBot.playGame(this);

        try {
            Thread.sleep(GAME_TIME);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        endGame();



        return moves;
    }

    public void restartGame(){
        moves = new ArrayList<Integer>();
        winner = false;
        currentNumber = 0;
        running = true;

        targetNum = (int) (Math.random()*50);
        JOptionPane.showMessageDialog(null, "Get ready to play the math game! Click the buttons to add up to the special number: " + targetNum + "\nClick done when you have reached " + targetNum + ".", null, JOptionPane.PLAIN_MESSAGE);
        setTitle("MathGame - Bot or Not?");
        startGame();


    }

    private class NumberButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            JButton button = (JButton)e.getSource();

            int num = Integer.parseInt(button.getText().toString());
            System.out.print("Number Pressed: " + num + ".    ");
            moves.add(num);
            currentNumber+= num;
            System.out.println("Current Count: " + currentNumber);
            return;
        }
    }

    public class ExitButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            System.out.print("Exit button pressed.    ");
            System.out.println("Current Count: " + currentNumber);
            moves.add(EXIT_BUTTON);
            winner = false;

            running = false;

            setVisible(false);



        }
    }
    public class EnterButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Enter button pressed");
            moves.add(ENTER_BUTTON);
            if (currentNumber == targetNum) {
                winner = true;

                running = false;
                System.out.println("Setting running to false enter button: " + running);

                setVisible(false);


            }
            else {
                winner = false;

                running = false;

                setVisible(false);

            }
        }
    }
    public int getDifference(){
        return (currentNumber - targetNum);
    }


    private void endGame() {
        winner = false;
        running = false;

        setVisible(false);

    }
    public JButton[] getButtons(){
        return buttons;
    }

    public boolean getRunning(){
        return running;
    }

    public int getTargetNum() {
        return targetNum;
    }
    public boolean getWinner() {
        return winner;
    }
}



