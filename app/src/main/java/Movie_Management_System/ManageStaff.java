package Movie_Management_System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.IntStream;
import java.awt.event.ActionEvent;
import java.awt.event.*;
public class ManageStaff {
    private JFrame mainFrame;
    private Adminbe admin;
    private JTable table;
    private JLabel lblNewLabel;
    private JButton btnAddCard;
    private JButton btnRemoveCard;
    private JButton btnSave;
    private Customerbe cbe;

    /**
     * Create the application.
     */
    public ManageStaff(Adminbe admin, Customerbe cbe) {
        this.cbe = cbe;
        this.admin = admin;
        initialize();
    }

    public boolean returnHome() {
        return true;
    }


    /**
     * Initialize the contents of the frame.
     *
     */

    private String[][] getTableData(DefaultTableModel table) {
        int row = table.getRowCount(), col = table.getColumnCount();
        String[][] data = new String[row][col];
        for (int i = 0 ; i < row ; i++) {
            for (int j = 0 ; j < col ; j++) {
                //SANITY CHECK FOR EMPTY ROW VALUES
                data[i][j] = (String) table.getValueAt(i,j);
            }
        }
        return data;
    }



    private void initialize() {
        mainFrame = new JFrame();


        //Frame Title
        mainFrame.setTitle("Manage Staff");
        mainFrame.setSize(1000, 750);
        mainFrame.setBounds(100, 100, 450, 300);
        mainFrame.getContentPane().setLayout(null);


        //Display data in table
        String[][] body = admin.getStaffInfo();

        //Column names
        String[] headings = {"Staff id", "Role", "Password"};

        DefaultTableModel model = new DefaultTableModel();


        model.addColumn(headings[0]);
        model.addColumn(headings[1]);
        model.addColumn(headings[2]);

        //Initializing the table
        for (String[] row: body) {
            model.addRow(row);
        }

        table = new JTable(model);
        table.setBounds(30, 40, 200, 300);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);


        JScrollPane sp = new JScrollPane();
        sp.setViewportView(table);
        sp.setSize(294, 108);
        sp.setLocation(18, 99);
        mainFrame.getContentPane().add(sp, BorderLayout.CENTER);

        lblNewLabel = new JLabel("Manage Staff");
        lblNewLabel.setBounds(18, 37, 120, 16);
        mainFrame.getContentPane().add(lblNewLabel);

        btnAddCard = new JButton("Add Staff");
        //Adds new row
        btnAddCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addRow(new String[]{"", "", ""});
            }
        });
        btnAddCard.setBounds(327, 95, 117, 29);
        mainFrame.getContentPane().add(btnAddCard);


        btnRemoveCard = new JButton("Remove Staff");
        //Removes row
        btnRemoveCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    return;
                }
                //ConfirmationDialog popUp = new ConfirmationDialog("Are you sure you want to remove this card?");
                //if (popUp.getResponse().equals("Y")) {
                    IntStream.of(table.getSelectedRows()) .boxed()
                            .sorted(Collections.reverseOrder()) .map(table::convertRowIndexToModel)
                            .forEach(model::removeRow);
                //}
                //else if (popUp.getResponse().equals("N")) {
                    //popUp.close();
                //}

            }
        });
        btnRemoveCard.setBounds(327, 136, 117, 29);
        mainFrame.getContentPane().add(btnRemoveCard);

        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String[][] data = getTableData(model);
                    HashSet<String> check_dup = new HashSet<>();
                    boolean error = false;
                    for (String[] row: data) {
                        if (check_dup.contains(row[0])) {
                            error = true;
                            ErrorMessage em = new ErrorMessage("Sorry, duplicate Staff Ids not allowed.", true);
                            em.getDialog().setVisible(true);
                            break;
                        } else {
                            check_dup.add(row[0]);
                        }
                    }
                    if (!error) {
                        for (String[] row: data) {
                            if (!row[1].equals("Cinema Staff") && !row[1].equals("Manager")) {
                                error = true;
                                ErrorMessage em = new ErrorMessage("Role can only be Cinema Staff or Manager.", true);
                                em.getDialog().setVisible(true);
                                break;
                            }
                        }
                        if (!error) {
                            admin.updateStaffInfo(data);
                            mainFrame.dispose();
                            MovieTable m = new MovieTable(admin, cbe, "M");
                        }
                    }  
            }
        });
        btnSave.setBounds(327, 177, 117, 29);
        mainFrame.getContentPane().add(btnSave);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        WindowListener lis = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("closing connection...");
                admin.closeConnection();
                System.exit(0);
            }
        };
    
        mainFrame.addWindowListener(lis);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
