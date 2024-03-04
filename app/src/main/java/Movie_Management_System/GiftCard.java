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
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.IntStream;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class GiftCard {

    private JFrame mainFrame;
    private Adminbe admin;
    private JTable table;
    private JLabel lblNewLabel;
    private JButton btnAddCard;
    private JButton btnRemoveCard;
    private JButton btnSave;
    private String position;
    private Customerbe cbe;

    /**
     * Create the application.
     */
    public GiftCard(Adminbe admin, Customerbe cbe, String role) {
        this.position = role;
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
        mainFrame.setTitle("Manage Gift Cards");
        mainFrame.setSize(1000, 750);
        mainFrame.setBounds(100, 100, 450, 300);
        mainFrame.getContentPane().setLayout(null);


        //Display data in table
        String[][] body = admin.getGiftCardInfo();

        //Column names
        String[] headings = {"Card Number", "Redeemed"};

        DefaultTableModel model = new DefaultTableModel();


        model.addColumn(headings[0]);
        model.addColumn(headings[1]);

        //Initializing the table
        for (String[] row: admin.getGiftCardInfo()) {
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

        lblNewLabel = new JLabel("Manage Gift Cards");
        lblNewLabel.setBounds(18, 37, 120, 16);
        mainFrame.getContentPane().add(lblNewLabel);

        btnAddCard = new JButton("Add Card");
        //Adds new row
        btnAddCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addRow(new String[]{"", "", ""});
            }
        });
        btnAddCard.setBounds(327, 95, 117, 29);
        mainFrame.getContentPane().add(btnAddCard);


        btnRemoveCard = new JButton("Remove Card");
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
                HashSet<String> ids = new HashSet<>();
                int row = model.getRowCount(), col = model.getColumnCount();
                for (int i = 0 ; i < row ; i++) {
                    //SANITY CHECK FOR EMPTY ROW VALUES
                    String val = (String) table.getValueAt(i, 0);
                    String redeemed = (String) table.getValueAt(i, 1);
                    if (ids.contains(val)) {
                        ErrorMessage em = new ErrorMessage("No duplicate card allowed!");
                        em.getDialog().setVisible(true);
                        return;
                    } else {
                        ids.add(val);
                    }
                    //Checks if row value is empty
                    if (val.equals("")) {
                        ErrorMessage em = new ErrorMessage("Row values cannot be empty!");
                        em.getDialog().setVisible(true);
                        return;
                    }
    
                    else {
                        //Checks if gift card length is 16 digits long
                        if (val.length() != 16) {
                            ErrorMessage em = new ErrorMessage("Invalid gift card!");
                            em.getDialog().setVisible(true);
                            return;
                        }
    
                        else {
                            //Checks the GC suffix is present
                            if (!val.substring(14, 16).equals("GC")) {
                                ErrorMessage em = new ErrorMessage("Missing 'GC' validation!");
                                em.getDialog().setVisible(true);
                                return;
                            }
    
                            //Checks that the first 14 digits are integers
                            try {
                                new BigInteger(val.substring(0,14));
    
                            } catch (NumberFormatException ex) {
                                ErrorMessage em = new ErrorMessage("Numbers only accepted!");
                                em.getDialog().setVisible(true);
                                return;
                            }
                            //Checks that the 'Redeemed' column has valid input
                            if (!redeemed.equals("0") && !redeemed.equals("1")) {
                                ErrorMessage em = new ErrorMessage("Redeemed: only 0 or 1!");
                                em.getDialog().setVisible(true);
                                return;
                            }
                        }
                    }
                }
                admin.updateGiftCardInfo(getTableData(model));
                mainFrame.dispose();
                MovieTable m = new MovieTable(admin, cbe, position);
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
