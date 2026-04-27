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

public class Bookshelf extends RoomObject{

    private int width = 193;
    private int height = 248;

    private Image bookshelfImage;

    /**
     * The constructor for the bookshelf class.
     * 
     * @param x x coord
     * @param y y coord
     */
    public Bookshelf(int x, int y) {
        super(x, y);

        ImageIcon icon = new ImageIcon("Images/bookshelf.png");
        bookshelfImage = icon.getImage();
    }

    /**
     * Draws the user on the screen.
     * 
     * @param g Graphics component
     * @param cameraX the camera to draw in comparison to
     */
    public void draw(Graphics g, int cameraX) {
        g.drawImage(bookshelfImage, x - cameraX, y, width, height, null);
    }

    /**
     * Checks if the clock has been clicked based on mouse coordinates.
     *
     * @param mouseX The x-coordinate of the mouse
     * @param mouseY The y-coordinate of the mouse
     * @param cameraX The x-coordinate of the camera
     * @return true if the clock is clicked, false otherwise
     */
    @Override
    public boolean isClicked(int mouseX, int mouseY, int cameraX){
        int screenX = x - cameraX;

        return mouseX >= screenX && mouseX <= screenX + width
            && mouseY >= y && mouseY <= y + height;
    }

    /**
     * The main method is responsible for creating a thread that will construct
     * and show the graphical user interface.
     */
    @Override
    public void launch(){
        javax.swing.SwingUtilities.invokeLater(new DoodleFrame());
        
    }

}
