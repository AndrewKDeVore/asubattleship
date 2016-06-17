package asubattleship;

/**
* Represents a player's board.
* 
* @author JoDawn WHite
* @version 1.0
* @created 6/16/16
*/

import java.awt.*;

public class Board
{
    // Class variables.
    private static final int ROW_COUNT = 10;
    private static final int COLUMN_COUNT = 10;
    private static final int ROW_PIXEL_HEIGHT = 20;
    private static final int COLUMN_PIXEL_WIDTH = 20;
    private static final int SHIPS_PER_FLEET = 5;

    // Instance variables.
    //private RedPeg[] hitMarkers;
    //private WhitePeg[] missMarkers;
    private Ship[] fleet;
    private int[][] gridCells;

    // Methods.
    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        // Initialize the arrays used to store ship info.
        this.fleet = new Ship[SHIPS_PER_FLEET];
        this.gridCells = new int[ROW_COUNT][COLUMN_COUNT];
        
        // Fill the grid cells with empty spaces.
        int i = 0;
        while (i < ROW_COUNT) {
            int j = 0;
            while (j < COLUMN_COUNT) {
                this.gridCells[i][j] = Ship.TYPE_NONE;
                j++;
            }
            
            i++;
        }
        
        // Create a test fleet.
        // REMOVE THIS CODE AFTER TESTING IS COMPLETE!
        Ship testShip;
        testShip = new Ship(Ship.TYPE_AIRCRAFT_CARRIER,
                             Ship.ORIENTATION_RIGHT, 7, 3, 5);
        this.addShip(testShip);
        testShip = new Ship(Ship.TYPE_BATTLESHIP,
                             Ship.ORIENTATION_DOWN, 1, 4, 4);
        this.addShip(testShip);
        testShip = new Ship(Ship.TYPE_CRUISER,
                             Ship.ORIENTATION_UP, 8, 0, 3);
        this.addShip(testShip);
        testShip = new Ship(Ship.TYPE_SUBMARINE,
                             Ship.ORIENTATION_LEFT, 9, 8, 3);
        this.addShip(testShip);
        testShip = new Ship(Ship.TYPE_PT_BOAT,
                             Ship.ORIENTATION_DOWN, 2, 7, 2);
        this.addShip(testShip);
    }
    
    /**
     *  Add a ship to the grid.
     */
    public void addShip(Ship newShip) {
        int row = newShip.getRow();
        int col = newShip.getColumn();
        int orientation = newShip.getOrientation();
        int i = 0;
        
        // Add the ship to the fleet array.
        this.fleet[newShip.getType()] = newShip;
        
        if (orientation == Ship.ORIENTATION_UP) {
            while (i < newShip.getSize()) {
                this.gridCells[row - i][col] = newShip.getType();
                i++;
            }
        }
        else if (orientation == Ship.ORIENTATION_RIGHT) {
            while (i < newShip.getSize()) {
                this.gridCells[row][col + i] = newShip.getType();
                i++;
            }
        }
        else if (orientation == Ship.ORIENTATION_DOWN) {
            while (i < newShip.getSize()) {
                this.gridCells[row + i][col] = newShip.getType();
                i++;
            }
        }
        else {
            // Orientation must be LEFT.
            while (i < newShip.getSize()) {
                this.gridCells[row][col - i] = newShip.getType();
                i++;
            }
        }
    }

    /**
     *  Draws the ships to the grid.
     */
    public void drawShips(Graphics gfx, Color shipColor, int startX, int startY) {
        // Set the draw color.
        gfx.setColor(shipColor);
        
        int row = 0;    // Start at the first row.
        do {
            
            int col = 0;// Start at the first column.
            do {

                // Is the cell empty?
                if (this.gridCells[row][col] != Ship.TYPE_NONE) {
                    // No--the cell contains part of a ship.
                    
                    // Calculate the starting position of the cell.
                    int x = startX + col * COLUMN_PIXEL_WIDTH;
                    int y = startY + row * ROW_PIXEL_HEIGHT;

                    // Draw in a box that's smaller than the cell.
                    gfx.fillRect(x + 2, y + 2,
                                 COLUMN_PIXEL_WIDTH - 3,
                                 ROW_PIXEL_HEIGHT - 3);
                }
                
                col++;  // Move on to the next column.
            } while (col < COLUMN_COUNT); 
            
            row++;      // Move on to the next row.
        } while (row < ROW_COUNT);
    }
    
    /**
     * Draws a grid on the screen.
     */
    public void drawGrid(Graphics gfx, Color gridColor, int startX, int startY) {
        // Set the line color.
        gfx.setColor(gridColor);

        // Draw the horizontal lines.
        int lineCounter = 1;
        int x = startX;
        int y = startY;
        while (lineCounter <= 11) {
            gfx.drawLine(x, y, x + COLUMN_PIXEL_WIDTH * COLUMN_COUNT, y);
            y = y + ROW_PIXEL_HEIGHT;
            lineCounter++;
        }

        // Draw the vertical lines.
        lineCounter = 1;
        x = startX;
        y = startY;
        while (lineCounter <= 11) {
            gfx.drawLine(x, y, x, y + ROW_PIXEL_HEIGHT * ROW_COUNT);
            x = x + COLUMN_PIXEL_WIDTH;
            lineCounter++;
        }
    }
}