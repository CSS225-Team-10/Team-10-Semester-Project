//package maybe add?

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class NotesFrame implements Runnable {
    // right now only one copy of notes... with web dev
    // stuff i will prolly want to make it so you can
    // have a lot of different files to save your notes
    // in.
    private static String savedNotes = "";
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


    @Override
    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Desk Notes");
        frame.setPreferredSize(new Dimension(450, 350));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(lightLavender);

        JTextArea textArea = new JTextArea();
        textArea.setBackground(lightLavender);
        textArea.setForeground(coffee);
        textArea.setCaretColor(coffee);
        

        //GET INSTEAD OF SAVEDNOTES HERE

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/savedNotes"))
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();
            String notes = body.split(":\"")[1].replace("\"}", "");

            textArea.setText(notes);
        } catch (Exception ex) {
            System.out.println(ex);
        }


        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(coffee, 1));

        JButton saveButton = new JButton("Save Notes");
        saveButton.setBackground(darkGreen);

        Border border = BorderFactory.createLineBorder(coffee, 1);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedNotes = textArea.getText();

                try {
                    String safeNotes = savedNotes.replace("\"", "\\\"");
                    String jsonBody = "{\"notes\": \"" + safeNotes + "\"}";

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/savedNotes"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.body());
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(lightGreen);
        bottomPanel.setBorder(BorderFactory.createLineBorder(coffee, 1));
        bottomPanel.add(saveButton, BorderLayout.CENTER);
        frame.setBackground(lightLavender);
    

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }
}