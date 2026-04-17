
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Simple doodle pad for Pawffice. Click and drag to draw lines that stay on the
 * screen.
 * 
 * Based off of code from Lab 8
 * 
 * @author Team10, Bug Busters
 * @version May 2026
 */
public class DoodleFrame extends MouseAdapter implements Runnable {

    /**
     * Stores all drawn line segments.
     */
    private ArrayList<Line> lines = new ArrayList<>();

    /**
     * Previous mouse point.
     */
    private Point lastMouse;

    /**
     * Drawing panel.
     */
    private JPanel panel;

    /**
     * The run method to set up the graphical user interface.
     */
    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Pawffice Doodle Pad");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // button to clear frame
        JButton clearButton = new JButton("Clear");

        //button to undo
        JButton undoButton = new JButton("Undo");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearButton);
        buttonPanel.add(undoButton);


        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                redraw(g);
            }
        };

        panel.setBackground(java.awt.Color.WHITE);

      

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        clearButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
          lines.clear();
          panel.repaint();
         }
        });

        undoButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
          if(!lines.isEmpty()){
           lines.remove(lines.size()-1);
           panel.repaint();
          }
         }
        });

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Draw all saved lines.
     */
    private void redraw(Graphics g) {
        for (Line line : lines) {
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }

    /**
     * Updates the location of the mouse press in the panel and sets the
     * animation type.
     *
     * @param e The mouse event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        lastMouse = e.getPoint();
    }

    /**
     * Draw the type of line from where the mouse was first pressed to where the
     * mouse drag ended.
     *
     * @param e The mouse event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        Point current = e.getPoint();

        lines.add(new Line(lastMouse.x, lastMouse.y, current.x, current.y));

        lastMouse = current;

        panel.repaint();
    }

    /**
     * Simple inner class to store a line.
     */
    private class Line {

        int x1, y1, x2, y2;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    /**
     * The main method is responsible for creating a thread that will construct
     * and show the graphical user interface.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new DoodleFrame());
    }
}
