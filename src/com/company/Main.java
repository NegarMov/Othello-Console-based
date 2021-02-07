package com.company;

import java.util.Scanner;

/**
 * The main class determines if the player wants to play in single (With computer) or multi player mode.
 * Then it creates a board according to the game mode and runs it.
 * @author Negar Movaghatian
 * @since 2020-03-31
 */
public class Main {

    public static void main(String[] args) {


        // Determine if the game is single-player or multi-player
        System.out.println(">Do you want to play the game in single or multi player mode?");
        System.out.println("1)Single Player\n2)Multi Player");
        int ans = new Scanner(System.in).nextInt();


        if (ans==2) {
            // Run a multi-player game
            MultiPlayerBoard MPBoard = new MultiPlayerBoard();
            MPBoard.runGame();
        }
        else {
            // Run a single-player game
            SinglePlayerBoard SPBoard = new SinglePlayerBoard();
            SPBoard.runGame();
        }
    }
}
