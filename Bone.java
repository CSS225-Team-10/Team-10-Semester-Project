import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Interactable bone, can be picked up, Baloo will follow and the bone will drop
 * after being unclicked.
 * 
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class Bone extends RoomObject implements Runnable {
    /**
     * Instance variables
     */
    private boolean isClicked;

    private int width = 40;
    private int height = 40;


    private Image boneImage;

    /**
     * Constructor for the Bone class.
     * 
     * @param x x point
     * @param y y point
     */
    public Bone(int x, int y) {
        super(x, y);

        isClicked = false;
        ImageIcon icon = new ImageIcon("Images/bone.png");
        boneImage = icon.getImage();
    }

    /**
     * Checks if object is clicked at the point where the mouse is clicking.
     * Must be completed in all classes extending RoomObject.
     * 
     * @param mouseX  x point of mouse
     * @param mouseY  y point of mouse
     * @param cameraX x point of camera
     * @return
     */
    public boolean isClicked(int mouseX, int mouseY, int cameraX) {
        int screenXLeft = x - cameraX - width/2;
        int screenXRight = x - cameraX + width/2;
        int screenYTop = y - height/2;
        int screenYBottom = y + height/2;

        return mouseX >= screenXLeft && mouseX <= screenXRight && mouseY >= screenYTop && mouseY <= screenYBottom;
    }

    /**
     * Draws the bone on the screen.
     * 
     * @param g       Graphics component
     * @param cameraX the camera to draw in comparison to
     */
    public void draw(Graphics g, int cameraX) {
        /**g.fillOval(x - cameraX - width/2 - 2, y - height/2 - 2, 4, 4);
        g.fillOval(x - cameraX + width/2 - 2, y - height/2 - 2, 4, 4);
        g.fillOval(x - cameraX - width/2 - 2, y + height/2 - 2, 4, 4);
        g.fillOval(x - cameraX + width/2 - 2, y + height/2 - 2, 4, 4);**/

        g.drawImage(boneImage, x - cameraX - width/2, y - height/2, width, height, null);
    }

    /**
     * Method to set the isClicked instance variable to true.
     * 
     */
    public void clicked() {
        isClicked = true;
    }

    /**
     * Sets the clicked state of the bone.
     * 
     * @param clicked the new clicked state of the bone :P
     */
    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    /**
     * Drags the bone if the bone is clicked!
     * 
     * @param mouseX
     * @param mouseY
     */
    public void dragBone(int mouseX, int mouseY) {
        if (isClicked) {
            x = mouseX;
            y = mouseY;
        }
    }

    /**
     * Runs the bone's behavior in a separate thread.
     */
    @Override
    public void run() {
    }

    /**
     * This will launch the frames we want when we click on the different room
     * objects
     */
    public void launch() {
        javax.swing.SwingUtilities.invokeLater(new Bone(0, 0));
    }
}
