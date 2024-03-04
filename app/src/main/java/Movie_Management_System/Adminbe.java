package Movie_Management_System;
import java.sql.*;
import java.util.*;

public class Adminbe extends Backend {

    public Adminbe(Connection conn) {
        super(conn);
    }

    /*
    M - manager valid account
    S - cinema staff valid account
    F - failed to log in or error in system
     */
    public String check_adminLogin(String username, String password) {
        String query = "SELECT * FROM Staff WHERE staff_id = '" + username + "' and password = '" + password + "';";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst() ) {
                return "F";
            }else {
                if (rs.getString("role").equals("Cinema Staff")){
                    return "S";
                }else if (rs.getString("role").equals("Manager")){
                    return "M";
                }else{
                    return "F";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "F";
        }
    }

    /*
    // format String[][] {movie_id, name}
    public String[][] getMovies() {
        ArrayList<String[]> bundle = new ArrayList<>();
        String query = "SELECT movie_id, name FROM Movie;";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) bundle.toArray(new String[bundle.size()][]);
            }
            while (rs.next()) {
                String[] thisRow = new String[] {rs.getString("movie_id"), rs.getString("name")};
                bundle.add(thisRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (String[][]) bundle.toArray(new String[bundle.size()][]);
    }

    // format Hashmap {movie_id = movie_id, name = name, synopsis = synopsis, classification = classification, release_date = release_date, director = director, cast = cast}
    public HashMap<String, String> getMovieInfo(String id) {
        HashMap<String, String> info = new HashMap<>();
        String query = "SELECT * FROM Movie WHERE movie_id = " + id + ";";
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

    // format String[][] {session_id, cinema_name, screen_size, time, total_seats}
    // expect the booked column not editable
    public String[][] getMovieSessionInfo(String id) {
        ArrayList<String[]> info = new ArrayList<>();
        if (id == "") {
            return (String[][]) info.toArray(new String[info.size()][]);
        }
        String query = "SELECT * FROM Session INNER JOIN Cinema USING (cinema_name) WHERE movie_id = " + id + ";";
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
*/
    // format String[][] {card_number, redeemed}
    public String[][] getGiftCardInfo() {
        ArrayList<String[]> info = new ArrayList<>();
        String query = "SELECT * FROM GiftCard";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) info.toArray(new String[info.size()][]);
            }
            while (rs.next()) {
                String[] row = new String[] {rs.getString("card_number"), rs.getString("redeemed")};
                info.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (String[][]) info.toArray(new String[info.size()][]);
    }

    public String[][] getStaffInfo() {
        ArrayList<String[]> info = new ArrayList<>();
        String query = "SELECT * FROM Staff";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) info.toArray(new String[info.size()][]);
            }
            while (rs.next()) {
                String[] row = new String[] {rs.getString(1), rs.getString(2), rs.getString(3)};
                info.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (String[][]) info.toArray(new String[info.size()][]);
    }

    public void updateStaffInfo(String[][] updated) {
        try {
            Statement stmt  = conn.createStatement();
            String delete = "DELETE FROM Staff;";
            stmt.executeUpdate(delete);
            for (String[] s: updated) {
                String insert = "INSERT INTO Staff VALUES ('" + s[0] + "', '" + s[1] + "', '" + s[2] +  "');";
                stmt.executeUpdate(insert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean removeMovie(String id) {
        String query = "DELETE FROM Movie WHERE movie_id = " + id + ";";
        try {
            Statement stmt  = conn.createStatement();
            int rs = stmt.executeUpdate(query);
            if (rs > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // format: HashMap
    public boolean updateMovieInfo(HashMap<String, String> data, String movie_id) {
        String query = "UPDATE Movie SET name = '" + data.get("name") + "', synopsis = '" + data.get("synopsis") + 
        "', classification = '" + data.get("classification") + "', release_date = '" + data.get("release_date") +  
        "', director = '" + data.get("director") + "', cast = '" + data.get("cast") + "' WHERE movie_id = " + movie_id + ";";
        try {
            Statement stmt  = conn.createStatement();
            int rs = stmt.executeUpdate(query);
            if (rs > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // format: hashmap
    public String addMovieInfo(HashMap<String, String> data) {
        String checkUniqueName = "SELECT name FROM Movie WHERE name = '" + data.get("name") + "';";
        try {
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(checkUniqueName);
            if (rs2.isBeforeFirst()) {
                return "U"; // UNIQUE movie name constraint failed
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String query = "INSERT INTO Movie (name, synopsis, classification, release_date, director, cast) VALUES ('" + 
        data.get("name") + "', '" + data.get("synopsis") + "', '" + data.get("classification") + "', '" + data.get("release_date") 
        + "', '" + data.get("director") + "', '" + data.get("cast") + "');";
        try {
            Statement stmt  = conn.createStatement();
            int rs = stmt.executeUpdate(query);
            if (rs > 0) {
                String queryId = "SELECT movie_id FROM Movie WHERE name = '" + data.get("name") + "';";
                ResultSet rs3 = stmt.executeQuery(queryId);
                String addedMoviedId = rs3.getString("movie_id");
                return addedMoviedId;
            } else {
                return "F";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "F";
    }


    // add movie, after user clicks save, call addMovieInfo to retrieve the movie id first, then call updatesessioninfo
    // format for updated: data in the present table: {session_id (if add then empty string ""), cinema_name, screen_size, time, total_seats}
    // deleted: array of deleted session_ids
    public void addOrUpdateSessionInfo(String[][] updated, String[] deleted, String movie_id) {
        try {
            Statement stmt  = conn.createStatement();
            for (String[] s: updated) {
                if (s[0] == "") {
                    String addRow  = "INSERT INTO Session (movie_id, cinema_name, screen_size, time, total_seats, booked_seats) VALUES ("
                    + movie_id + ", '" + s[1] + "', '" + s[2] + "', '" + s[3] + "', '" + s[4] + "', " + "0);";
                    stmt.executeUpdate(addRow);
                } else {
                    String updateRow = "UPDATE Session SET cinema_name = '" + s[1] + "', screen_size = '" + s[2] + "', time = '" +
                    s[3] + "', total_seats = " + s[4] + " WHERE session_id = " + s[0] + ";";
                    stmt.executeUpdate(updateRow);
                }
            }
            for (String d: deleted) {
                String deleteRow = "DELETE FROM Session WHERE session_id = " + d + ";";
                stmt.executeUpdate(deleteRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // format: String[][] {card_number, redeemed}
    public void updateGiftCardInfo(String[][] updated) {
        try {
            Statement stmt  = conn.createStatement();
            String delete = "DELETE FROM GiftCard;";
            stmt.executeUpdate(delete);
            for (String[] s: updated) {
                String insert = "INSERT INTO GiftCard VALUES ('" + s[0] + "', " + s[1] + ");";
                stmt.executeUpdate(insert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public String[][] getMovieCSVInfo() {
        ArrayList<String[]> info = new ArrayList<>();
        String query = "SELECT movie_id, name, session_id, cinema_name, screen_size, time, synopsis, classification, release_date, director, \"cast\" FROM Movie INNER JOIN Session USING (movie_id)";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) info.toArray(new String[info.size()][]);
            }
            while (rs.next()) {
                String[] row = new String[] {rs.getString(1), rs.getString(2), rs.getString(3),
            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
            rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)};
                info.add(row);
            }
            return (String[][]) info.toArray(new String[info.size()][]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (String[][]) info.toArray(new String[info.size()][]);
    }

    public String[][] getBookingCSVInfo() {
        ArrayList<String[]> info = new ArrayList<>();
        String query = "SELECT session_id, movie_id, name, cinema_name, time, booked_seats, total_seats - booked_seats as available_seats, COUNT(transaction_id) AS no_booking FROM" + 
        " Movie INNER JOIN Session USING (movie_id) LEFT OUTER JOIN Book USING (session_id) GROUP BY session_id;";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) info.toArray(new String[info.size()][]);
            }
            while (rs.next()) {
                String[] row = new String[] {rs.getString(1), rs.getString(2), rs.getString(3),
            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
            rs.getString(8)};
                info.add(row);
            }
            return (String[][]) info.toArray(new String[info.size()][]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (String[][]) info.toArray(new String[info.size()][]);
    }

    public String[][] getCancelledTransactions() {
        ArrayList<String[]> info = new ArrayList<>();
        String query = "SELECT * FROM FailedTransaction";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                return (String[][]) info.toArray(new String[info.size()][]);
            }
            while (rs.next()) {
                String[] row = new String[] {rs.getString(1), rs.getString(2), rs.getString(3)};
                info.add(row);
            }
            return (String[][]) info.toArray(new String[info.size()][]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (String[][]) info.toArray(new String[info.size()][]);

    }
/*
    public void executeSQLUpdate(String sql_string) {
        try {
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(sql_string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
 */
}
