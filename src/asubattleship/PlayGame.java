package asubattleship;

/**
* The basic framework for a battleship game.
*
* @author JoDawn White
* @version 1.0
* @created 6/16/16
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayGame extends JFrame implements MouseListener{
    private static final int GAME_STATE_SETUP = 1;
    private static final int GAME_STATE_PLAYING = 2;
    private static final int GAME_STATE_GAMEOVER = 3;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 500;
    private static final int TITLE_X = 260;
    private static final int TITLE_Y = 225;
    private static final int MOUSE_MSG_X = 228;
    private static final int MOUSE_MSG_Y = 275;
    private static final int RED_GRID_X = 5;
    private static final int RED_GRID_Y = 5;
    private static final int BLUE_GRID_X = 255;
    private static final int BLUE_GRID_Y = 5;
    
    //instance variables
    private Board redBoard;
    private Board blueBoard;
    //private FriendlyPlayer friendlyPlayer;
    //private EnemyPlayer enemyPlayer;
    private int gameState;

    /**
     * Entry point for code execution.
     */
    public static void main(String[] args) {
    new PlayGame();
    }

    /**
     * Constructor for objects of class BattleshipApp
     */
    public PlayGame()
    {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.show();
        this.addMouseListener(this);
        this.gameState = GAME_STATE_SETUP;

        // Initialize the game boards.
        this.redBoard = new Board();
        this.blueBoard = new Board();
    }

	/**
     * Draws the game window.
     */
    public void paint(Graphics gfx) {
        //if (this.gameState == GAME_STATE_INTRO) {
            //this.drawTitleScreen();
        //}
        //else 
        	if (this.gameState == GAME_STATE_SETUP) {
            this.drawGrids();
        }
    }

    /**
     * Draws the 'Welcome to Battleship' screen.
     */
    /*private void drawTitleScreen() {
        // Get an object representing the area within the window borders.
        Container clientArea = this.getContentPane();

        // Get the Graphics object associated with the client area.
        Graphics gfx = clientArea.getGraphics();

        // Get the size of the client area.
        int width = clientArea.getWidth();
        int height = clientArea.getHeight();

        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, width, height);

        gfx.setColor(Color.green);
        gfx.drawString("BATTLESHIP", TITLE_X, TITLE_Y);
        gfx.setColor(Color.gray);
        gfx.drawString("(click mouse to continue)", MOUSE_MSG_X, MOUSE_MSG_Y);
    }*/

    /**
     * Draw the game grids.
     */
    private void drawGrids() {
        // Get an object representing the area within the window borders.
        Container clientArea = this.getContentPane();

        // Get the Graphics object associated with the client area.
        Graphics gfx = clientArea.getGraphics();

        // Get the size of the client area.
        int width = clientArea.getWidth();
        int height = clientArea.getHeight();

        // Fill the background with black.
        gfx.setColor(Color.gray);
        gfx.fillRect(0, 0, width, height);

        // Draw the two grids.
        this.redBoard.drawGrid(gfx, Color.red, RED_GRID_X, RED_GRID_Y);
        this.blueBoard.drawGrid(gfx, Color.blue, BLUE_GRID_X, BLUE_GRID_Y);
        
        // Draw the ships on the grids.
        this.redBoard.drawShips(gfx, Color.green, RED_GRID_X, RED_GRID_Y);
        this.blueBoard.drawShips(gfx, Color.green, BLUE_GRID_X, BLUE_GRID_Y);
    }

    /**
     * MouseListener methods.
     */
    public void mouseClicked(MouseEvent event) {}

    public void mouseEntered(MouseEvent event) {}

    public void mouseExited(MouseEvent event) {}

    public void mousePressed(MouseEvent event) {}

    public void mouseReleased(MouseEvent event) {
        this.gameState = GAME_STATE_SETUP;
        this.repaint();
    }
}
