import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
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

    private JTextArea tipArea;

    /**
     * 
     */
    @Override
    public void run(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Pawffice Tips");
        frame.setPreferredSize(new Dimension(300, 325));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label = new JLabel("I hope you like this helpful tip!");
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        newTipButton = new JButton("Click for a new Tip");
        newTipButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                tipArea.setText(getRandTip());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newTipButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        tipArea = new JTextArea(getRandTip());
        tipArea.setLineWrap(true);
        tipArea.setWrapStyleWord(true);
        tipArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(tipArea);

        Border padding = BorderFactory.createEmptyBorder(10,15,10,15);
        scrollPane.setBorder(padding);


        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    private ArrayList<String> populateTips(){
        ArrayList<String> tipList = new ArrayList<>();

        tipList.add("If you are struggling to increase your positivity, one thing you can do is practice gratitude! What are some things you are grateful for?");
        tipList.add("Take a few deep breaths! You are allowed to take breaks. Everything will be okay.");
        tipList.add("Take some time to practice Self Care! You can try journalling, taking a walk, breathing, and so many other things! Learn what works best for you.");
        tipList.add("Shoot for the moon, land amongst the stars.");
        tipList.add("Prioritize consistent sleep. This will also help your immune system function, enhance brain performance, memory, but most importantly emotional regulation. If you are feeling overwhelmed with tasks, one of the best things you can do is sleep so that you are better prepared to have the brainpower to complete those tasks! Stay happy and healthy and get some rest!");
        tipList.add("Spend some time in nature. You could take a walk, smell some flowers, sit by a tree, go on a picnic, build a snowman, ect. Whatever the weather permits. Nature can help you feel calmer.");
        tipList.add("Physical health is very linked to mental health! Find a way to get active! Go on a short walk, play with your dog or cat, try yoga, or anything else you can think of. Just do whatever feels right for you!");
        tipList.add("Take some time to de-stress and unwind. Find a hobby you enjoy and spend some time on that. You could go on a walk, color in a coloring page, hang out with friends, play a game, ect. This can help lower stress, cortisol levels, and decrease any anxiety or depression.");
        tipList.add("Try a journalling exercise! You can even go do this by clicking on the bookshelf and taking notes there. Spend as much or as little time you need, and try writing down whatever has been on your mind. Take note of any differences in how you feel when you finish. Remember to be kind to yourself.");
        tipList.add("If you have the energy for it, try to clean up your living area. It can be difficult coming back to a messy space when dealing with a lot of stress in your daily life. Clearer spaces can help your mind feel clearer. You can even help others while helping yourself! Clean out your old clothes, toys, books, etc. and compile them to donate!");
        tipList.add("Try a breathing exercise to help you feel calm. One example is called box breathing. This is when you inhale for four seconds, hold for four seconds, exhale for four seconds, and hold one last time for four seconds. This process can be repeated as many times as needed. You can also switch out the numbers for whatever feels best for you.");
        

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