
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A simple Pomodoro timer. User enters work minutes and break minutes. Timer
 * counts down work time, then break time.
 * 
 * I watched a YouTube video by Winston Lievsay and altered it to implement this
 * as pomodoro.
 * 
 * work needs to be done on formatting and making it more intuitive
 * 
 * 
 * @author Team 10
 * @version April 2026
 */

public class TimerFrame extends JFrame implements Runnable {

    // labels for user to enter time requested for work and break
    private JLabel workLabel, breakLabel, timerLabel, sessionLabel;

    // time left in seconds
    private int counter;

    // work seconds
    private int workSeconds;

    // break seconds
    private int breakSeconds;

    // text field to enter value for work time and break time
    private JTextField workField, breakField;

    // buttons to start, stop and reset timers
    private JButton startButton, stopButton, resetButton;

    // timer
    private Timer timer;

    // check if in work session or break
    private boolean isWorkSession;

    /**
     * Constructor that creates timer frame
     */
    public TimerFrame() {
        setTitle("Pomodoro Timer");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 10, 10, 10));

        workLabel = new JLabel("Work minutes:", SwingConstants.CENTER);
        workField = new JTextField(5);

        breakLabel = new JLabel("Break minutes:", SwingConstants.CENTER);
        breakField = new JTextField(5);

        sessionLabel = new JLabel("Waiting to start", SwingConstants.CENTER);
        timerLabel = new JLabel("00:00", SwingConstants.CENTER);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        add(workLabel);
        add(workField);
        add(breakLabel);
        add(breakField);
        add(sessionLabel);
        add(timerLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(buttonPanel);

        // timer runs every second
        timer = new Timer(1000, new TimeClass());

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });
    }

    // start button
    private void startTimer() {
        int workMinutes = Integer.parseInt(workField.getText());
        int breakMinutes = Integer.parseInt(breakField.getText());

        workSeconds = workMinutes * 60;
        breakSeconds = breakMinutes * 60;

        isWorkSession = true;
        counter = workSeconds;

        sessionLabel.setText("Work Time");
        timerLabel.setText(formatTime(counter));

        timer.start();
    }

    // reset button
    public void resetTimer() {
        timer.stop();
        counter = 0;
        timerLabel.setText("00:00");
        sessionLabel.setText("Waiting to start");
        workField.setText("");
        breakField.setText("");
    }

    // timer logic
    private class TimeClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            counter--;
            timerLabel.setText(formatTime(counter));

            if (counter == 0) {
                Toolkit.getDefaultToolkit().beep();

                if (isWorkSession) {
                    isWorkSession = false;
                    counter = breakSeconds;
                    sessionLabel.setText("Break Time");
                    timerLabel.setText(formatTime(counter));
                } else {
                    timer.stop();
                    sessionLabel.setText("Done!");
                    timerLabel.setText("00:00");
                }
            }
        }
    }

    // helper method to display MM:SS
    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        String minString = String.valueOf(minutes);
        String secString = String.valueOf(seconds);

        if (seconds < 10) {
            secString = "0" + secString;
        }

        if (minutes < 10) {
            minString = "0" + minString;
        }

        return minString + ":" + secString;
    }

    @Override
    public void run() {
        setVisible(true);
    }

    public void launch() {
        javax.swing.SwingUtilities.invokeLater(this);
    }
}
