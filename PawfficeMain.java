import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Creates the actual panel for the user to interact with, still needs all the
 * implementation of the interactable stuff.
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class PawfficeMain implements Runnable{

    //user
    private User user;

    //testing stuff
    private int x = 250;
    private int y = 250;

    //background color
    private final Color BACKGROUND = new Color(0, 0, 0);

    //Panel for our GUI.
    private JPanel panel;

    /**
     * The run method to set up the graphical user interface
     */
    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Pawffice");
        frame.setPreferredSize(new Dimension(500, 500));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        user = new User(250, 250);
        
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(BACKGROUND);
                g.fillRect(0, 0, getWidth(), getHeight());

                user.draw(g);
            }
        };

        panel.setPreferredSize(new Dimension(500, 500));
        frame.add(panel);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new PawfficeMain());
    }
}