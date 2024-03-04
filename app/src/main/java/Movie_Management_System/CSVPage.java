package Movie_Management_System;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.JButton;

public class CSVPage {

	private JFrame frame;
	private JTextField textField;
    private Adminbe admin;
    private Customerbe cbe;
    private String role;
    private String type;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public CSVPage(Adminbe admin, Customerbe cbe, String role, String type) {
        this.admin = admin;
        this.cbe = cbe;
        this.role = role;
        this.type = type;
		initialize();
	}

    public static boolean isValidPath(String path) {
        if (path == "") {
            return false;
        }
        java.nio.file.Path ppath = Paths.get(path);
        return Files.isDirectory(ppath);
    }

    public static boolean existing(String path) {
        if (path == "") {
            return false;
        }
        File f = new File(path);
        return f.exists();
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
        frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		String title = "";
        if (type == "M") {
            title = "Download Movies CSV";
        } else if (type == "B") {
            title = "Download Bookings CSV";
        } else if (type == "T") {
            title = "Cancelled Transactions CSV";
        }
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setBounds(47, 35, 180, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(45, 116, 355, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Please enter a download path:");
		lblNewLabel_1.setBounds(47, 89, 190, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Download");
		btnNewButton.setBounds(163, 196, 117, 29);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = textField.getText().trim();
                if (!isValidPath(path) || path.equals("")) {
                    ErrorMessage em = new ErrorMessage("Sorry, the path is invalid");
                    em.getDialog().setVisible(true);
                } else {
                    if ((Character.toString(path.charAt(path.length() - 1)) != File.separator)) {
                        path += File.separator;
                    }
                    String original_name = "";
                    String iter_name = "";
                    if (type == "M") {
                        original_name = "MMS_Movie_Data";
                        iter_name = "MMS_Movie_Data";
                    } else if (type == "B") {
                        original_name = "MMS_Booking_Data";
                        iter_name = "MMS_Booking_Data";
                    } else {
                        original_name = "MMS_Cancelled_Transaction_Data";
                        iter_name = "MMS_Cancelled_Transaction_Data";
                    }
                    int i = 1;
                    while (existing(path + original_name + ".csv")) {
                        original_name = iter_name + i;
                        i++;
                    }
                    try {
                        PrintWriter printWriter = new PrintWriter(path + original_name + ".csv");
                        String[][] data;
                        if (type == "M") {
                            data = admin.getMovieCSVInfo();
                            printWriter.println("movie_id,movie_name,session_id,cinema_name,screen_size,time,synopsis,classification,release_date,director,cast");
                            for (String[] row: data) {
                                printWriter.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                                    row[0], row[1], row[2], row[3], row[4], row[5], row[6],
                                    row[7], row[8], row[9], row[10]
                                );
                            }
                            printWriter.close();
                            SuccessMessage su = new SuccessMessage("Download Successful!");
                            su.getDialog().setVisible(true);
                        } else if (type == "B") {
                            data = admin.getBookingCSVInfo();
                            printWriter.println("session_id,movie_id,movie_name,cinema_name,time,booked_seats,available_seats,number_of_bookings");
                            for (String[] row: data) {
                                printWriter.printf("%s,%s,%s,%s,%s,%s,%s, %s\n",
                                    row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]
                                );
                            }
                            printWriter.close();
                            SuccessMessage su = new SuccessMessage("Download Successful!");
                            su.getDialog().setVisible(true);
                        } else if (type == "T") {
                            data = admin.getCancelledTransactions();
                            printWriter.println("timestamp,username,reason");
                            for (String[] row: data) {
                                printWriter.printf("%s,%s,%s\n",
                                    row[0], row[1], row[2]
                                );
                            }
                            printWriter.close();
                            SuccessMessage su = new SuccessMessage("Download Successful!");
                            su.getDialog().setVisible(true);
                        }
                        printWriter.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        ErrorMessage em = new ErrorMessage("Sorry, failed to generate file");
                        em.getDialog().setVisible(true);
                    }
                }
            }
        });

        JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(292, 196, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MovieTable m = new MovieTable(admin, cbe, role);
            }
        });
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
