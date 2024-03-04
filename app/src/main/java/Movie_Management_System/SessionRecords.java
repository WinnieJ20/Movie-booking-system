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
public class SessionRecords {
	private Connection conn;
	private Adminbe adminbe;
	private String movie_id;

	public JFrame frame;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private String position;
	private Customerbe cbe;

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
	public SessionRecords(Adminbe adminbe, Customerbe cbe, String movie_id, String role) {
		this.cbe = cbe;
		this.position = role;
		this.adminbe = adminbe;
		this.movie_id = movie_id;
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// WindowListener lis = new WindowAdapter() {
        //     public void windowClosing(WindowEvent e) {
        //         System.out.println("closing connection...");
        //         adminbe.closeConnection();
        //         System.exit(0);
        //     }
        // };
    
        
//		admin = new Adminbe();
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

		if(!movie_id.equals("")) {
			textField.setText(adminbe.getMovieInfo(movie_id).get("name"));
			textField_1.setText(adminbe.getMovieInfo(movie_id).get("synopsis"));
			textField_2.setText(adminbe.getMovieInfo(movie_id).get("classification"));
			textField_3.setText(adminbe.getMovieInfo(movie_id).get("release_date"));
			textField_4.setText(adminbe.getMovieInfo(movie_id).get("director"));
			textField_5.setText(adminbe.getMovieInfo(movie_id).get("cast"));
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
        String[] columnNames = { "Session ID", "Cinema Name", "Screen Size", "Session Time", "Total Seats" };
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Session ID");

        model.addColumn("Cinema Name");
        model.addColumn("Screen Size");
        model.addColumn("Session Time");
		model.addColumn("Total Seats");
		for(int i = 0; i < adminbe.getMovieSessionInfo(movie_id).length; i++) {
			model.addRow(adminbe.getMovieSessionInfo(movie_id)[i]);
		}

		model.setColumnIdentifiers(columnNames);

		JTable j = new JTable(model);

		j.removeColumn(j.getColumnModel().getColumn(0));

		TableModel tm_start = j.getModel();
		String[] start_session_ids = new String[tm_start.getRowCount()];
		List<String> screen_sizes = new ArrayList<>();
		screen_sizes.add("Gold");
		screen_sizes.add("Bronze");
		screen_sizes.add("Silver");

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
        
        JButton btnNewButton = new JButton("Add Session");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		model.addRow(new String[] {"", "", ""});
        		Rectangle last =j.getCellRect(j.getRowCount()-1, 0, true);
        		j.scrollRectToVisible(last);
        		JScrollBar bar = sp.getVerticalScrollBar();
                bar.setValue(bar.getMaximum());
        	}
        });
        btnNewButton.setBounds(671, 433, 130, 29);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Remove Session");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (j.getSelectedRow() == -1) {
        			return;
        		}
        		IntStream.of(j.getSelectedRows())
                .boxed()
                .sorted(Collections.reverseOrder())
                .map(j::convertRowIndexToModel) // support for sorted table
                .forEach(model::removeRow);
        	}
        });
        btnNewButton_1.setBounds(671, 474, 130, 29);
        frame.getContentPane().add(btnNewButton_1);
        
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				HashMap<String, String> info = new HashMap<>();
				List<String> classifications = new ArrayList<>();
				classifications.add("G");
				classifications.add("PG");
				classifications.add("M");
				classifications.add("MA15+");
				classifications.add("R18+");
				try {
					Integer release_date = Integer.parseInt(textField_3.getText());
				} catch(NumberFormatException num_exception) {
					ErrorMessage em = new ErrorMessage("Release date must be numeric.");
                    em.getDialog().setVisible(true);
					textField_3.setText("");
				}
				
				boolean null_session_flag = false;
				boolean wrong_time = false;
				boolean wrong_location = false;
				boolean wrong_seats = false;
				boolean wrong_size = false;
				TableModel tm = j.getModel();
				String[][] tableData = new String[tm.getRowCount()][tm.getColumnCount()];

				for (int i = 0; i < tm.getRowCount(); i++) {
					for (int k = 0; k < tm.getColumnCount(); k++) {
						Object o = tm.getValueAt(i, k);
						tableData[i][k] = (String)o;
						if(tableData[i][k] == null) {
							null_session_flag = true;
						}
						else if(k == 1) {
							String[] locations = adminbe.get_all_locations();	
							if(!Arrays.asList(locations).contains(tableData[i][k])) {
								wrong_location = true;
							}		
						}
						else if(k == 2) {
							if(!screen_sizes.contains(tableData[i][k])) {
								wrong_size = true;
							}
						}
						else if(k == 3) {
							if(!tableData[i][k].matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})")) {
								wrong_time = true;
							} else {
								String session_time = tableData[i][k];
								String year = session_time.substring(0, 4);
								if(Integer.parseInt(year) < 2021) {
									wrong_time = true;
								}
								String month = session_time.substring(5, 7);
								if(Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1) {
									wrong_time = true;
								}
								String day = session_time.substring(8, 10);
								if(Integer.parseInt(day) > 31 || Integer.parseInt(day) < 1) {
									wrong_time = true;
								}
								String hour = session_time.substring(11, 13);
								if(Integer.parseInt(hour) > 24 || Integer.parseInt(hour) < 0) {
									wrong_time = true;
								}
								String min = session_time.substring(14, 16);
								if(Integer.parseInt(min) > 59 || Integer.parseInt(min) < 0) {
									wrong_time = true;
								}
								String sec = session_time.substring(17, 19);
								if(Integer.parseInt(sec) > 59 || Integer.parseInt(sec) < 0) {
									wrong_time = true;
								}
								// System.out.println(month);
								// System.out.println(day);
								// System.out.println(hour);
								// System.out.println(min);
								// System.out.println(sec);
							
							}
						}
						else if(k == 4) {
							try {
								Integer num_seats = Integer.parseInt(tableData[i][k]);
							} catch(NumberFormatException num_seat_exception) {
								wrong_seats = true;
							}
						}
					}
				}

				// Sanity checks.
				if(textField.getText().equals("")) {
					ErrorMessage em = new ErrorMessage("Movie Name cannot be null.");
                    em.getDialog().setVisible(true);
                    textField.setText("");
				} else if(textField_1.getText().equals("")) {
					ErrorMessage em = new ErrorMessage("Synopsis cannot be null.");
                    em.getDialog().setVisible(true);
                    textField_1.setText("");
				} else if(textField_2.getText().equals("")) {
					ErrorMessage em = new ErrorMessage("Classification cannot be null.");
                    em.getDialog().setVisible(true);
					textField_2.setText("");
				} else if(textField_3.getText().equals("")) {
					ErrorMessage em = new ErrorMessage("Release date cannot be null.");
                    em.getDialog().setVisible(true);
					textField_3.setText("");
				} else if(textField_4.getText().equals("")) {
					ErrorMessage em = new ErrorMessage("Director cannot be null.");
                    em.getDialog().setVisible(true);
                    textField_4.setText("");
				} else if(textField_5.getText().equals("")) {
					ErrorMessage em = new ErrorMessage("Cast cannot be null.");
                    em.getDialog().setVisible(true);
					textField_5.setText("");
				} else if(Integer.parseInt(textField_3.getText()) > 2021) {
					ErrorMessage em = new ErrorMessage("Release date cannot be in future.");
                    em.getDialog().setVisible(true);
					textField_3.setText("");
				} else if(Integer.parseInt(textField_3.getText()) < 0) {
					ErrorMessage em = new ErrorMessage("Release date cannot be negative.");
                    em.getDialog().setVisible(true);
					textField_3.setText("");
				} else if(!classifications.contains(textField_2.getText())) {
					ErrorMessage em = new ErrorMessage("Class.: G/PG/MA15+/M/R18+.");
                    em.getDialog().setVisible(true);
					textField_2.setText("");
				} else if(null_session_flag) {
					ErrorMessage em = new ErrorMessage("Table data cannot be null.");
					em.getDialog().setVisible(true);
				} else if(wrong_seats) {
					ErrorMessage em = new ErrorMessage("Total seats must be numeric.");
					em.getDialog().setVisible(true);
				} else if(wrong_size) {
					ErrorMessage em = new ErrorMessage("Screen size: Bronze/Silver/Gold.");
					em.getDialog().setVisible(true);
				} else if(wrong_time) {
					ErrorMessage em = new ErrorMessage("Time: YYYY-MM-DD HH:MM:SS");
					em.getDialog().setVisible(true);
				} else if(wrong_location) {
					ErrorMessage em = new ErrorMessage("Cinema names must be valid.");
					em.getDialog().setVisible(true);
				}
				else {
					info.put("name", textField.getText());
					info.put("synopsis", textField_1.getText());
					info.put("classification", textField_2.getText());
					info.put("release_date", textField_3.getText());
					info.put("director", textField_4.getText());
					info.put("cast", textField_5.getText());
			
					tm = j.getModel();
					tableData = new String[tm.getRowCount()][tm.getColumnCount()];

					String[] saved_session_ids = new String[tm.getRowCount()];
					String error_msg = null;
					for (int i = 0; i < tm.getRowCount(); i++) {
						for (int k = 0; k < tm.getColumnCount(); k++) {
							Object o = tm.getValueAt(i, k);
							tableData[i][k] = (String)o;
							// System.out.println(i + k + tableData[i][k]);
							if(k == 0) {
								saved_session_ids[i] = tableData[i][k];
							}
						}
					}

					// start_session_ids, startList,: list of ids at the start
					List<String> startList = new ArrayList<>(Arrays.asList(start_session_ids));
					List<String> saved_session_id_list = new ArrayList<>(Arrays.asList(saved_session_ids));
					for (String session_id : start_session_ids) {
						if (saved_session_id_list.contains(session_id)) {
							startList.remove(session_id);
						}
					}
					String[] deleted = new String[startList.size()];
					deleted = startList.toArray(deleted);
		

					// IF IT IS ADD MOVIE ON SAVE:
					if(movie_id.equals("")) {
						movie_id = adminbe.addMovieInfo(info);
						
						adminbe.addOrUpdateSessionInfo(tableData, deleted, movie_id);	
						frame.dispose();
						MovieTable table = new MovieTable(adminbe, cbe, position);
					} 
					// IF IT IS UPDATE MOVIE ON SAVE:
					
					else {
						adminbe.updateMovieInfo(info, movie_id);	
						adminbe.addOrUpdateSessionInfo(tableData, deleted, movie_id);	
						frame.dispose();
						MovieTable table = new MovieTable(adminbe, cbe, position);
					}


				}

        	}
        });
        btnNewButton_2.setBounds(671, 515, 130, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MovieTable table = new MovieTable(adminbe, cbe, position);
        	}
        });
        btnNewButton_3.setBounds(684, 37, 117, 29);
		frame.getContentPane().add(btnNewButton_3);
		
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
