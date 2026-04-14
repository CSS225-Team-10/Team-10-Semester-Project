import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Creates the actual panel for the user to interact with, still needs all the
 * implementation of the interactable stuff.
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class PawfficeMain implements Runnable {
    // a constant for the world width, no height needed i think
    private final int WORLD_WIDTH = 750;

    // user
    private User user;

    // background color
    private final Color BACKGROUND = new Color(0, 0, 0);

    // Panel for our GUI.
    private JPanel panel;

    // thisll be for the camera to follow the user around the world :D
    private int cameraX = 0;

    /**
     * The run method to set up the graphical user interface
     */
    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Pawffice");
        frame.setPreferredSize(new Dimension(600, 400));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("Images/background.jpeg"));
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }

        final BufferedImage finalBackgroundImage = backgroundImage;

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(finalBackgroundImage, 0, 0, getWidth(), getHeight(), null);

            

                g.setColor(Color.RED);
                g.fillRect(0 - cameraX, 0, 8, getHeight());
                g.fillRect(WORLD_WIDTH - cameraX - 8, 0, 8, getHeight());

                user.draw(g, cameraX);
            }
        };

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        user = new User(panel.getWidth() / 2, panel.getHeight() / 2, WORLD_WIDTH);

        frame.addKeyListener(new KeyAdapter() {

            // THE CODE BELOW IS BY BLUE!! I HAVE DONE CODE LIKE THIS PREVIOUSLY
            // WHEN I CREATED A MINI GAME FOR MYSELF SO I RESEARCHED ON MY OWN TIME :)

            // checks which key is pressed
            public void keyPressed(KeyEvent e) {
                // if key is left arrow, movingLeft boolean is set true
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    user.setMovingLeft(true);
                }
                // if key is right arrow, movingRight boolean is set true
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    user.setMovingRight(true);
                }
            }

            // checks which key is released
            public void keyReleased(KeyEvent e) {
                // if key is left arrow, movingLeft boolean is set false
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    user.setMovingLeft(false);
                }
                // if key is right arrow, movingRight boolean is set false
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    user.setMovingRight(false);
                }
            }
        });

        // while loop to allow for the panel to even function
        while (true) {
            // repaint the panel and allow the user to move :D
            panel.repaint();
            updateCamera();
            user.update();

            try {
                // this represents the games fps!!!
                Thread.sleep(16);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * This method updates the camera to keep the
     * user centered on the screen, while making sure that the camera
     * does not go out of bounds of the pawffice :D
     * 
     */
    private void updateCamera() {
        int panelWidth = panel.getWidth();

        // makes sure the camera is centered on the user!
        cameraX = user.getX() + user.getWidth() / 2 - panelWidth / 2;

        if (cameraX < 0) {
            cameraX = 0;
        }

        if (cameraX + panelWidth > WORLD_WIDTH) {
            cameraX = WORLD_WIDTH - panelWidth;
        }
    }

    public static void main(String[] args) {
        PawfficeMain pm = new PawfficeMain();
        Thread pawffice = new Thread(pm);
        pawffice.start();
    }
}