package Movie_Management_System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;

public class Booking {

	private JFrame frame;
    private String session_id;
    private String cinema;
    private String time;
    private String movie_name;
    private String size;
    private String location;
    private HomePage hp;
    private Customerbe cbe;
    private int child_price;
    private int student_price;
    private int adult_price;
    private int pensioner_price;
    private String search_size;
    private int child_amount = 0;
    private int student_amount = 0;
    private int adult_amount = 0;
    private int pensioner_amount = 0;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Booking(HomePage hp, Customerbe cbe, String movie_name, String session_id, String cinema, String time, String size, String location, String search_size) {
        this.hp = hp;
        this.cbe = cbe;
        this.movie_name = movie_name;
        this.session_id = session_id;
        this.cinema = cinema;
        this.time = time;
        this.size = size;
        this.location = location;
        this.search_size = search_size;
        System.out.println(size);
        if (size.equals("Gold")) {
            child_price = 40;
            adult_price = 42;
            student_price = 40;
            pensioner_price = 40;
        } else if (size.equals("Silver")) {
            child_price = 21;
            adult_price = 26;
            student_price = 23;
            pensioner_price = 23;
        } else if (size.equals("Bronze")) {
            System.out.println("here");
            child_price = 14;
            adult_price = 21;
            pensioner_price = 16;
            student_price = 18;
        }
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Booking " + movie_name + " " + cinema + " " + time);
		lblNewLabel.setBounds(50, 39, 584, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Child (under 12)");
		lblNewLabel_1.setBounds(54, 93, 114, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Student");
		lblNewLabel_2.setBounds(54, 134, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Adult");
		lblNewLabel_3.setBounds(54, 177, 61, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Pensioner");
		lblNewLabel_4.setBounds(54, 220, 61, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
        JLabel lblNewLabel_10 = new JLabel("$0");
		lblNewLabel_10.setBounds(464, 134, 61, 16);
		frame.getContentPane().add(lblNewLabel_10);

		JButton btnNewButton = new JButton("-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(274, 88, 41, 29);
		frame.getContentPane().add(btnNewButton);

        JLabel lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setBounds(331, 93, 34, 16);
		frame.getContentPane().add(lblNewLabel_5);

        btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               int x = Integer.parseInt(lblNewLabel_5.getText());
               if (x != 0) {
                   x --;
                   lblNewLabel_5.setText(Integer.toString(x));
               }
               child_amount = x;
               lblNewLabel_10.setText("$" + (child_amount * child_price + student_amount * student_price +  adult_amount * adult_price + pensioner_amount * pensioner_price));
			}
		});

        JButton btnNewButton_4 = new JButton("+");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(lblNewLabel_5.getText());
                x ++;
                lblNewLabel_5.setText(Integer.toString(x));
                child_amount = x;
                lblNewLabel_10.setText("$" + (child_amount * child_price + student_amount * student_price +  adult_amount * adult_price + pensioner_amount * pensioner_price));
			}
		});
		btnNewButton_4.setBounds(358, 88, 41, 29);
		frame.getContentPane().add(btnNewButton_4);
        

        JLabel lblNewLabel_5_1 = new JLabel("0");
		lblNewLabel_5_1.setBounds(331, 134, 20, 16);
		frame.getContentPane().add(lblNewLabel_5_1);
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.setBounds(274, 129, 41, 29);
        btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               int x = Integer.parseInt(lblNewLabel_5_1.getText());
               if (x != 0) {
                   x --;
                   lblNewLabel_5_1.setText(Integer.toString(x));
               }
               student_amount = x;
               lblNewLabel_10.setText("$" + (child_amount * child_price + student_amount * student_price +  adult_amount * adult_price + pensioner_amount * pensioner_price));
			}
		});
		frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_5 = new JButton("+");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(lblNewLabel_5_1.getText());
                x ++;
                lblNewLabel_5_1.setText(Integer.toString(x));
                student_amount = x;
                lblNewLabel_10.setText("$" + (child_amount * child_price + student_amount * student_price +  adult_amount * adult_price + pensioner_amount * pensioner_price));
			}
		});
		btnNewButton_5.setBounds(358, 129, 41, 29);
		frame.getContentPane().add(btnNewButton_5);


        JLabel lblNewLabel_5_2 = new JLabel("0");
		lblNewLabel_5_2.setBounds(331, 177, 20, 16);
		frame.getContentPane().add(lblNewLabel_5_2);

		
		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.setBounds(274, 172, 41, 29);
        btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               int x = Integer.parseInt(lblNewLabel_5_2.getText());
               if (x != 0) {
                   x --;
                   lblNewLabel_5_2.setText(Integer.toString(x));
               }
               adult_amount = x;
               lblNewLabel_10.setText("$" + (child_amount * child_price + student_amount * student_price +  adult_amount * adult_price + pensioner_amount * pensioner_price));
			}
		});
		frame.getContentPane().add(btnNewButton_2);

        JButton btnNewButton_6 = new JButton("+");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(lblNewLabel_5_2.getText());
                x ++;
                lblNewLabel_5_2.setText(Integer.toString(x));
                adult_amount = x;
                lblNewLabel_10.setText("$" + (child_amount * child_price + student_amount * student_price +  adult_amount * adult_price + pensioner_amount * pensioner_price));
			}
		});
		btnNewButton_6.setBounds(358, 172, 41, 29);
		frame.getContentPane().add(btnNewButton_6);
		

		JLabel lblNewLabel_5_3 = new JLabel("0");
		lblNewLabel_5_3.setBounds(331, 220, 20, 16);
		frame.getContentPane().add(lblNewLabel_5_3);
		
		JButton btnNewButton_3 = new JButton("-");
		btnNewButton_3.setBounds(274, 215, 41, 29);
        btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               int x = Integer.parseInt(lblNewLabel_5_3.getText());
               if (x != 0) {
                   x --;
                   lblNewLabel_5_3.setText(Integer.toString(x));
               }
               pensioner_amount = x;
               lblNewLabel_10.setText("$" + (child_amount * child_price + student_amount * student_price +  adult_amount * adult_price + pensioner_amount * pensioner_price));
			}
		});
		frame.getContentPane().add(btnNewButton_3);

		
		JButton btnNewButton_7 = new JButton("+");
		btnNewButton_7.setBounds(358, 215, 41, 29);
        btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(lblNewLabel_5_3.getText());
                x ++;
                lblNewLabel_5_3.setText(Integer.toString(x));
                pensioner_amount = x;
                lblNewLabel_10.setText("$" + (child_amount * child_price + student_amount * student_price +  adult_amount * adult_price + pensioner_amount * pensioner_price));
			}
		});
		frame.getContentPane().add(btnNewButton_7);
		
		JLabel lblNewLabel_6 = new JLabel("Seats");
		lblNewLabel_6.setBounds(50, 274, 61, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Front");
		lblNewLabel_7.setBounds(54, 312, 61, 16);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("Middle");
		lblNewLabel_7_1.setBounds(54, 357, 61, 16);
		frame.getContentPane().add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_2 = new JLabel("Rear");
		lblNewLabel_7_2.setBounds(54, 398, 61, 16);
		frame.getContentPane().add(lblNewLabel_7_2);
		
        JLabel lblNewLabel_5_3_1 = new JLabel("0");
		lblNewLabel_5_3_1.setBounds(253, 312, 20, 16);
		frame.getContentPane().add(lblNewLabel_5_3_1);

		JButton btnNewButton_8 = new JButton("-");
		btnNewButton_8.setBounds(200, 307, 41, 29);
        btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               int x = Integer.parseInt(lblNewLabel_5_3_1.getText());
               if (x == 0) {
                   return;
               } else {
                   x --;
                   lblNewLabel_5_3_1.setText(Integer.toString(x));
               }
			}
		});
		frame.getContentPane().add(btnNewButton_8);

		JButton btnNewButton_7_1 = new JButton("+");
		btnNewButton_7_1.setBounds(274, 307, 41, 29);
        btnNewButton_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(lblNewLabel_5_3_1.getText());
                x ++;
                lblNewLabel_5_3_1.setText(Integer.toString(x));
			}
		});
		frame.getContentPane().add(btnNewButton_7_1);

		JLabel lblNewLabel_5_3_1_1 = new JLabel("0");
		lblNewLabel_5_3_1_1.setBounds(253, 357, 20, 16);
		frame.getContentPane().add(lblNewLabel_5_3_1_1);
		
		JButton btnNewButton_9 = new JButton("-");
		btnNewButton_9.setBounds(200, 352, 41, 29);
        btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               int x = Integer.parseInt(lblNewLabel_5_3_1_1.getText());
               if (x == 0) {
                   return;
               } else {
                   x --;
                   lblNewLabel_5_3_1_1.setText(Integer.toString(x));
               }
			}
		});
		frame.getContentPane().add(btnNewButton_9);

		JButton btnNewButton_7_2 = new JButton("+");
		btnNewButton_7_2.setBounds(274, 352, 41, 29);
        btnNewButton_7_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(lblNewLabel_5_3_1_1.getText());
                x ++;
                lblNewLabel_5_3_1_1.setText(Integer.toString(x));
			}
		});
		frame.getContentPane().add(btnNewButton_7_2);

        JLabel lblNewLabel_5_3_1_2 = new JLabel("0");
		lblNewLabel_5_3_1_2.setBounds(253, 398, 20, 16);
		frame.getContentPane().add(lblNewLabel_5_3_1_2);

		
		JButton btnNewButton_10 = new JButton("-");
		btnNewButton_10.setBounds(200, 393, 41, 29);
        btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               int x = Integer.parseInt(lblNewLabel_5_3_1_2.getText());
               if (x == 0) {
                   return;
               } else {
                   x --;
                   lblNewLabel_5_3_1_2.setText(Integer.toString(x));
               }
			}
		});
		frame.getContentPane().add(btnNewButton_10);
	
		
		JButton btnNewButton_7_3 = new JButton("+");
		btnNewButton_7_3.setBounds(274, 393, 41, 29);
        btnNewButton_7_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(lblNewLabel_5_3_1_2.getText());
                x ++;
                lblNewLabel_5_3_1_2.setText(Integer.toString(x));
			}
		});
		frame.getContentPane().add(btnNewButton_7_3);
		
		JLabel lblNewLabel_8 = new JLabel("$" + child_price);
		lblNewLabel_8.setBounds(180, 93, 61, 16);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("$" + student_price);
		lblNewLabel_8_1.setBounds(180, 134, 61, 16);
		frame.getContentPane().add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_8_2 = new JLabel("$" + adult_price);
		lblNewLabel_8_2.setBounds(180, 177, 61, 16);
		frame.getContentPane().add(lblNewLabel_8_2);
		
		JLabel lblNewLabel_8_3 = new JLabel("$" + pensioner_price);
		lblNewLabel_8_3.setBounds(180, 220, 61, 16);
		frame.getContentPane().add(lblNewLabel_8_3);
		
		JLabel lblNewLabel_9 = new JLabel("Total Price:");
		lblNewLabel_9.setBounds(464, 88, 92, 16);
		frame.getContentPane().add(lblNewLabel_9);
	
		
		JButton btnNewButton_11 = new JButton("Proceed");
		btnNewButton_11.setBounds(456, 172, 117, 29);
		frame.getContentPane().add(btnNewButton_11);

        // proceed needs the session_id, money, number of people, and need to check
        // If it is now overbooked?
        btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                int total = child_amount + student_amount + adult_amount + pensioner_amount;
                if (cbe.checkFurtherFull(session_id, total)) {
                    ErrorMessage em = new ErrorMessage("Sorry, only " + cbe.checkAvailableSeat(session_id) + " seats are available", true);
                    em.getDialog().setVisible(true);
                } else {
                    if (child_amount + student_amount + adult_amount + pensioner_amount != Integer.parseInt(lblNewLabel_5_3_1.getText()) + Integer.parseInt(lblNewLabel_5_3_1_1.getText()) + Integer.parseInt(lblNewLabel_5_3_1_2.getText())) {
                        ErrorMessage em = new ErrorMessage("The number of people and the number of seats do not match.", true);
                        em.getDialog().setVisible(true);
                    } else {
                        // reserve seats
                        if (child_amount + student_amount + adult_amount + pensioner_amount == 0) {
                            ErrorMessage em = new ErrorMessage("You have not entered the number of audience.", true);
                            em.getDialog().setVisible(true);
                        } else {
                            frame.dispose();
                            cbe.reserve_seats(session_id, total);
                            Transaction t = new Transaction(hp, cbe, child_amount * child_price + student_amount * student_price +  adult_amount * adult_price + pensioner_amount * pensioner_price, total, session_id);
                        }
                    }
                }
			}
		});


        JButton btnNewButton_12 = new JButton("Cancel");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MovieRecords m = new MovieRecords(hp, cbe, movie_name, search_size, location);
			}
		});
        btnNewButton_12.setBounds(456, 215, 117, 29);
        frame.getContentPane().add(btnNewButton_12);
		frame.setBounds(0, 0, 700, 500);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
