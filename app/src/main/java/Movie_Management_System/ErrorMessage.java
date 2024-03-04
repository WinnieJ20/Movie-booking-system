package Movie_Management_System;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ErrorMessage {
    private String message;
    private JLabel errorMsg;
    private JFrame f = new JFrame();
    private JDialog dialog;

    public ErrorMessage(String message){
        this.message = message;
        this.errorMsg = new JLabel(message);
        this.initialise();
    }

    public ErrorMessage(String message, boolean big) {
        this.message = message;
        this.errorMsg = new JLabel(message);
        this.initialiseBig();
    }

    public JDialog getDialog(){
        return this.dialog;
    }

    public void initialiseBig() {
        this.errorMsg = new JLabel(message);
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialog = new JDialog(f, "Error Message", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setSize(500, 250);
        dialog.setLocationRelativeTo(null);
        dialog.getContentPane().setLayout(null);

        errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        errorMsg.setBounds(30, 51, 400, 46);
        dialog.getContentPane().add(errorMsg);

        JButton ok = new JButton("OK");
        ok.setBounds(224, 144, 51, 29);
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        dialog.getContentPane().add(ok);
    }

    public void initialise(){
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialog = new JDialog(f, "Error Message", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setBounds(200, 50, 224, 130);
        dialog.setLocationRelativeTo(null);
        dialog.getContentPane().setLayout(null);

        errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        errorMsg.setBounds(6, 21, 212, 16);
        dialog.getContentPane().add(errorMsg);

        JButton ok = new JButton("OK");
        ok.setBounds(84, 54, 51, 29);
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        dialog.getContentPane().add(ok);
    }
}
