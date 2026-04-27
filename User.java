import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for creating the user.
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class User {

    // Instance variables

    /* physics */
    private int x;
    private int y;
    private int width = 128;
    private int height = 128;
    private int speed = 4;

    /* movement direction */
    private boolean movingLeft = false;
    private boolean movingRight = false;

    private int frame = 0;

    private int worldWidth;

    private Image userImage;
    
    private boolean isClickedState = false;

    /**
     * The constructor for the user
     * 
     * @param x x coord
     * @param y y coord
     * @param worldWidth width of the world
     */
    public User(int x, int y, int worldWidth) {
        this.x = x;
        this.y = y;
        this.worldWidth = worldWidth;

        ImageIcon icon = new ImageIcon("Images/Baloo-Idle.png");
        userImage = icon.getImage();

        Timer timer = new Timer(ANIMATION_RATE, change);
        timer.start();
    }

    static final int ANIMATION_RATE = 400;

    ActionListener change = new ActionListener() {
        /**
         * Actionperformed method
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = (frame + 1) % 2;
        }
    };

    /**
     * This is a timer when clicked to allow for the heart animation for baloo!
     * 
     * @param mouseX x point of mouse
     * @param mouseY y point of mouse
     * @param cameraX x point of camera
     */
    public void clicked(int mouseX, int mouseY, int cameraX) {
        if (isClicked(mouseX, mouseY, cameraX)) {
            isClickedState = true;

            // 2 sec delay
            Timer revertTimer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isClickedState = false;
                    ((Timer) e.getSource()).stop();
                }
            });
            revertTimer.setRepeats(false);
            revertTimer.start();
        }
    }

    /**
     * Updates the user's position and animation frame based on movement and click state.
     */
    public void update() {
        // if moving left, subtract from speed
        if (movingLeft) {
            x -= speed;
            ImageIcon icon;
            if (frame == 0) {
                icon = new ImageIcon("Images/Baloo-WalkL-1.png");
            } else {
                icon = new ImageIcon("Images/Baloo-WalkL-2.png");
            }
            userImage = icon.getImage();

        }
        // if moving right, add to speed
        if (movingRight) {
            x += speed;
            ImageIcon icon;
            if (frame == 0) {
                icon = new ImageIcon("Images/Baloo-WalkR-1.png");
            } else {
                icon = new ImageIcon("Images/Baloo-WalkR-2.png");
            }
            userImage = icon.getImage();
        }

        if (!movingRight && !movingLeft) {

            ImageIcon icon = new ImageIcon("Images/Baloo-Idle.png");
            userImage = icon.getImage();
        }

        if (isClickedState) {
            userImage = new ImageIcon("Images/heart-Sprite.png").getImage();
            return; 
        }

        if (movingRight && movingLeft) {

            ImageIcon icon = new ImageIcon("Images/Baloo-Idle.png");
            userImage = icon.getImage();
        }

        // Ensure the user stays within the bounds of the world
        if (x < 0) {
            x = 0;
        }
        if (x + width > worldWidth) {
            x = worldWidth - width;
        }


    }

    /**
     * Draws the user on the screen.
     * 
     * @param g Graphics component
     * @param cameraX the camera to draw in comparison to
     */
    public void draw(Graphics g, int cameraX) {
        if (userImage != null) {
            g.drawImage(userImage, x - cameraX, 175, width, height, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x - cameraX, y, width, height);
        }
    }

    /**
     * Method to check if checked and set movingleft.
     * 
     * @param movingLeft if moving left
     */
    public void setMovingLeft(boolean movingLeft) {
        if (isClickedState) {
            this.movingLeft = false;
            return; 
        }
        this.movingLeft = movingLeft;
    }

    /**
     * Method to check if checked and set movingright.
     * 
     * @param movingRight if moving right
     */
    public void setMovingRight(boolean movingRight) {
        if (isClickedState) {
            this.movingRight = false;
            return; 
        }
        this.movingRight = movingRight;
    }

    /**
     * Returns the x coordinate.
     * 
     * @return the x coord
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate.
     * 
     * @return the y coord
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width.
     * 
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Checks if the dog is clicked at the given mouse coordinates.
     * 
     * @param mouseX  x point of mouse
     * @param mouseY  y point of mouse
     * @param cameraX x point of camera
     * @return true if clicked, false otherwise
     */
    public boolean isClicked(int mouseX, int mouseY, int cameraX) {
        int screenX = x - cameraX;
        return mouseX >= screenX && mouseX <= screenX + width && mouseY >= y && mouseY <= y + height;
    }
}