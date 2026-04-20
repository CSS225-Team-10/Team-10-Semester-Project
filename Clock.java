import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Clock implementation for Pawffice, will be an interactable object that gives the user
 * the ability to set a pomodoro timer.
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class Clock extends RoomObject implements Runnable{
    private int x;
    private int y;
    private int width = 128;
    private int height = 128;

    private Image clockImage;

    public Clock(int x, int y) {
        super(x, y);

        ImageIcon icon = new ImageIcon("Images/clock.png");
        clockImage = icon.getImage();
    }

    public void draw(Graphics g, int cameraX) {
        g.drawImage(clockImage, x - cameraX, y, width, height, null);
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
     * 
     */
    @Override
    public void run(){
        TimerFrame timerFrame = new TimerFrame();
        timerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        timerFrame.setTitle("Pomodoro Timer");
        timerFrame.setSize(500, 500);
        timerFrame.setVisible(true);
        
    }

    /**
     * The main method is responsible for creating a thread that will construct
     * and show the graphical user interface.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Clock(0,0));
    }
}