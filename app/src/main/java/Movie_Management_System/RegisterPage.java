package Movie_Management_System;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.*;

public class RegisterPage {

	private JFrame frame;
    Customerbe cbe;
    HomePage hp;

	/**
	 * Create the application.
	 */
	public RegisterPage(Customerbe cbe, HomePage hp) {
        this.cbe = cbe;
        this.hp = hp;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
		
		
		JPasswordField textArea_password = new JPasswordField();
        textArea_password.setEchoChar('*');
		textArea_password.setBounds(232, 142, 198, 22);
		frame.getContentPane().add(textArea_password);
		
		JTextArea textArea_username = new JTextArea();
		textArea_username.setBounds(232, 93, 198, 22);
		frame.getContentPane().add(textArea_username);
		
		JLabel lblNewLabel = new JLabel("Please enter your username:");
		lblNewLabel.setBounds(32, 93, 226, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please enter your password:");
		lblNewLabel_1.setBounds(32, 142, 188, 16);
		frame.getContentPane().add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(158, 196, 117, 29);
		frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textArea_username.getText().trim();
                String password = textArea_password.getText();
				if (username.equals("") || password.equals("")){
					ErrorMessage em = new ErrorMessage("Empty entry not allowed.");
					em.getDialog().setVisible(true);
				} else{
					if (cbe.getUsernames().contains(username)) {
						ErrorMessage em = new ErrorMessage("Sorry, the username is taken, please enter a different one.", true);
						em.getDialog().setVisible(true);
					} else {
						cbe.InsertRegisterInfo(username, password);
						frame.dispose();
						//hp.initialise();
						hp.set_login(true);
						hp.set_username(username);
						hp.initialise();
						hp.customer_mode(username);
					}
				} 
            }
        });
		
		JButton btnNewButton_3 = new JButton("Return");
		btnNewButton_3.setBounds(16, 15, 92, 29);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                hp.guest_mode();
            }
        });
		frame.getContentPane().add(btnNewButton_3);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
