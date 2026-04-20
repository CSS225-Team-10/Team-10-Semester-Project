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

    public RoomObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract boolean isClicked(int mouseX, int mouseY, int cameraX);
}
