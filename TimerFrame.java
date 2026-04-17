
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A program in which the user enters their time requested 
 * and a timer starts counting down from the requested value.
 * 
 * I watched a YouTube video by Winston Lievsay to implement thsi
 * 
 * Pomodoro to be implemented which will allow selection of
 * hours and minutes 
 * 
 * @author Team 10
 * @version April 2026
 */
public class TimerFrame extends JFrame {

    JLabel promptLabel, timerLabel;
    int counter;
    JTextField tf;
    JButton button;

    Timer timer;

    public TimerFrame() {
        setLayout(new GridLayout(2, 2, 5, 5));

        promptLabel = new JLabel("Enter seconds:", SwingConstants.CENTER);

        tf = new JTextField(5);
        add(tf);

        button = new JButton("Start timing");

        add(button);

        timerLabel = new JLabel("Waiting...", SwingConstants.CENTER);
        add(timerLabel);


        event e = new event();

        button.addActionListener(e);
    }

    public class event implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int count = (int)(Double.parseDouble(tf.getText()));
            timerLabel.setText("Time left: " + count);

            TimeClass tc = new TimeClass(count);
            timer = new Timer(1000, tc);
            timer.start();
        }
    }

    public class TimeClass implements ActionListener{
        int counter;

        public TimeClass(int counter){
            this.counter = counter;
        }

        public void actionPerformed(ActionEvent tc){
            counter--;

            if(counter >= 1){
                timerLabel.setText("Time left: " + counter);
            }else{
                timer.stop();
                timerLabel.setText("Done!");
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
    public static void main(String[] args) {
        TimerFrame gui = new TimerFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setTitle("Timer Frame");
        gui.setVisible(true);
    }
}
