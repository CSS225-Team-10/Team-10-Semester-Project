import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Mental health tip screen for Pawwfice
 * 
 * @author Team 10, Bug Busters
 * @version Spring 2026
 */
public class Bed extends RoomObject implements Runnable{
    
    private static ArrayList<String> tips = new ArrayList<>();

    private JLabel label;

    private JButton newTipButton;

    /**
     * 
     */
    @Override
    public void run(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Pawffice Tips");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label = new JLabel("I hope you like this helpful tip!");
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        newTipButton = new JButton("Click for a new Tip");
        newTipButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                label.setText(getRandTip());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newTipButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);



        frame.setVisible(true);
    }

    private ArrayList<String> populateTips(){
        ArrayList<String> tipList = new ArrayList<>();

        tipList.add("If you are struggling to increase your positivity, one thing you can do is practice gratitude! What are some things you are grateful for?");
        tipList.add("Take a few deep breaths! You are allowed to take breaks. Everything will be okay.");
        tipList.add("Take some time to practice Self Care! You can try journalling, taking a walk, breathing, and so many other things! Learn what works best for you.");

        //ADD WAY MORE TIPS LATER :)

        return tipList;
    }

    private String getRandTip(){
        Random rand = new Random();

        return tips.get(rand.nextInt(tips.size()));
    }

    public Bed(){
        tips = populateTips();
    }

    /**
     * The main method is responsible for creating a thread that will construct
     * and show the graphical user interface.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Bed());
    }

}