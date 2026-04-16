import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Creates the actual panel for the user to interact with, still needs all the
 * implementation of the interactable stuff.
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class Clock {
    private int x;
    private int y;
    private int width = 120;
    private int height = 120;

    private Image clockImage;

    public Clock(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon icon = new ImageIcon("Images/clock.png");
        clockImage = icon.getImage();
    }

    public void draw(Graphics g, int cameraX) {
        g.drawImage(clockImage, x - cameraX, y, width, height, null);
    }

    //COMPLETE THE REST OF THE CLASS TO ACTUALLY BE A POMODORO TIMER! :D
}