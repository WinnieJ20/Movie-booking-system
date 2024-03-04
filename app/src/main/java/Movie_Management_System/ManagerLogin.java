package Movie_Management_System;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ManagerLogin {

    private JFrame frame;
    private JButton btnNewButton;
    private JPasswordField passwordField;
    private JTextField textField;
    private Adminbe abe;
    private Customerbe cbe;

    /**
     * Create the application.
     */
    public ManagerLogin(Adminbe abe, Customerbe cbe) {
        this.abe = abe;
        this.cbe = cbe;
        this.initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.setTitle("Cinema Staff Login");
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
                HomePage h = new HomePage(abe, cbe);
                h.guest_mode();
            }
        });
        breturn.setBounds(11, 10, 85, 29);
        frame.getContentPane().add(breturn);

        btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());
                String result = abe.check_adminLogin(username, password);
                if (result.equals("M")){
                    frame.dispose();
                    MovieTable sp = new MovieTable(abe, cbe, "M");
                    //ManagerPage mp = new ManagerPage(abe);
                }else if (result.equals("S")){
                    frame.dispose();
                    MovieTable sp = new MovieTable(abe, cbe, "S");
//                    textField.setText("");
//                    passwordField.setText("");
                }else{
                    ErrorMessage em = new ErrorMessage("Incorrect username or password");
                    em.getDialog().setVisible(true);
                    textField.setText("");
                    passwordField.setText("");
                }
            }
        });
        WindowListener lis = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("closing connection...");
                abe.closeConnection();
                System.exit(0);
            }
        };
    
        frame.addWindowListener(lis);
        btnNewButton.setBounds(161, 188, 117, 29);
        frame.getContentPane().add(btnNewButton);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
