package Movie_Management_System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.stream.IntStream;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MovieTable {

    private JFrame frame;
    private JTable table;
    private Adminbe admin;
    private Customerbe cbe;
    //private HomePage hp;
    private String position;

    /**
     * Create the application.
     */
    public MovieTable(Adminbe admin, Customerbe cbe, String role) {
        this.position = role;
        this.admin = admin;
        this.cbe = cbe;
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();

        //frame Title
        frame.setTitle("Page 1 Mockup");
        frame.setSize(1000, 600);
        //frame.setBounds(100, 100, 450, 300);
        frame.getContentPane().setLayout(null);

        //Display data in table
        String[][] body = admin.getMovies();

        //Column names
        String[] headings = {"Movie ID", "Name"};
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn(headings[0]);
        model.addColumn(headings[1]);

        //Initializing the table
        for (String[] row: body) {
            model.addRow(row);
        }
        /*model.addRow(body[0]);
        model.addRow(body[1]);
        model.addRow(body[2]);*/

        table = new JTable(model);
        table.setBounds(30, 40, 200, 300);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);


        //Remove user edit access
        table.setDefaultEditor(Object.class, null);


        JScrollPane sp = new JScrollPane();
        sp.setViewportView(table);
        sp.setSize(700, 377);
        sp.setLocation(18, 106);
        frame.getContentPane().add(sp, BorderLayout.CENTER);
        
        String accountName = "";
        if (position == "M") {
            accountName = "Manager Account";
        } else {
            accountName = "Staff Account";
        }
        JLabel lblNewLabel = new JLabel(accountName);
        lblNewLabel.setBounds(18, 16, 149, 20);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblManageMoviesselect = new JLabel("Manage Movies (Select Row)");
        lblManageMoviesselect.setBounds(18, 60, 175, 16);
        frame.getContentPane().add(lblManageMoviesselect);

        JButton btnNewButton = new JButton("Log Out");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //links to Winnie's interface
                frame.dispose();
                frame.setVisible(false);
                HomePage hp = new HomePage(admin, cbe);
                hp.guest_mode();;
            }
        });
        btnNewButton.setBounds(824, 16, 117, 29);
        frame.getContentPane().add(btnNewButton);

        //links to Belinda's interface
        JButton btnAddRow = new JButton("Add Movie");
        btnAddRow.setBounds(767, 102, 117, 29);
        btnAddRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SessionRecords s = new SessionRecords(admin, cbe, "", position);
                return;
            }
        });
        frame.getContentPane().add(btnAddRow);

        //links to Belinda's interface
        JButton btnUpdateMovie = new JButton("Update Movie");
        btnUpdateMovie.setBounds(767, 169, 117, 29);
        btnUpdateMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selected = table.getSelectedRow();
                if (selected == -1) {
                    return;
                }
                String movie_id = (String) table.getValueAt(selected, 0);
                frame.dispose();
                SessionRecords s = new SessionRecords(admin, cbe, movie_id, position);
            }
        });
        frame.getContentPane().add(btnUpdateMovie);

        JButton btnRemoveMovie = new JButton("Remove Movie");
        btnRemoveMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    return;
                }
                //ConfirmationDialog popUp = new ConfirmationDialog("Are you sure you want to remove this movie?");
                //if (popUp.getResponse().equals("Y")) {
                    String id = model.getValueAt(table.getSelectedRow(), 0).toString();
                    IntStream.of(table.getSelectedRows()) .boxed()
                            .sorted(Collections.reverseOrder()) .map(table::convertRowIndexToModel)
                            .forEach(model::removeRow);
                    admin.removeMovie(id);
                //}
                //else if (popUp.getResponse().equals("N")) {
                    //popUp.close();
                //}
            }
        });
        btnRemoveMovie.setBounds(767, 238, 117, 29);
        frame.getContentPane().add(btnRemoveMovie);

        JButton gcButton = new JButton("Manage Gift Cards");
        gcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GiftCard gc = new GiftCard(admin, cbe, position);
            }
        });
        gcButton.setBounds(358, 16, 159, 29);
        frame.getContentPane().add(gcButton);

        if (position == "M") {
            JButton btnNewButton_1 = new JButton("Manage Staff");
            btnNewButton_1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    ManageStaff m = new ManageStaff(admin, cbe);
                }
            });
            btnNewButton_1.setBounds(218, 16, 117, 29);
            frame.getContentPane().add(btnNewButton_1);

            JButton btnNewButton_4 = new JButton("Cancelled Transactions");
            btnNewButton_4.setBounds(218, 55, 175, 29);
            btnNewButton_4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    CSVPage c = new CSVPage(admin, cbe, position, "T");
                }
            });
            frame.getContentPane().add(btnNewButton_4);
        }
        
        JButton btnNewButton_2 = new JButton("Movie CSV");
        btnNewButton_2.setBounds(541, 16, 117, 29);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CSVPage c = new CSVPage(admin, cbe, position, "M");
            }
        });
        frame.getContentPane().add(btnNewButton_2);

        
        JButton btnNewButton_3 = new JButton("Booking CSV");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CSVPage c = new CSVPage(admin, cbe, position, "B");
        	}
        });
        btnNewButton_3.setBounds(681, 16, 117, 29);
        frame.getContentPane().add(btnNewButton_3);
        
        frame.setVisible(true);
        WindowListener lis = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("closing connection...");
                admin.closeConnection();
                System.exit(0);
            }
        };
    
        frame.addWindowListener(lis);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
