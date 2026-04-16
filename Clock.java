import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Clock implementation for Pawffice, will be an interactable object that gives the user
 * the ability to set a pomodoro timer.
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class Clock {
    private int x;
    private int y;
    private int width = 128;
    private int height = 128;

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