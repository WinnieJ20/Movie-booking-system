package Movie_Management_System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class PaymentSuccessful {

	private JFrame frame;
    private int transaction_id = -1;
    private HomePage hp;
    private Customerbe cbe;
    private String card_number;

	/**
	 * Create the application.
	 */
	public PaymentSuccessful(HomePage hp, Customerbe cbe, String card_user_name, String card_num, int transaction_id) {
        this.transaction_id = transaction_id;
        this.hp = hp;
        this.cbe = cbe;
        this.card_number = card_num;
		initialize(true);
	}

    public PaymentSuccessful(HomePage hp, Customerbe cbe, int transaction_id) {
        this.transaction_id = transaction_id;
        this.hp = hp;
        this.cbe = cbe;
		initialize(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean credit_card) {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 450, 300);
		JLabel lblNewLabel = new JLabel("Payment Successful!");
		lblNewLabel.setFont(new Font("Telugu Sangam MN", Font.PLAIN, 15));
		lblNewLabel.setBounds(152, 46, 202, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Your transaction id is: " + transaction_id + ".");
		lblNewLabel_1.setBounds(59, 96, 187, 16);
		frame.getContentPane().add(lblNewLabel_1);
		if (credit_card) {
            JTextArea txtrDoYouWant = new JTextArea();
            txtrDoYouWant.setText("Do you want to save the\n card info used in this transaction?");
            txtrDoYouWant.setBackground(SystemColor.window);
            txtrDoYouWant.setBounds(59, 154, 245, 38);
            frame.getContentPane().add(txtrDoYouWant);
            
            JRadioButton rdbtnNewRadioButton = new JRadioButton("Yes");
            rdbtnNewRadioButton.setBounds(302, 154, 59, 23);
            frame.getContentPane().add(rdbtnNewRadioButton);

            JRadioButton rdbtnNo = new JRadioButton("No");
            rdbtnNo.setBounds(360, 150, 59, 29);
            frame.getContentPane().add(rdbtnNo);
            frame.setBounds(100, 100, 450, 300);
            rdbtnNo.setSelected(true);

            ButtonGroup bg=new ButtonGroup();    
            bg.add(rdbtnNewRadioButton);
            bg.add(rdbtnNo);

            JButton btnNewButton = new JButton("Home");
            btnNewButton.setBounds(302, 224, 117, 29);
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (rdbtnNewRadioButton.isSelected()) {
                        cbe.save_card(hp.get_username(), card_number);
                    } else {
                        cbe.unsave_card(hp.get_username(), card_number);
                    }
                    frame.dispose();
                    hp.initialise();
                    hp.customer_mode(hp.get_username());
                }
            });
            frame.getContentPane().add(btnNewButton);

        } else {
            JButton btnNewButton = new JButton("Home");
            btnNewButton.setBounds(302, 224, 117, 29);
            frame.getContentPane().add(btnNewButton);
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    hp.initialise();
                    hp.customer_mode(hp.get_username());
                }
            });
            frame.getContentPane().add(btnNewButton);
        }
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
