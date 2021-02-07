package com.company;

import java.util.Scanner;

/**
 * The Board class simulates a board for Othello game. It holds a list of the board
 * cells which itself holds a disc for player 1, player 2 or is empty.
 * The first player plays white and the second player plays black. Black goes first.
 * In this mode, one of the players is computer and the other one is a human.
 * Also this class is a parent class for MultiPlayerBoard and SinglePlayerBoard.
 * @author Negar Movaghatian
 * @since 2020-03-31
 */
public class SinglePlayerBoard extends Board {

    /**
     * Run a single-player game while the board still has an empty cell
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
                    printTable();
                    System.out.println((player1Cells > player2Cells) ? "<<PLAYER 1 WINS!>>" : "<<COMPUTER WINS!>>");
                    return;
                }
            }
            else {
                printTable();
                scores = "Player 1:   " + player1Cells + "            Computer:   " + player2Cells  + "          " + ((Cell.getTurn()==0)? "[Player 1]" : "[Computer]");
                System.out.println(scores);
                if (Cell.getTurn()==0) {
                    // Human's turn
                    System.out.println(">Please choose a cell from the BOLD cells to put your disk on.");
                    Scanner scn = new Scanner(System.in);
                    String cell = scn.next();

                    while (!availableCells.contains(cell) || !alphabet.contains("" + cell.charAt(1)) || !numbers.contains("" + cell.charAt(0))) {
                        System.out.println(">Can't choose this cell in this round or the cell address is invalid.");
                        cell = scn.next();
                    }

                    cells[numbers.indexOf((cell.charAt(0)))][alphabet.indexOf(cell.charAt(1))].updateCell((Cell.getTurn()==0)? "W" : "B");
                    int x = cells[numbers.indexOf((cell.charAt(0)))][alphabet.indexOf(cell.charAt(1))].getX();
                    int y = cells[numbers.indexOf((cell.charAt(0)))][alphabet.indexOf(cell.charAt(1))].getY();
                    for (int d=0; d<8; d++)
                        action(directions[d], 1, x, y, false);
                    player1Cells++;
                    Cell.reverseTurn();
                }
                else {
                    // Computer's turn
                    try {
                        Thread.sleep(750);
                    }
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    int maxCellsBetween = 0, maxi = 0, maxj = 0, cellsBetween = 0;
                    for (int i=0; i<8; i++)
                        for (int j=0; j<8; j++) {
                            if (availableCells.contains("" + numbers.charAt(i) + alphabet.charAt(j))) {
                                cellsBetween = 0;
                                for (int d = 0; d < 8; d++)
                                    cellsBetween += action(directions[d], -1, i, j, true);
                                if (maxCellsBetween<cellsBetween) {
                                    maxCellsBetween = cellsBetween;
                                    maxi = i;
                                    maxj = j;
                                }
                            }
                        }
                    cells[maxi][maxj].updateCell("B");
                    for (int d=0; d<8; d++)
                        action(directions[d], -1, maxi, maxj, false);
                    System.out.println("" + numbers.charAt(maxi) + alphabet.charAt(maxj));
                    player2Cells++;
                    Cell.reverseTurn();
                }
            }
        }
        printTable();
        System.out.println((player1Cells>player2Cells)? "<<PLAYER 1 WINS!>>" : "<<COMPUTER WINS!>>");
    }

}
