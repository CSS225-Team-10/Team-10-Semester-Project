import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Bookshelf implementation for Pawffice, will be an interactable object that gives the user
 * the ability to doodle.
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class Bookshelf extends RoomObject implements Runnable{
    private int x;
    private int y;
    private int width = 142;
    private int height = 193;

    private Image bookshelfImage;

    public Bookshelf(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon icon = new ImageIcon("Images/bookshelf.png");
        bookshelfImage = icon.getImage();
    }

    public void draw(Graphics g, int cameraX) {
        g.drawImage(bookshelfImage, x - cameraX, y, width, height, null);
    }

    //COMPLETE THE REST OF THE CLASS TO BE ABLE TO DOODLE! :D
    //CALL DOODLE FRAME I THINK :)
}