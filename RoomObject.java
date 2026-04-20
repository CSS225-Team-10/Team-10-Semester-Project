/**
 * Abstract class for all objects in the room, will be used to make code cleaner and more organized,
 *  and to make it easier to add new objects in the future.
 * 
 * @author Team10, Bug Busters
 * @version May 2026
 * 
 */
abstract class RoomObject{
    //These are supposed to say where the object is on the screen
    int x;
    int y;

    /**
     * Abstract constructor, putting the object at x and y.
     * 
     * @param x x point
     * @param y y point
     */
    public RoomObject(int x, int y) {
        this.x = x;
        this.y = y;
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
    public abstract boolean isClicked(int mouseX, int mouseY, int cameraX);
}
