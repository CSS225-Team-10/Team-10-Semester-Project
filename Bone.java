import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Interactable bone, can be picked up, Baloo will follow and the bone will drop after being unclicked.
 * 
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class Bone extends RoomObject{
    /**
     * Instance variables
     */
    private boolean isClicked;

    private int width = 40;
    private int height = 40;

    private Image bookshelfImage;

    /**
     * Constructor for the Bone class.
     * 
     * @param x x point
     * @param y y point
     */
    public Bone(int x, int y){
        super(x, y);

        isClicked = false;
        ImageIcon icon = new ImageIcon("Images/bookshelf.png");
        bookshelfImage = icon.getImage();
    }

    /**
     * Checks if object is clicked at the point where the mouse is clicking.
     * Must be completed in all classes extending RoomObject.
     * 
     * @param mouseX x point of mouse
     * @param mouseY y point of mouse
     * @param cameraX x point of camera
     * @return
     */
    public boolean isClicked(int mouseX, int mouseY, int cameraX) {
        int screenX = x - cameraX;
        int screenY = y;

        return mouseX >= screenX && mouseX <= screenX + width && mouseY >= screenY && mouseY <= screenY + height;
    }

    /**
     * This will launch the frames we want when we click on the different room objects
     */
    public void launch() {

    }
}
