package Movie_Management_System;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SuccessMessage {
    private String message;
    private JLabel errorMsg;
    private JFrame f = new JFrame();
    private JDialog dialog;

    public SuccessMessage(String message){
        this.message = message;
        this.errorMsg = new JLabel(message);
        this.initialise();
    }

    public JDialog getDialog(){
        return this.dialog;
    }

    public void initialise(){
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialog = new JDialog(f, "Success Message", true);
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
