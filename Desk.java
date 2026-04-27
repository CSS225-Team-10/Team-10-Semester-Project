import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Desk implementation for Pawffice, will be an interactable object 
 * that gives the user the ability to take notes.
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class Desk extends RoomObject{
    
    private int width = 214;
    private int height = 131;
    
    private static String savedNotes = "";
    
    private Image deskImage;

    /**
     * The constructor for the desk class.
     * 
     * @param x x coord
     * @param y y coord
     */
    public Desk(int x, int y){
        super(x, y);

        ImageIcon icon = new ImageIcon("Images/desk.png");
        deskImage = icon.getImage();
    }

    /**
     * Draws the desk on the screen.
     * 
     * @param g graphics component
     * @param cameraX the camera to draw in comparison to
     */
    public void draw(Graphics g, int cameraX) {
        g.drawImage(deskImage, x - cameraX, y, width, height, null);
    }

    /**
     * Checks if the desk is clicked at the given mouse coordinates.
     * 
     * @param mouseX x point of mouse
     * @param mouseY y point of mouse
     * @param cameraX x point of camera
     * @return true if clicked, false otherwise
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
    public void launch() {
        javax.swing.SwingUtilities.invokeLater(new NotesFrame());
    }
    //I was gonna write this code but i think instead I wanna get
    //the clicking on pawwfice main working because this
    //is gonna be really involved possibly with web dev stuff...
}