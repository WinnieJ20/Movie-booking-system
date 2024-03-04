package Movie_Management_System;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CustomerLogin {
    private JFrame frame;
    private JButton btnNewButton;
    private JPasswordField passwordField;
    private JTextField textField;
    private Customerbe cbe;
    private HomePage hp;

    public CustomerLogin(Customerbe cbe, HomePage hp){
        this.hp = hp;
        this.cbe = cbe;
    }

    public void initialise(){
        frame = new JFrame();
        frame.setTitle("Customer Login");
        //frame.setSize(1000, 750);
        frame.setBounds(100, 100, 450, 300);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Username:");
        lblNewLabel_1.setBounds(29, 81, 73, 16);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(114, 76, 225, 26);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Password:");
        lblNewLabel.setBounds(29, 127, 73, 16);
        frame.getContentPane().add(lblNewLabel);

        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setBounds(114, 121, 225, 29);
        frame.getContentPane().add(passwordField);

        JButton breturn = new JButton("Return");
        breturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                hp.guest_mode();
            }
        });
        breturn.setBounds(11, 10, 85, 29);
        frame.getContentPane().add(breturn);

        btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());
                boolean result = cbe.check_login(username, password);
                if (result){
                    frame.dispose();
                    //hp.initialise();
                    hp.set_login(true);
                    hp.set_username(username);
                    hp.initialise();
                    hp.customer_mode(username);
                }else{
                    ErrorMessage em = new ErrorMessage("Incorrect username or password");
                    em.getDialog().setVisible(true);
                    textField.setText("");
                    passwordField.setText("");
                }
            }
        });
        btnNewButton.setBounds(161, 188, 117, 29);
        frame.getContentPane().add(btnNewButton);

        WindowListener lis = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("closing connection...");
                cbe.closeConnection();
                System.exit(0);
            }
        };
        frame.addWindowListener(lis);

        frame.setVisible(true);
    }

    public JFrame getFrame(){
        return this.frame;
    }
}
