
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
import java.awt.*;
import java.awt.event.*;


public class InterRegister {

	private JFrame frame;

    private HomePage hp;
	private Customerbe cbe;
	private String movie_name;
	private String search_size;
	private String location;
    private String curr_size;
    private String session_id;
    private String movie_cinema;
    private String movie_time;
    private JButton btnNewButton;
    private JPasswordField passwordField;
    private JTextField textField;

	/**
	 * Create the application.
	 */
	public InterRegister(HomePage hp, Customerbe cbe, String movie_name, String session_id, String movie_cinema, String movie_time, String curr_size, String location, String size) {
        this.cbe = cbe;
        this.hp = hp;
        this.movie_name = movie_name;
        this.search_size = size;
        this.location = location;
        this.curr_size = curr_size;
        this.session_id = session_id;
        this.movie_cinema = movie_cinema;
        this.movie_time = movie_time;
		initialise();
	}

	/**
	 * Initialize the contents of the frame.
	 */
    /*
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
                if (cbe.getUsernames().contains(username)) {
                    ErrorMessage em = new ErrorMessage("Sorry, the username is taken, please enter a different one.", true);
                    em.getDialog().setVisible(true);
                } else {
                    cbe.InsertRegisterInfo(username, password);
                    frame.dispose();
                    //hp.initialise();
					hp.set_login(true);
                    hp.set_username(username);
                    Booking b = new Booking(hp, cbe, movie_name, session_id, movie_cinema, movie_time, curr_size, location, search_size);
                }
            }
        });
		
		JButton btnNewButton_3 = new JButton("Return");
		btnNewButton_3.setBounds(16, 15, 92, 29);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MovieRecords m = new MovieRecords(hp, cbe, movie_name, search_size, location);
            }
        });
		frame.getContentPane().add(btnNewButton_3);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}*/

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
                MovieRecords m = new MovieRecords(hp, cbe, movie_name, search_size, location);
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
                    Booking b = new Booking(hp, cbe, movie_name, session_id, movie_cinema, movie_time, curr_size, location, search_size);
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
