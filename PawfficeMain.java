import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;

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
    private final int FRAME_WIDTH = 600;
    // user
    private User user;

    // clock
    private Clock clock;

    // bed
    private Bed bed;

    // bookshelf
    private Bookshelf bookshelf;

    // desk
    private Desk desk;

    // images that go straight on the frame, not interactable objects :D
    private static final ImageIcon BACKGROUND_IMAGE = new ImageIcon("Images/background.png");
    private static final ImageIcon CARPET_IMAGE = new ImageIcon("Images/carpet.png");
    private static final ImageIcon CHAIR_IMAGE = new ImageIcon("Images/chair.png");
    //private static final ImageIcon SHADOWS_IMAGE = new ImageIcon("Images/shadows.png");


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
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, 400));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        try {
            frame.setIconImage(ImageIO.read(new File("Images/appicon.png")));
        } catch (IOException e) {
        }

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // cameraX = user.getX() + user.getWidth() / 2 - getWidth() / 2;

                g.drawImage(BACKGROUND_IMAGE.getImage(), -cameraX, 0, 750, getHeight(), null);
                g.drawImage(CARPET_IMAGE.getImage(), 125 - cameraX, 200, 460, 184, null);
                clock.draw(g, cameraX);
                bed.draw(g, cameraX);
                bookshelf.draw(g, cameraX);
                desk.draw(g, cameraX);
                g.drawImage(CHAIR_IMAGE.getImage(), 178 - cameraX, 190, 136, 177, null);

                // System.out.println("Camera X: " + cameraX);
                Color color = new Color(234, 199, 159);

                g.setColor(color);
                g.fillRect(0 - cameraX, 0, 8, getHeight());
                g.fillRect(WORLD_WIDTH - cameraX - 8, 0, 8, getHeight());

                user.draw(g, cameraX);
                //g.drawImage(SHADOWS_IMAGE.getImage(), 182 - cameraX, 63, 404, 245, null);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                if (bed.isClicked(mouseX, mouseY, cameraX)) {
                    javax.swing.SwingUtilities.invokeLater(bed);
                }
                if (clock.isClicked(mouseX, mouseY, cameraX)) {
                    clock.launch();
                }
                if (bookshelf.isClicked(mouseX, mouseY, cameraX)) {
                    bookshelf.launch();
                }
                if (user.isClicked(mouseX, mouseY, cameraX)) {
                    user.clicked(mouseX, mouseY, cameraX);
                }
                if (desk.isClicked(mouseX, mouseY, cameraX)) {
                    desk.launch();
                }
            }
        });

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        user = new User(panel.getWidth() / 2, panel.getHeight() / 2, WORLD_WIDTH);
        bookshelf = new Bookshelf(393, 60);
        clock = new Clock(20, 10);
        bed = new Bed(586, 115);
        desk = new Desk(178, 140);
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