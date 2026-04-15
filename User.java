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
        @Override
        public void actionPerformed(ActionEvent e) {
            frame = (frame + 1) % 2;
        }
    };

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
            if (frame == 0){
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

        // Ensure the user stays within the bounds of the world
        if (x < 0) {
            x = 0;
        }
        if (x + width > worldWidth) {
            x = worldWidth - width;
        }
    }

    public void draw(Graphics g, int cameraX) {
        if (userImage != null) {
            g.drawImage(userImage, x - cameraX, 175, width, height, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x - cameraX, y, width, height);
        }
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }
}