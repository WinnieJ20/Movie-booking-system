package Movie_Management_System;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage {
    private Adminbe abe;
    private Customerbe cbe;
    private JFrame frame;
    private JTable table;
    private JScrollPane sp;
    private JComboBox size;
    private JComboBox location;
    private JButton register;
    private JButton login;
    private JButton staff_login;
    private boolean is_login;
    private String username;
    private JLabel sign_in;
    private JButton sign_out;

    //private DefaultTableModel model;


    public HomePage(Adminbe abe, Customerbe cbe){
        this.abe = abe;
        this.cbe = cbe;
        this.is_login = false;
        this.initialise();
    }

    public void initialise(){
        frame = new JFrame();
        frame.setTitle("Movie Ticket Booking");
        frame.setSize(1000, 750);
        //frame.setBounds(100, 100, 450, 300);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JLabel welcome = new JLabel("Welcome to Movie Ticket Booking System");
        welcome.setForeground(new Color(139, 69, 19));
        welcome.setFont(new Font("Impact", Font.PLAIN, 30));
        welcome.setBounds(233, 6, 547, 49);
        frame.getContentPane().add(welcome);
        JLabel showing = new JLabel("Movie Showing");
        showing.setFont(new Font("Herculanum", Font.PLAIN, 24));
        showing.setBounds(424, 93, 197, 18);
        frame.getContentPane().add(showing);

        String[][] body = cbe.getMovies();
        String[] headings = {"Classification", "Movie Name"};

        create_table(body, headings);


        JLabel filter = new JLabel("Filter");
        filter.setHorizontalAlignment(SwingConstants.CENTER);
        filter.setBackground(new Color(255, 255, 255));
        filter.setFont(new Font("Papyrus", Font.BOLD, 16));
        filter.setBounds(207, 128, 52, 27);
        frame.getContentPane().add(filter);

        JLabel screen_size = new JLabel("Screen Size:");
        screen_size.setBounds(282, 144, 74, 16);
        frame.getContentPane().add(screen_size);

        String[] s_size = {"Gold", "Silver", "Bronze"};
        size = new JComboBox(s_size);
        size.setBounds(368, 140, 105, 27);
        size.setSelectedIndex(-1);
        frame.getContentPane().add(size);

        JLabel cinema = new JLabel("Cinema:");
        cinema.setBounds(482, 144, 61, 16);
        frame.getContentPane().add(cinema);

        String[] locations = cbe.get_all_locations();
        location = new JComboBox(locations);
        location.setBounds(545, 140, 117, 27);
        location.setSelectedIndex(-1);
        frame.getContentPane().add(location);

        JButton apply = new JButton("Apply");
        apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected_size = (String) size.getSelectedItem();
                String selected_cinema = (String) location.getSelectedItem();
                if (selected_cinema != null & selected_size != null){
                    String[][] data = cbe.filter_movie(selected_size, selected_cinema);
                    create_table(data, headings);
                }else if (selected_cinema == null){
                    String[][] data = cbe.filter_movie(selected_size, null);
                    create_table(data, headings);
                }else if (selected_size == null){
                    String[][] data = cbe.filter_movie(null, selected_cinema);
                    create_table(data, headings);
                }

            }
        });
        apply.setBounds(694, 139, 68, 29);
        frame.getContentPane().add(apply);

        JButton view = new JButton("View");
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selected = table.getSelectedRow();
                if (selected == -1) {
                    return;
                }
                String movie_name = (String) table.getValueAt(selected, 1);

                
                frame.dispose();
                String selected_size = (String) size.getSelectedItem();
                String selected_cinema = (String) location.getSelectedItem();
                MovieRecords s = new MovieRecords(HomePage.this, cbe, movie_name, selected_size, selected_cinema);
            }
        });
        view.setBounds(866, 185, 85, 29);;
        frame.getContentPane().add(view);

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                location.setSelectedIndex(-1);
                size.setSelectedIndex(-1);
                String[][] data = cbe.getMovies();
                create_table(data, headings);
            }
        });
        reset.setBounds(764, 139, 74, 29);
        frame.getContentPane().add(reset);

        WindowListener lis = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                abe.closeConnection();
                System.exit(0);
            }
        };

        frame.addWindowListener(lis);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(sp);
//        frame.setVisible(true);
    }

    public void customer_mode(String username){
        is_login = true;
        frame.getContentPane().remove(register);
        frame.getContentPane().remove(login);
        frame.getContentPane().remove(staff_login);
        frame.getContentPane().repaint();

        sign_in = new JLabel("Hello, " + username);
        sign_in.setForeground(new Color(204, 153, 204));
        sign_in.setBackground(new Color(255, 255, 255));
        sign_in.setFont(new Font("HanziPen TC", Font.BOLD, 20));
        sign_in.setBounds(21, 49, 167, 29);
        frame.getContentPane().add(sign_in);

        sign_out = new JButton("Sign out");
        sign_out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage.this.guest_mode();
            }
        });
        sign_out.setBounds(889, 15, 105, 29);
        frame.getContentPane().add(sign_out);
        frame.getContentPane().add(sp);

        frame.setVisible(true);
    }

    public void guest_mode(){
        is_login = false;
        if (sign_in != null) {
            frame.getContentPane().remove(sign_in);
        }
        if (sign_out != null) {
            frame.getContentPane().remove(sign_out);
        }
        register = new JButton("Register");
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                RegisterPage r = new RegisterPage(cbe, HomePage.this);
            }
        });
        register.setBounds(21, 16, 85, 29);
        frame.getContentPane().add(register);

        login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerLogin cl = new CustomerLogin(cbe, HomePage.this);
                cl.initialise();
            }
        });
        login.setBounds(21, 53, 85, 29);
        frame.getContentPane().add(login);

        staff_login = new JButton("Staff Login");
        staff_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ManagerLogin ml = new ManagerLogin(abe, cbe);
            }
        });
        staff_login.setBounds(889, 15, 105, 29);
        frame.getContentPane().add(staff_login);

        frame.setVisible(true);
    }

    public void create_table(String[][] body, String[] headings){
        if (table != null){
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.setColumnCount(0);
            model.setRowCount(0);

            model.addColumn(headings[0]);
            model.addColumn(headings[1]);

            for (String[] row : body) {
                model.addRow(row);
            }
        }else{
            DefaultTableModel model = new DefaultTableModel(body, headings){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };
            table = new JTable(model);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setShowGrid(true);
            table.setGridColor(Color.BLACK);

            sp = new JScrollPane(table);
            sp.setSize(631, 405);
            sp.setLocation(207, 167);
            table.setFillsViewportHeight(true);
            frame.getContentPane().add(sp);
        }
    }

    public Customerbe getCusomerBe() {
        return cbe;
    }

    public void set_login(boolean changed){
        this.is_login = changed;
    }

    public boolean isIs_login(){
        return this.is_login;
    }

    public void set_username(String username){
        this.username = username;
    }

    public String get_username(){
        return this.username;
    }



}
