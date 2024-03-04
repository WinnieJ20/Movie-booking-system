package Movie_Management_System;

import java.sql.*;
import java.util.*;

public class Customerbe extends Backend{

    public Customerbe(Connection conn) {
        super(conn);
    }

    public boolean check_login(String username, String password){
        String query = "SELECT * FROM Customer WHERE username = '" + username + "' and password = '" + password + "';";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst() ) {
                return false;
            }else {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean checkSessionFull(String session_id) {
        String query = "SELECT total_seats, booked_seats FROM Session WHERE session_id = " + session_id + ";";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst() ) {
                return true;
            }else {
                int booked = rs.getInt("total_seats");
                int total = rs.getInt("booked_seats");
                return booked == total;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    // public String[] get_all_locations(){
    //     ArrayList<String> locations = new ArrayList<>();
    //     String query = "SELECT cinema_name FROM Cinema";
    //     try {
    //         Statement stmt  = conn.createStatement();
    //         ResultSet rs    = stmt.executeQuery(query);
    //         if (!rs.isBeforeFirst() ) {
    //             return (String[]) locations.toArray(new String[locations.size()]);
    //         }else {
    //             while(rs.next()){
    //                 String value = rs.getString(1);
    //                 locations.add(value);
    //             }
    //             return (String[]) locations.toArray(new String[locations.size()]);
    //         }
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //         return (String[]) locations.toArray(new String[locations.size()]);
    //     }
    // }

    public String getMovieIdFromName(String movie_name) {
        String query = "SELECT movie_id FROM Movie WHERE name = '"
        + movie_name + "';";
        String id = null;
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if(rs.next()) {
                id = rs.getString("movie_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String[][] filter_movie(String size, String location){
        String query = "SELECT DISTINCT classification, name FROM Movie NATURAL JOIN Session WHERE ";
        ArrayList<String[]> bundle = new ArrayList<>();
        if (size == null){
            query = query + "cinema_name = '" + location + "'";
        }else if (location == null){
            query = query + "screen_size = '" + size + "'";
        }else{
            query = "SELECT DISTINCT classification, name FROM Movie NATURAL JOIN Session WHERE cinema_name = '"
                    + location + "' and screen_size ='" + size + "'";
        }
        query = query + " AND time <= date('now', '+7 days') AND time > date('now');";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) bundle.toArray(new String[bundle.size()][]);
            }
            while (rs.next()) {
                String[] thisRow = new String[] {rs.getString("classification"), rs.getString("name")};
                bundle.add(thisRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (String[][]) bundle.toArray(new String[bundle.size()][]);
    }

    public HashMap<String, String> getMovieInfoFromName(String movie_name) {
        HashMap<String, String> info = new HashMap<>();
        String query = "SELECT * FROM Movie WHERE name = '" + movie_name + "';";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            info.put("movie_id", rs.getString("movie_id"));
            info.put("name", rs.getString("name"));
            info.put("synopsis", rs.getString("synopsis"));
            info.put("classification", rs.getString("classification"));
            info.put("release_date", rs.getString("release_date"));
            info.put("director", rs.getString("director"));
            info.put("cast", rs.getString("cast"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }
    
    public String[][] getMovieSessionInfo(String id, String size, String location) {
        ArrayList<String[]> info = new ArrayList<>();
        if (id == "") {
            return (String[][]) info.toArray(new String[info.size()][]);
        }
        String query = null;
        
        // if(size.equals("")) {
        //     System.out.println("empty size");
        // } 
        if(size == null && location == null) {
            query = "SELECT * FROM Session INNER JOIN Cinema USING (cinema_name) WHERE movie_id = " + id + " AND time <= date('now', '+7 days') AND time > date('now');";
        } else if(size != null && location == null) {
            query = "SELECT * FROM Session INNER JOIN Cinema USING (cinema_name) WHERE movie_id = " + id + " AND screen_size = '" + size + "' AND time <= date('now', '+7 days') AND time > date('now');";
        } else if(size == null && location != null) {
            query = "SELECT * FROM Session INNER JOIN Cinema USING (cinema_name) WHERE movie_id = " + id + " AND cinema_name = '" + location + "' AND time <= date('now', '+7 days') AND time > date('now');";
        } else {
            query = "SELECT * FROM Session INNER JOIN Cinema USING (cinema_name) WHERE movie_id = " + id + " AND screen_size = '" + size + "' AND cinema_name = '" + location + "' AND time <= date('now', '+7 days') AND time > date('now');";
        }
        
        // note: don't forget to check cinema <-> screen_size consistency
        // sessionid, cinema, screensize
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) info.toArray(new String[info.size()][]);
            }
            while(rs.next()) {
                String[] row = new String[] {rs.getString("session_id"), rs.getString("cinema_name"), rs.getString("screen_size"),
                rs.getString("time"), rs.getString("total_seats")};
                info.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (String[][]) info.toArray(new String[info.size()][]);
    }

    public String[][] getCustomerMovieSessionInfo(String id, String size, String location) {
        ArrayList<String[]> info = new ArrayList<>();
        if (id == "") {
            return (String[][]) info.toArray(new String[info.size()][]);
        }
        String query = null;
        
        // if(size.equals("")) {
        //     System.out.println("empty size");
        // } 
        if(size == null && location == null) {
            query = "SELECT session_id, cinema_name, screen_size, time, total_seats - booked_seats AS available_seats FROM Session INNER JOIN Cinema USING (cinema_name) WHERE movie_id = " + id + " AND time <= date('now', '+7 days') AND time > date('now');";
        } else if(size != null && location == null) {
            query = "SELECT session_id, cinema_name, screen_size, time, total_seats - booked_seats AS available_seats FROM Session INNER JOIN Cinema USING (cinema_name) WHERE movie_id = " + id + " AND screen_size = '" + size + "' AND time <= date('now', '+7 days') AND time > date('now');";
        } else if(size == null && location != null) {
            query = "SELECT session_id, cinema_name, screen_size, time, total_seats - booked_seats AS available_seats FROM Session INNER JOIN Cinema USING (cinema_name) WHERE movie_id = " + id + " AND cinema_name = '" + location + "' AND time <= date('now', '+7 days') AND time > date('now');";
        } else {
            query = "SELECT session_id, cinema_name, screen_size, time, total_seats - booked_seats AS available_seats FROM Session INNER JOIN Cinema USING (cinema_name) WHERE movie_id = " + id + " AND screen_size = '" + size + "' AND cinema_name = '" + location + "' AND time <= date('now', '+7 days') AND time > date('now');";
        }
        
        // note: don't forget to check cinema <-> screen_size consistency
        // sessionid, cinema, screensize
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) info.toArray(new String[info.size()][]);
            }
            while(rs.next()) {
                String[] row = new String[] {rs.getString("session_id"), rs.getString("cinema_name"), rs.getString("screen_size"),
                rs.getString("time"), rs.getString("available_seats")};
                info.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (String[][]) info.toArray(new String[info.size()][]);
    }

    // public String[][] getMovieInfoFromName(String movie_name) {
    //     ArrayList<String[]> bundle = new ArrayList<>();
    //     String query = "SELECT * FROM Movie WHERE name = '" + movie_name + "';";
    //     try {
    //         Statement stmt  = conn.createStatement();
    //         ResultSet rs    = stmt.executeQuery(query);
    //         if (!rs.isBeforeFirst()) {
    //             return (String[][]) bundle.toArray(new String[bundle.size()][]);
    //         }
    //         while (rs.next()) {
    //             String[] thisRow = new String[] {rs.getString("name"), rs.getString("synopsis"), rs.getString("classification"),
    //             rs.getString("release_date"), rs.getString("director"), rs.getString("cast")};
    //             bundle.add(thisRow);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     // System.out.println((String[][]) bundle.toArray(new String[bundle.size()][]));
    //     return (String[][]) bundle.toArray(new String[bundle.size()][]);
    // }


    public String[][] getMovies() {
        ArrayList<String[]> bundle = new ArrayList<>();
        String query = "select distinct m.name, m.classification " + 
        "from Movie m join Session s using (movie_id) " + 
        "where time <= date('now', '+7 days') AND time > date('now');";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) bundle.toArray(new String[bundle.size()][]);
            }
            while (rs.next()) {
                String[] thisRow = new String[] {rs.getString("classification"), rs.getString("name")};
                bundle.add(thisRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (String[][]) bundle.toArray(new String[bundle.size()][]);
    }

    public boolean checkFurtherFull(String session_id, int book) {
        String query = "SELECT total_seats, booked_seats FROM Session WHERE session_id = " + session_id + ";";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return true;
            }
            int total = rs.getInt("total_seats");
            int booked = rs.getInt("booked_seats");
            return book + booked > total;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }

    public boolean reserve_seats(String session_id, int seats) {
        String query = "UPDATE Session SET booked_seats = booked_seats + " + seats + " WHERE session_id = " + session_id + ";";
        try {
            Statement stmt  = conn.createStatement();
            int res   = stmt.executeUpdate(query);
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancel_reservation(String session_id, int seats) {
        String query = "UPDATE Session SET booked_seats = booked_seats - " + seats + " WHERE session_id = " + session_id + ";";
        try {
            Statement stmt  = conn.createStatement();
            int res   = stmt.executeUpdate(query);
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int checkAvailableSeat(String session_id) {
        String query = "SELECT total_seats - booked_seats AS available_seats FROM Session WHERE session_id = " + session_id + ";";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return 0;
            }
            int avail = rs.getInt("available_seats");
            return avail;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> getUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        String query = "SELECT username FROM Customer;";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return usernames;
            }
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
            return usernames;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    public void InsertRegisterInfo(String username, String password) {
        String query = "INSERT INTO Customer(username, password) VALUES ('" + username + "', '" + password + "');";
        try {
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean log_failed_transaction(String timestamp, String username, String reason) {
        String query = "INSERT INTO FailedTransaction(timestamp, username, reason) VALUES ('" + timestamp + "', '" + username + "', '" + reason + "')";
        try {
            Statement stmt  = conn.createStatement();
            int res = stmt.executeUpdate(query);
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // needs join here
    public String[] return_saved_card_info(String username) {
        String[] result = new String[2];
        String query = "SELECT name, card_number FROM Customer INNER JOIN Card USING (card_number) WHERE username = '" + username + "';";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return result;
            }
            String card_holder_name = rs.getString("name");
            if (rs.wasNull()) {
                card_holder_name = "";
            }
            String card_number = rs.getString("card_number");
            if (rs.wasNull()) {
                card_number = "";
            }
            result[0] = card_holder_name;
            result[1] = card_number;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
    }

    // return n means doesn't exist such a card
    // return r means the card has been redeemed
    public String validate_gift_card(String card_number) {
        String query = "SELECT redeemed FROM GiftCard WHERE card_number = '" + card_number + "';";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return "n";
            }
            int redeemed = rs.getInt("redeemed");
            if (redeemed == 1) {
                return "r";
            }
            return "p";
        } catch (SQLException e) {
            e.printStackTrace();
            return "e";
        }
    }

    public void redeem_gift_card(String card_number) {
        String query = "UPDATE GiftCard SET redeemed = 1 WHERE card_number = '" + card_number + "';";
        try {
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void confirm_book(int num_of_people, String username, String session_id) {
        String query = "INSERT INTO Book(number_people, username, session_id) VALUES (" + num_of_people + ",'" + username + "', '" + session_id + "');";
        try {
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validate_credit_card(String card_holder_name, String card_number) {
        String query = "SELECT * FROM Card WHERE card_number = '" + card_number + "' AND name = '" + card_holder_name + "';";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return false;
            } 
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int get_transaction_id(String username, String session_id) {
        String query = "SELECT MAX(transaction_id) AS tid FROM BOOK WHERE username = '" + username + "' AND session_id = " + session_id + ";";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return -1;
            }
            return rs.getInt("tid");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void save_card(String username, String card_number) {
        String query = "UPDATE Customer SET card_number = '" + card_number + "' WHERE username = '" + username + "';";
        try {
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unsave_card(String username, String card_number) {
        String query = "SELECT card_number FROM Customer WHERE username = '" + username + "';";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.getString("card_number").equals(card_number)) {
                String update = "UPDATE Customer SET card_number = NULL WHERE username = '" + username + "';";
                stmt.executeUpdate(update);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
