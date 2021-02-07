package com.company;

import java.util.Scanner;

/**
 * The MultiPlayerBoard class simulates an Othello board for two players. It holds a list of the board
 * cells which itself holds a disc for player 1, player 2 or is empty.
 * The first player plays white and the second player plays black. Black goes first.
 * In this mode, both of the players are human.
 * Also this class is a child class of Board.
 * @author Negar Movaghatian
 * @since 2020-03-31
 */
public class MultiPlayerBoard extends Board {

    /**
     * Run a multi-player game while the board still has an empty cell
     * capable of placing a new disc on. Also manage the number of discs of
     * each player on board (which represents the player's score).
     * At the end show the name of the winner, the player whose score is greater.
     */
    public void runGame() {
        while (player1Cells + player2Cells<64) {
            int numberOfCells = disableCells();
            if (numberOfCells==0) {
                System.out.println("Pass!");
                Cell.reverseTurn();
                numberOfCells = disableCells();
                if (numberOfCells==0) {
                    System.out.println((player1Cells > player2Cells) ? "<<PLAYER 1 WINS!>>" : "<<PLAYER 2 WINS!>>");
                    break;
                }
            }
            else {
                printTable();
                scores = "Player 1:   " + player1Cells + "            Player 2:   " + player2Cells  + "          " + ((Cell.getTurn()==0)? "[Player 1]" : "[Player 2]");
                System.out.println(scores);
                System.out.println(">Please choose a cell form the BOLD cells to put your disk on.");
                Scanner scn = new Scanner(System.in);
                String cell = scn.next();

                while (!availableCells.contains(cell) || !alphabet.contains("" + cell.charAt(1)) || !numbers.contains("" + cell.charAt(0))) {
                    System.out.println(">Can't choose this cell in this round or the cell address is invalid.");
                    cell = scn.next();
                }

                if (Cell.turn == 0)
                    player1Cells++;
                else
                    player2Cells++;

                cells[numbers.indexOf((cell.charAt(0)))][alphabet.indexOf(cell.charAt(1))].updateCell((Cell.getTurn()==0)? "W" : "B");
                int color = cells[numbers.indexOf((cell.charAt(0)))][alphabet.indexOf(cell.charAt(1))].getStat();
                int x = cells[numbers.indexOf((cell.charAt(0)))][alphabet.indexOf(cell.charAt(1))].getX();
                int y = cells[numbers.indexOf((cell.charAt(0)))][alphabet.indexOf(cell.charAt(1))].getY();
                for (int d=0; d<8; d++)
                    action(directions[d], color, x, y, false);
                Cell.reverseTurn();
            }
        }
        printTable();
        System.out.println((player1Cells>player2Cells)? "<<PLAYER 1 WINS!>>" : "<<PLAYER 2 WINS!>>");
    }
}
