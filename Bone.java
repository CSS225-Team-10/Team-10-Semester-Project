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
}
