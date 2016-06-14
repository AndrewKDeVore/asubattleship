package asubattleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rnealis on 6/13/16.
 */
public class MainMenu {

    public static void main(String args[]) {
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Main Menu");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JButton playButton = new JButton("Play Game");
                JButton creditsButton = new JButton("Credits");

                JLabel sparkyPic = new JLabel("Boom");

                // Define ActionListener
                ActionListener actionListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("We're gonna play the game.");

                    }
                };

                // Attach listener
                playButton.addActionListener(actionListener);

                frame.add(sparkyPic, BorderLayout.NORTH);
                frame.add(playButton, BorderLayout.CENTER);
                frame.add(creditsButton, BorderLayout.SOUTH);
                // center the window
                frame.setLocationRelativeTo(null);
                frame.setSize(300, 100);
                frame.setVisible(true);
            }
        };
        EventQueue.invokeLater(runner);
    }
}
