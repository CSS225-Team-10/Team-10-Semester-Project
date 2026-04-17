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

public class Desk extends RoomObject implements Runnable {
    
    private int x;
    private int y;
    private int width = 214;
    private int height = 131;
    
    private static String savedNotes = "";
    
    private Image deskImage;

    public Desk(int x, int y){
        this.x = x;
        this.y = y;

        ImageIcon icon = new ImageIcon("Images/desk.png");
        deskImage = icon.getImage();
    }

    public void draw(Graphics g, int cameraX) {
        g.drawImage(deskImage, x - cameraX, y, width, height, null);
    }

    public boolean isClicked(int mouseX, int mouseY, int cameraX){
        return false;
    }

    public void run(){
        
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Desk(0,0));
    }
    //I was gonna write this code but i think instead I wanna get
    //the clicking on pawwfice main working because this
    //is gonna be really involved possibly with web dev stuff...
}