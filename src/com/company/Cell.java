package com.company;

/**
 * The class Cell represents a cell of the board. Each cell is made of a unicode character, the cell's
 * coordinate ((x, y)), the status of the cell (is empty, a black disc is on it or a white disc is on it)
 * and manages which player's turn it is at the moment.
 * @author Negar Movaghatian
 * @since 2020-03-31
 */
public class Cell{

    // The unicode character which represents a cell's status
    private char character;
    // The turn of the players [0: first player(white)  1: second player(black)]
    static int turn;
    // The x of the cell
    private int x;
    // The y of the cell
    private int y;
    // The status of the cell [0: empty    1: white    -1: black]
    private int stat;

    /**
     * Create a new cell with the given coordinate and set it's character to an empty
     * box.
     * @param x the x of the new cell.
     * @param y the y of the new cell.
     */
    public Cell(int x, int y) {
        turn = 1;
        this.x = x;
        this.y = y;
        stat = 0;
        character = '\u2B1C';
    }

    /**
     * Update the cell and change its status to the given color.
     * @param color The color of the disk on the cell.
     */
    public void updateCell(String color) {
        if (color.equals("B")) {
            character = '\u26AA';
            stat = -1;
        }
        else {
            character = '\u26AB';
            stat = 1;
        }
    }

    /**
     * Get the character of the cell.
     * @return character field.
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Change the character of this cell to the given character.
     * @param character the character to change the character field with.
     */
    public void setCharacter(char character) {
        this.character = character;
    }

    /**
     * Get the status of the cell.
     * @return stat field.
     */
    public int getStat() {
        return stat;
    }

    /**
     * Change the status (color) of the cell.
     * @param stat the new status of the cell.
     */
    public void setStat(int stat) {
        this.stat = stat;
        updateCell((stat==1)? "W" : "B");
    }

    /**
     * Get the turn of the players at the moment.
     * @return turn field.
     */
    public static int getTurn() {
        return turn;
    }

    /**
     * Get the x coordinate of the cell
     * @return x field
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate of the cell
     * @return y field
     */
    public int getY() {
        return y;
    }

    /**
     * Give the current turn to the other player.
     */
    static void reverseTurn() {
        turn = 1-turn;
    }
}
