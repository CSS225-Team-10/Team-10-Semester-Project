import java.awt.Graphics;
import java.awt.Color;;
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
    private int width = 40; 
    private int height = 40;
    private int speed = 10;

    /* movement direction */
    private boolean movingLeft = false; 
    private boolean movingRight = false;

    
    public User(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        //if moving left, subtract from speed
        if (movingLeft) {
            x -= speed;
        }
        //if moving left, add to speed
        if (movingRight) {
            x += speed;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x, y, width, height);
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
}