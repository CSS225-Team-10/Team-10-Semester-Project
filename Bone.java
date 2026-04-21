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


    /**
     * Constructor for the Bone class.
     * 
     * @param x x point
     * @param y y point
     */
    public Bone(int x, int y){
        super(x, y);

        isClicked = false;
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
        return isClicked;
    }

    /**
     * This will launch the frames we want when we click on the different room objects;
     */
    public void launch() {
        return;
    }
}
