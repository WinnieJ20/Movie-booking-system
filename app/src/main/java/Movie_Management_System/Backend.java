package Movie_Management_System;

import java.sql.*;
import java.util.*;

public class Backend {
    protected Connection conn;

    public Backend(Connection conn){
        this.conn = conn;
    }

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

    public void executeSQLUpdate(String sql_string) {
        try {
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(sql_string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    Return all the distinct cinema location
     */
    public String[] get_all_locations(){
        ArrayList<String> locations = new ArrayList<>();
        String query = "SELECT cinema_name FROM Cinema";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            if (!rs.isBeforeFirst() ) {
                return (String[]) locations.toArray(new String[locations.size()]);
            }else {
                while(rs.next()){
                    String value = rs.getString(1);
                    locations.add(value);
                }
                return (String[]) locations.toArray(new String[locations.size()]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return (String[]) locations.toArray(new String[locations.size()]);
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
}
