package asubattleship;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


class Tile extends JButton {

    private String tileType;

    Tile(String text, String tileType, GameState gState) {
        super(text);

        // This is what shows up in the tile button text
        setText("Water");
        setBackground(Color.BLUE);
        setOpaque(true);

        // Define the name so that even if we update the
        // text of the button we can still identify it
        setName(text);

        // Stores what type of tile this is, player or cpu
        this.tileType = tileType;

        setActionCommand(text);
        addActionListener(gState);
    }

    public String getTileType() {
        return tileType;
    }
}

class GameState implements ActionListener {
    /* This class handles game events and also keeps track of state.
     */

    /* Game states
       0 - start of game. player places ship
       1 - cycling of turns, player 1 goes first, then CPU, until
           someone has their ship sunk.
     */
    int gameState = 0;

    Tile[] playerTiles;
    Tile[] cpuTiles;
    JTextArea stepText =  new JTextArea("Pick a tile to place your ship " +
                                        "in within the left side of tiles.");
    Tile playerShip;
    Tile cpuShip;

    GameState(Tile[] playerTiles, Tile[] cpuTiles) {
        // we need references to the different tiles so that we
        // can update them as the game plays along
        this.playerTiles = playerTiles;
        this.cpuTiles = cpuTiles;
    }

    public void actionPerformed(ActionEvent ae) {
        // not a huge fan of this portion of the code. We just
        // have to make sure we never generate events from
        // anything but tiles, otherwise bugs will ensure...
        Tile actionTile = (Tile) ae.getSource();

        // if we're in the earliest state where the player needs
        // to pick a tile to place their ship on
        if ((gameState == 0) && (actionTile.getTileType() == "player")) {
            actionTile.setBackground(Color.DARK_GRAY);
            actionTile.setOpaque(true);
            stepText.setText("Good choice. Your ship is in tile "
                    + actionTile.getName() + ".");
            // update the tile text so we know where the player ship is
            // (besides the different bg color)
            actionTile.setText("SHIP");

            // store off what tile the ship is in.
            playerShip = actionTile;

            // Pick a random tile to put the CPUs ship in
            cpuShip = pickRandomTile(cpuTiles);
            stepText.setText("CPU placed their ship. See if you can guess where.");
            // time to take a shot
            gameState += 1;

        } else if (gameState == 0){
            stepText.setText("Doh. You need to pick a tile from the player "
                    + "board on the left.");
        } else if ((gameState == 1) && actionTile.getTileType() == "cpu") {
            // The player takes a turn, let's see if they found the cpu ship
            if (actionTile.equals(cpuShip)) {
                stepText.setText("You found them! SUNK");
                actionTile.setBackground(Color.RED);
                actionTile.setOpaque(true);
            } else {
                stepText.setText("You missed!");
                actionTile.setText("Miss");
                actionTile.setBackground(Color.CYAN);
                actionTile.setOpaque(true);
                // CPU turn
                stepText.setText(stepText.getText() + " CPU fires salvo!");
                Tile randomShot = pickRandomTile(playerTiles);
                if (randomShot.equals(playerShip)) {
                    stepText.setText("CPU sunk you!!!");
                    playerShip.setBackground(Color.RED);
                    playerShip.setOpaque(true);
                } else {
                    randomShot.setBackground(Color.CYAN);
                    randomShot.setText("Miss");
                    stepText.setText(stepText.getText() + " And misses. Take another shot.");
                }

            }

        } else if (actionTile.getTileType() == "player") {
            stepText.setText("Doh. You need to pick a tile from the CPU "
                    + "board on the right.");

        }
    }

    public JTextArea getStepText() {
        return stepText;
    }

    private Tile pickRandomTile(Tile[] tiles){
        Random rand = new Random();
        return tiles[rand.nextInt(9)];
    }
}

public class BarebonesBattleship {

    // Our base JFrame
    private JFrame jfrm;

    // The tiles on the game boards
    Tile[] playerTiles = new Tile[9];
    Tile[] cpuTiles = new Tile[9];

    GameState gState = new GameState(playerTiles, cpuTiles);

    BarebonesBattleship() {
        // Create a new JFrame for storing our components
        jfrm = new JFrame("ASU Battleship");
        jfrm.setLayout(new BorderLayout());
        jfrm.setSize(800, 300);

        // Center the window
        jfrm.setLocationRelativeTo(null);

        // Terminate on window close
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display at the top of the Window
        JLabel heading = new JLabel("Let's Play Battleship!");
        heading.setForeground(Color.BLUE);
        heading.setFont(heading.getFont().deriveFont(34.0f));
        heading.setBorder(BorderFactory.createLineBorder(Color.black));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        jfrm.add(heading, BorderLayout.PAGE_START);

        // Player Board
        jfrm.add(new Board("player", playerTiles), BorderLayout.LINE_START);

        JTextArea textBox = gState.getStepText();
        // Make sure it wraps around nicely!
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);
        jfrm.add(textBox, BorderLayout.CENTER );

        // CPU Board
        jfrm.add(new Board("cpu", cpuTiles), BorderLayout.LINE_END);


        jfrm.setVisible(true);

    }

    public class Board extends JPanel {

        Tile[] tiles;

        // We need to know whether this is a player board or a CPU board,
        // also we store references to the different tiles, used to keep
        // track of their status
        Board(String boardType, Tile[] tiles) {

            this.tiles = tiles;

            // Sets up a simple 3 by 3 grid for our board
            setLayout(new GridLayout(3, 3, 4, 4));
            setSize(500, 500);

            // instantiate each tile, their name is just the number they are
            // on the board
            for(int i=0; i<9; i++){
                String tileName = Integer.toString(i);
                // create with a default tile name of the number of the tile,
                // store whether or not the tile is a player tile or a cpu tile,
                // as well as a reference to the game event handler
                tiles[i] = new Tile(tileName, boardType, gState);
            }

            for (Tile tile : tiles) {
                add(tile, gState);
            }

            // Add an empty border around the content pane.
            this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

            setVisible(true);
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BarebonesBattleship();
            }
        });
    }

}

