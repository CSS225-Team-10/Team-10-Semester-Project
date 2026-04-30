import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * Mental health tip screen for Pawwfice
 * 
 * @author Team 10, Bug Busters
 * @version Spring 2026
 */
public class Bed extends RoomObject implements Runnable {

    private static ArrayList<String> tips = new ArrayList<>();

    private JLabel label;

    private JButton newTipButton;

    private JTextArea tipArea;

    private int width = 163;
    private int height = 262;

    private Image bedImage;
    
    /**
     * Dark green color for panel
     */
    private static Color darkGreen = new Color(52, 88, 48);

    /**
     * Light green color for panel
     */
    private static Color lightGreen = new Color(182, 204, 161);

    /**
     * Light lavender color for panel
     */
    private static Color lightLavender = new Color(233, 214, 236);

    /**
     * Coffee color for panel
     */
    private static Color coffee = new Color(37, 22, 5);


    /**
     * Constructor for the bed, will be an interactable object that gives the user
     * mental health tips when clicked on.
     * 
     * @param x The x-coordinate of the bed
     * @param y The y-coordinate of the bed
     */
    public Bed(int x, int y) {
        super(x, y);
        tips = populateTips();

        ImageIcon icon = new ImageIcon("Images/bed.png");
        bedImage = icon.getImage();
    }

    /**
     * Draws the user on the screen.
     * 
     * @param g       Graphics component
     * @param cameraX the camera to draw in comparison to
     */
    public void draw(Graphics g, int cameraX) {
        g.drawImage(bedImage, x - cameraX, y, width, height, null);
    }

    /**
     * The run method to set up the graphical user interface.
     */
    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Pawffice Tips");
        frame.setPreferredSize(new Dimension(300, 325));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label = new JLabel("I hope you like this helpful tip!");
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        newTipButton = new JButton("Click for a new Tip");
        newTipButton.setBackground(darkGreen);
        newTipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tipArea.setText(getRandTip());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newTipButton);
        buttonPanel.setBackground(lightGreen);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        tipArea = new JTextArea(getRandTip());
        tipArea.setLineWrap(true);
        tipArea.setWrapStyleWord(true);
        tipArea.setEditable(false);
        tipArea.setBackground(lightLavender);

        JScrollPane scrollPane = new JScrollPane(tipArea);

        Border padding = BorderFactory.createLineBorder(coffee, 1);
        scrollPane.setBorder(padding);

        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Populates the list of tips.
     * 
     * @return the list of tips
     */
    private ArrayList<String> populateTips() {
        ArrayList<String> tipList = new ArrayList<>();

        // https://www.carecredit.com/well-u/health-wellness/tips-improve-mental-health/
        // https://mhanational.org/resources/31-tips-to-boost-your-mental-health/

        tipList.add(
                "If you are struggling to increase your positivity, one thing you can do is practice gratitude! What are some things you are grateful for?");
        tipList.add("Take a few deep breaths! You are allowed to take breaks. Everything will be okay.");
        tipList.add("Take some time to practice Self Care! You can try journalling, taking a walk, breathing, and so many other things! Learn what works best for you.");
        tipList.add("Shoot for the moon, land amongst the stars.");
        tipList.add(
                "Prioritize consistent sleep. This will also help your immune system function, enhance brain performance, memory, but most importantly emotional regulation. If you are feeling overwhelmed with tasks, one of the best things you can do is sleep so that you are better prepared to have the brainpower to complete those tasks! Stay happy and healthy and get some rest!");
        tipList.add(
                "Spend some time in nature. You could take a walk, smell some flowers, sit by a tree, go on a picnic, build a snowman, ect. Whatever the weather permits. Nature can help you feel calmer.");
        tipList.add(
                "Physical health is very linked to mental health! Find a way to get active! Go on a short walk, play with your dog or cat, try yoga, or anything else you can think of. Just do whatever feels right for you!");
        tipList.add(
                "Take some time to de-stress and unwind. Find a hobby you enjoy and spend some time on that. You could go on a walk, color in a coloring page, hang out with friends, play a game, ect. This can help lower stress, cortisol levels, and decrease any anxiety or depression.");
        tipList.add(
                "Try a journalling exercise! You can even go do this by clicking on the bookshelf and taking notes there. Spend as much or as little time you need, and try writing down whatever has been on your mind. Take note of any differences in how you feel when you finish. Remember to be kind to yourself.");
        tipList.add(
                "If you have the energy for it, try to clean up your living area. It can be difficult coming back to a messy space when dealing with a lot of stress in your daily life. Clearer spaces can help your mind feel clearer. You can even help others while helping yourself! Clean out your old clothes, toys, books, etc. and compile them to donate!");
        tipList.add(
                "Try a breathing exercise to help you feel calm. One example is called box breathing. This is when you inhale for four seconds, hold for four seconds, exhale for four seconds, and hold one last time for four seconds. This process can be repeated as many times as needed. You can also switch out the numbers for whatever feels best for you.");
        tipList.add(
                "If you are trying to feel better quickly, the best thing you can do is try getting your body moving as much or as often as possible. Excersize helps reduce symptoms of anxiety and depression. You don't have to run a marathon! You can walk your dog, go on a jog, or attend a fitness class. You can take some time to figure out what is best for you.");
        tipList.add(
                "If you're having trouble studying, try out clicking on the clock! This will bring you to a Pomodoro timer. If you're not sure what that is, it is a productivity tool that implements the Pomodoro Technique, which breaks work up into intervals of focus time called \"pomodoros\" seperated by breaks. The idea is to select a single task to focus on, and then set a timer, typically for about 25 minutes, and then work on the task you selected exclusively until the timer rings. Then, you can take a short break to stretch, grab water, practice some self care, ect. and repeat! Happy studying :)");
        tipList.add(
                "Healthy food is good for not only your body, but also your mind. There are associations between how you eat and how you feel. Healthy diets that have many fruits and veggies incorporated with lean protiens are best for mental health.");
        tipList.add(
                "Stay hydrated! Hydration helps make sure that your brain is working optimally. Dehydration can lead to many different health problems, including but not limited to reduced brain cogniiton, dizziness, fatigue, ect. Keep your favorite glass or water bottle handy while working! Take a walk to go refill it on the breaks from your pomodoro timer. Stay well, happy studying!");

        // ADD WAY MORE TIPS LATER :)

        return tipList;
    }

    /**
     * Gives a random tip.
     * 
     * @return returns a random tip
     */
    private String getRandTip() {
        Random rand = new Random();

        return tips.get(rand.nextInt(tips.size()));
    }

    /**
     * Checks if the bed is clicked at the given mouse coordinates.
     * 
     * @param mouseX  x point of mouse
     * @param mouseY  y point of mouse
     * @param cameraX x point of camera
     * @return true if clicked, false otherwise
     */
    public boolean isClicked(int mouseX, int mouseY, int cameraX) {
        int screenX = x - cameraX;

        return mouseX >= screenX && mouseX <= screenX + width
                && mouseY >= y && mouseY <= y + height;
    }

    /**
     * The main method is responsible for creating a thread that will construct
     * and show the graphical user interface.
     */
    public void launch() {
        javax.swing.SwingUtilities.invokeLater(new Bed(0, 0));
    }

}
