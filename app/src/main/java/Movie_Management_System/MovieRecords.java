package Movie_Management_System;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.GroupLayout;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;

import java.sql.*;
import java.util.List;
import java.util.*;
public class MovieRecords {
	//private Connection conn;
	private HomePage hp;
	private Customerbe cbe;
	private String movie_name;
	private String size;
	private String location;

	public JFrame frame;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SessionRecords window = new SessionRecords();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public MovieRecords(HomePage hp, Customerbe cbe, String movie_name, String size, String location) {
        this.hp = hp;
		this.cbe = cbe;
		this.movie_name = movie_name;
		this.size = size;
		this.location = location;
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// WindowListener lis = new WindowAdapter() {
        //     public void windowClosing(WindowEvent e) {
        //         System.out.println("closing connection...");
        //         cbe.closeConnection();
        //         System.exit(0);
        //     }
        // };
    
        
//		admin = new cbe();
		frame = new JFrame();
		// frame.addWindowListener(lis);
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Movie Name");
		lblNewLabel.setBounds(34, 42, 98, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSynopsis = new JLabel("Synopsis");
		lblSynopsis.setBounds(34, 80, 98, 16);
		frame.getContentPane().add(lblSynopsis);
		
		textField = new JTextField();

		
		textField.setBounds(144, 37, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		
		textField_1.setColumns(10);
		textField_1.setBounds(34, 108, 240, 75);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(144, 195, 130, 26);
		
		frame.getContentPane().add(textField_2);
		
		JLabel lblClassification = new JLabel("Classification");
		lblClassification.setBounds(34, 200, 98, 16);
		frame.getContentPane().add(lblClassification);
		
		JLabel lblReleaseDate = new JLabel("Release Date");
		lblReleaseDate.setBounds(34, 240, 98, 16);
		frame.getContentPane().add(lblReleaseDate);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(144, 235, 130, 26);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(144, 273, 130, 26);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(144, 315, 130, 26);
		frame.getContentPane().add(textField_5);

		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		textField_4.setEditable(false);
		textField_5.setEditable(false);

		if(!movie_name.equals("")) {
			textField.setText(cbe.getMovieInfoFromName(movie_name).get("name"));
			textField_1.setText(cbe.getMovieInfoFromName(movie_name).get("synopsis"));
			textField_2.setText(cbe.getMovieInfoFromName(movie_name).get("classification"));
			textField_3.setText(cbe.getMovieInfoFromName(movie_name).get("release_date"));
			textField_4.setText(cbe.getMovieInfoFromName(movie_name).get("director"));
			textField_5.setText(cbe.getMovieInfoFromName(movie_name).get("cast"));
		}
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(34, 278, 98, 16);
		frame.getContentPane().add(lblDirector);
		
		JLabel lblCast = new JLabel("Cast");
		lblCast.setBounds(34, 320, 98, 16);
		frame.getContentPane().add(lblCast);
		frame.setBounds(0, 0, 1000, 800);

		
		 // Frame Title
        frame.setTitle("JTable Example");
        frame.setSize(1000, 750); 
		frame.getContentPane().setLayout(null);

        // Column Names
        String[] columnNames = { "Session ID", "Cinema Name", "Screen Size", "Session Time", "Available Seats" };
        DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};

        model.addColumn("Session ID");
        

        model.addColumn("Cinema Name");
        model.addColumn("Screen Size");
        model.addColumn("Session Time");
		model.addColumn("Available Seats");
		String movie_id = cbe.getMovieIdFromName(movie_name);
		String[][] searchResult = cbe.getCustomerMovieSessionInfo(movie_id, size, location);
		for(int i = 0; i < searchResult.length; i++) {
			model.addRow(searchResult[i]);
		}

		model.setColumnIdentifiers(columnNames);

		JTable j = new JTable(model);

		j.removeColumn(j.getColumnModel().getColumn(0));

		TableModel tm_start = j.getModel();
		String[] start_session_ids = new String[tm_start.getRowCount()];
		for (int i = 0; i < tm_start.getRowCount(); i++) {
			for (int k = 0; k < tm_start.getColumnCount(); k++) {
				Object o = tm_start.getValueAt(i, k);
				if(k == 0) {
					start_session_ids[i] = (String)o;
				}
				
			}
		}

        
        j.setBounds(30, 40, 200, 300);
        j.setShowHorizontalLines(true);
        j.setShowVerticalLines(true);
        j.setShowGrid(true);   
        j.setGridColor(Color.BLACK);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        sp.setSize(589, 137);
        sp.setLocation(34, 412);
        frame.getContentPane().add(sp, BorderLayout.WEST);

		JButton btnNewButton_3 = new JButton("Back");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				frame.dispose();
				if (hp.isIs_login()){
					String name = hp.get_username();
					hp.initialise();
					hp.customer_mode(name);
				}else{
					hp.guest_mode();
				}
        	}
        });
        btnNewButton_3.setBounds(684, 37, 117, 29);
		frame.getContentPane().add(btnNewButton_3);

		JButton bookButton = new JButton("Book");
		bookButton.setBounds(684, 408, 117, 29);
		frame.getContentPane().add(bookButton);

		/// for book, need movie_id, movie_name, movie_cinema, movie_time
		// need to check for number of available bookings
		bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				int selected = j.getSelectedRow();
				if (selected == -1) {
					return;
				}
				
				String session_id = (String) tm_start.getValueAt(selected, 0);
				String movie_cinema = (String) tm_start.getValueAt(selected, 1);
				String curr_size = (String) tm_start.getValueAt(selected, 2);
				String movie_time = (String) tm_start.getValueAt(selected, 3);
				if (cbe.checkSessionFull(session_id)) {
					ErrorMessage em = new ErrorMessage("Sorry, the session is full");
					em.getDialog().setVisible(true);
				} else {
					if (hp.isIs_login()) {
						frame.dispose();
						Booking b = new Booking(hp, cbe, movie_name, session_id, movie_cinema, movie_time, curr_size, location, size);
					} else {
						frame.dispose();
						InterRegister i = new InterRegister(hp, cbe, movie_name, session_id, movie_cinema, movie_time, curr_size, location, size);
					}
					
				}
			}
		});
		

		
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
