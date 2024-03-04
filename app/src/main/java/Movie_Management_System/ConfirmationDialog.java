package Movie_Management_System;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ConfirmationDialog {

    private JFrame frame = new JFrame();
    private JLabel message;
    private String response;

    /**
     * Create the application.
     */

    public ConfirmationDialog(String input) {
        setMessage(input);
        initialize();
    }

    public void setMessage(String input) {
        this.message = new JLabel(input);
    }

    public void setResponse(int command) {
        if (command == 0) {
            this.response = "N";
        }
        else if (command == 1) {
            this.response = "Y";
        }
    }

    public String getResponse() {
        return this.response;
    }

    public void close() {
        frame.dispose();
        frame.setVisible(false);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame.getContentPane().setLayout(null);
        setMessage("Are you sure you want to remove this movie?");
        frame.setBounds(100, 100, 450, 300);
        frame.setSize(500, 325);

        message.setBounds(45,75,361,41);
        frame.getContentPane().add(message);

        JButton removeYes = new JButton("OK");
        removeYes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setResponse(1);
            }
        });
        removeYes.setBounds(102, 169, 117, 29);
        frame.getContentPane().add(removeYes);

        JButton removeNo = new JButton("Cancel");
        removeNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setResponse(0);
            }
        });
        removeNo.setBounds(281, 169, 117, 29);
        frame.getContentPane().add(removeNo);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

}
