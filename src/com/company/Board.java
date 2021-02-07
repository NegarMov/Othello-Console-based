package com.company;

import java.util.ArrayList;

/**
 * The Board class simulates a board for Othello game. It holds a list of the board
 * cells which itself holds a disc for player 1, player 2 or is empty.
 * The first player plays white and the second player plays black. Black goes first.
 * Also this class is a parent class of MultiPlayerBoard and SinglePlayerBoard.
 * @author Negar Movaghatian
 * @since 2020-03-31
 */
public class Board{

    // The list of the board's cells
    protected Cell[][] cells;
    // The number of discs player 1 has on the board
    protected int player1Cells;
    // The number of discs player 2 has on the board
    protected int player2Cells;
    // A String to show the scores
    protected String scores;
    // Directions of the board
    protected String[] directions = {"E", "W", "S", "N", "SE", "SW", "NE", "NW"};
    // The list of available cells to put disk on each round
    protected ArrayList<String> availableCells;
    // The list of legal alphabets on board
    protected String alphabet = "ABCDEFGH";
    // The list of legal numbers on board
    protected String numbers = "12345678";

    /**
     * Creat a new board with adding 64 cells to it and setting
     * the four player initial discs.
     */
    public Board() {

        cells = new Cell[8][8];
        player1Cells = 2;
        player2Cells = 2;
        availableCells = new ArrayList<>();

        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++) {
                Cell c = new Cell(i, j);
                cells[i][j] = c;
            }
        cells[3][3].setStat(1);
        cells[4][4].setStat(1);
        cells[3][4].setStat(-1);
        cells[4][3].setStat(-1);
    }

    /**
     * Print the game's board with all of its components.
     */
    protected void printTable() {
        System.out.print("\n\n\n\n ");
        for (int i=0; i<8; i++)
            System.out.print(alphabet.charAt(i) + "\u2005");
        System.out.println();
        for (int i=0; i<8; i++) {
            System.out.print(i+1);
            for (int j=0; j<8; j++)
                System.out.print(cells[i][j].getCharacter());
            System.out.println();
        }
    }


    /**
     * Disable the cells which can not hold a new disk in this round according to the games's rules.
     * @return The number of cells which are capable of holding new discs in this round.
     */
    protected int disableCells() {
        int legalCells = 64;
        availableCells.clear();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (cells[i][j].getCharacter()=='\u2B1C' || cells[i][j].getCharacter()=='\u2B1B')
                    cells[i][j].setCharacter('\u2B1C');
                int color = (Cell.getTurn()==0)? 1 : -1;
                boolean able = cells[i][j].getStat()!=0;
                for (int d=0; d<8; d++)
                    able = able || action(directions[d], color, i, j, true)>0;
                if (!able)
                    legalCells--;
                else if (cells[i][j].getStat()==0) {
                    cells[i][j].setCharacter('\u2B1B');
                    availableCells.add("" + numbers.charAt(i) + alphabet.charAt(j));
                }
                if (cells[i][j].getStat()!=0)
                    legalCells--;
            }
        }
        return legalCells;
    }

    /**
     * Check the number of discs moving from (x, y) to the given direction that should be flipped
     * according to the game's rules when we put a disc with color 'color' on the coordinate (x, y).
     * If 'check' is true then we only need the number of discs, otherwise the action would be performed
     * and the discs would be flipped.
     * @param dir the direction to move and check the disc on its way.
     * @param color The color of the cell to begin from.
     * @param x The y of the cell to begin from.
     * @param y The y of the cell to begin from.
     * @param check This boolean shows if the action is really happening and the discs must be flipped(false)
     *              or we only need to know the number of disc that will be flipped(true).
     * @return The number of cells to be flipped while moving in the given direction.
     */
    protected int action(String dir, int color, int x, int y, boolean check) {
        int dx = 0, dy = 0, tmpx = x, tmpy = y;
        if (dir.contains("E")) dx = 1;
        if (dir.contains("W")) dx = -1;
        if (dir.contains("N")) dy = -1;
        if (dir.contains("S")) dy = 1;

        int cellsBetween = 0;
        x += dx;
        y += dy;
        while (x>=0 && y>=0 && x<8 && y<8 && cells[x][y].getStat()==-1*color) {
            x += dx;
            y += dy;
            cellsBetween++;
        }
        if ((x>=0 && y>=0 && x<8 && y<8) && cells[x][y].getStat()==color) {
            if (check)
                return cellsBetween;
            if (color==1) {
                player1Cells += cellsBetween;
                player2Cells -= cellsBetween;
            }
            else {
                player2Cells += cellsBetween;
                player1Cells -= cellsBetween;
            }
            tmpx += dx;
            tmpy += dy;
            while (tmpx>=0 && tmpy>=0 && tmpx<8 && tmpy<8 && cells[tmpx][tmpy].getStat()!=color) {
                cells[tmpx][tmpy].updateCell((color==1)? "W" : "B");
                tmpx += dx;
                tmpy += dy;
            }
        }
        return 0;
    }
}