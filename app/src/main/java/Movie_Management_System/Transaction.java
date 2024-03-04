package Movie_Management_System;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.Instant;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Transaction {
    // need amount of money, homepage, cbe
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
    private int amount_due = 0;
    private boolean applied_gift_card = false;
    private HomePage hp;
    private Customerbe cbe;
    private int number_people;
    private String session_id;
    private String credit_card_user;
    private String credit_card_number;
    private Timer timer;
    private Timer visual_timer;

	/**
	 * Create the application.
	 */
	public Transaction(HomePage hp, Customerbe cbe, int amount, int people, String session_id) {
        this.hp = hp;
        this.cbe = cbe;
        this.amount_due = amount;
        this.number_people = people;
        this.session_id = session_id;
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setBounds(0, 0, 700, 500);
		JLabel lblNewLabel = new JLabel("120");
		lblNewLabel.setBounds(549, 40, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Your booking will be reserved for 2 minutes before it expires.");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(78, 86, 409, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Payment");
		lblNewLabel_2.setBounds(78, 40, 120, 26);
		lblNewLabel_2.setFont(new Font("Mukta Malar", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Credit Card");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(78, 124, 120, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CardHolder Name:");
		lblNewLabel_4.setBounds(78, 169, 133, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Credit Card Number:");
		lblNewLabel_5.setBounds(78, 212, 133, 16);
		frame.getContentPane().add(lblNewLabel_5);

		String[] ret = cbe.return_saved_card_info(hp.get_username());

		textField = new JTextField();
		textField.setBounds(237, 164, 168, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(237, 207, 168, 26);
		passwordField.setEchoChar('*');
		frame.getContentPane().add(passwordField);

        if (ret.length != 0 && ret[0] != "" && ret[1] != "") {
            textField.setText(ret[0]);
            passwordField.setText(ret[1]);
        }
		
		JLabel lblNewLabel_6 = new JLabel("Amount: ");
		lblNewLabel_6.setBounds(462, 125, 61, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("$" + amount_due);
		lblNewLabel_7.setBounds(535, 125, 61, 16);
		frame.getContentPane().add(lblNewLabel_7);
		
		JButton btnNewButton = new JButton("Pay by Credit Card");
		btnNewButton.setBounds(507, 164, 149, 29);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String card_user_name = textField.getText();
                String card_num = passwordField.getText();
                if (!cbe.validate_credit_card(card_user_name, card_num)) {
                    ErrorMessage em = new ErrorMessage("Card is not valid, payment failed.", true);
                    em.getDialog().setVisible(true);
                    cbe.log_failed_transaction(Instant.now().toString(), hp.get_username(), "card payment failed");
                } else {
                    Transaction.this.timer.stop();
                    Transaction.this.visual_timer.stop();
                    cbe.confirm_book(number_people, hp.get_username(), session_id);
                    int trans_id = cbe.get_transaction_id(hp.get_username(), session_id);
                    frame.dispose();
                    PaymentSuccessful p = new PaymentSuccessful(hp, cbe, card_user_name, card_num, trans_id);
                }
                
            }
        });
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel Transaction");
		btnNewButton_1.setBounds(507, 407, 144, 29);
		frame.getContentPane().add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Transaction.this.timer.stop();
                Transaction.this.visual_timer.stop();
                cbe.cancel_reservation(session_id, number_people);
                cbe.log_failed_transaction(Instant.now().toString(), hp.get_username(), "user cancelled");
                frame.dispose();
                hp.initialise();
                hp.customer_mode(hp.get_username());
            }
        });
		
		JLabel lblNewLabel_3_1 = new JLabel("Gift Card");
		lblNewLabel_3_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(78, 273, 120, 16);

		frame.getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_8 = new JLabel("Gift Card Number: ");
		lblNewLabel_8.setBounds(78, 320, 133, 16);
		frame.getContentPane().add(lblNewLabel_8);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(237, 315, 168, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

        JButton btnNewButton_2 = new JButton("Pay by Gift Card");
		btnNewButton_2.setBounds(507, 315, 144, 29);
        btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String card_num = textField_1.getText();
                if (applied_gift_card) {
                    ErrorMessage em = new ErrorMessage("You have already applied a gift card.", true);
                    em.getDialog().setVisible(true);
                } else {
                    String res = cbe.validate_gift_card(card_num);
                    if (res.equals("n")) {
                        cbe.log_failed_transaction(Instant.now().toString(), hp.get_username(), "card payment failed");
                        ErrorMessage em = new ErrorMessage("Card does not exist.");
                        em.getDialog().setVisible(true);
                    } else if (res.equals("r")) {
                        ErrorMessage em = new ErrorMessage("Card was already redeemed.");
                        em.getDialog().setVisible(true);
                    } else if (res.equals("p")) {
                        Transaction.this.timer.stop();
                        Transaction.this.visual_timer.stop();
                        cbe.redeem_gift_card(card_num);
                        cbe.confirm_book(number_people, hp.get_username(), session_id);
                        int trans_id = cbe.get_transaction_id(hp.get_username(), session_id);
                        frame.dispose();
                        PaymentSuccessful p = new PaymentSuccessful(hp, cbe, trans_id);
                    }
                }
			}
		});
		frame.getContentPane().add(btnNewButton_2);
		
        timer = new Timer(120000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cbe.cancel_reservation(session_id, number_people);
                cbe.log_failed_transaction(Instant.now().toString(), hp.get_username(), "timeout");
            	frame.dispose();
                hp.initialise();
                hp.customer_mode(hp.get_username());
            }    
        });
        visual_timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	int x = Integer.parseInt(lblNewLabel.getText());
            	x --;
            	lblNewLabel.setText(Integer.toString(x));
            }    
        });
        
        visual_timer.start();
        timer.start();
        timer.setRepeats(false);
        visual_timer.setRepeats(true);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
