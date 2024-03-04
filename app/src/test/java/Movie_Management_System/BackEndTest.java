package Movie_Management_System;


import org.junit.jupiter.api.*;

import java.io.PrintWriter;
import java.sql.*;
import Movie_Management_System.App;
import java.util.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BackEndTest {
    @BeforeEach
    void populateData() {
        try {
            App app = new App();
            app.connect();
            Statement stmt = app.conn.createStatement();
            ArrayList<String> sql_statement = new ArrayList<String>();
            // for card table
            sql_statement.add("INSERT INTO Card VALUES (40691, 'Charles');");
            sql_statement.add("INSERT INTO Card VALUES (42689, 'Sergio');");
            sql_statement.add("INSERT INTO Card VALUES (60146, 'Kasey');");
            sql_statement.add("INSERT INTO Card VALUES (59141, 'Vincent');");
            sql_statement.add("INSERT INTO Card VALUES (55134, 'Ruth');");
            sql_statement.add("INSERT INTO Card VALUES (23858, 'Donald');");
            sql_statement.add("INSERT INTO Card VALUES (35717, 'Christine');");

            // for customer table
            sql_statement.add("INSERT INTO Customer VALUES ('Amy', '12345', 40691);");
            sql_statement.add("INSERT INTO Customer VALUES ('Charles Smith', '13333', 40691);");
            sql_statement.add("INSERT INTO Customer(username, password) VALUES ('Sergio Rich', '231421');");
            sql_statement.add("INSERT INTO Customer VALUES ('Kasey Chen', '2318B', 60146);");
            sql_statement.add("INSERT INTO Customer(username, password) VALUES ('Vicent Scott', 'vicix28');");
            sql_statement.add("INSERT INTO Customer(username, password) VALUES ('Ruth Rutherford', 'rth3301');");
            sql_statement.add("INSERT INTO Customer(username, password) VALUES ('Donald Trump', 'makeAmericanGreaterAgain');");
            sql_statement.add("INSERT INTO Customer(username, password) VALUES ('Christine Xu', 'cccrrrxxx');");
            
            // for cinema table
            sql_statement.add("INSERT INTO Cinema VALUES ('Burwood');");
            sql_statement.add("INSERT INTO Cinema VALUES ('Wynyard');");
            sql_statement.add("INSERT INTO Cinema VALUES ('Broadway');");

            // for movie table
            sql_statement.add("INSERT INTO Movie VALUES (2, 'Toy Story', 'A story about toys.', 'G', '1995', 'John Lasseter', 'Woody and Buzz');");
            sql_statement.add("INSERT INTO Movie VALUES (1, 'Titanic', 'Dramatic love story.', 'M', '1997', 'James Cameron', 'Jack and Rose');");
            sql_statement.add("INSERT INTO Movie VALUES (3, 'Chicken Run', 'A running chicken', 'G', '2000', 'John Lasseter', 'Chicken');");
            sql_statement.add("INSERT INTO Movie VALUES (4, 'Up', 'Balloons and sadness.', 'PG', '2009', 'Pete Docter', 'Carl');");
            sql_statement.add("INSERT INTO Movie VALUES (5, 'Coraline', 'Spooky buttons.', 'PG', '2009', 'Henry Selick', 'Coraline');");
            sql_statement.add("INSERT INTO Movie VALUES (6, 'Iron Man 3', 'Iron man exploded all of his suits.', 'M', '2013', 'Shane Black', 'Robert Downey');");
            sql_statement.add("INSERT INTO Movie VALUES (7, 'The Incredibles', 'Incredible people.', 'PG', '2004', 'Brad Bird', 'Mr Incredible');");
            sql_statement.add("INSERT INTO Movie VALUES (8, 'fifty shades of grey', 'erotic romantic drama film.', 'R18+', '2015', 'Sam Taylor-Johnson', 'Dakota Johnson and Jamie Dornan');");

            // for session table
            sql_statement.add("INSERT INTO Session VALUES (1, 2, 'Burwood', 'Gold', '" + n_days_after_today(2) + " 08:00:00', 300, 100);");
            sql_statement.add("INSERT INTO Session VALUES (2, 1, 'Wynyard', 'Silver', '" + n_days_after_today(3) + " 12:00:00', 300, 200);");
            sql_statement.add("INSERT INTO Session VALUES (3, 3, 'Broadway', 'Bronze', '" + n_days_after_today(1) + " 13:00:00', 300, 150);");
            sql_statement.add("INSERT INTO Session VALUES (4, 4, 'Burwood', 'Gold', '" + n_days_after_today(10) + " 14:00:00', 300, 100);");
            sql_statement.add("INSERT INTO Session VALUES (5, 5, 'Wynyard', 'Silver', '" + n_days_after_today(9) + " 15:00:00', 300, 200);");
            sql_statement.add("INSERT INTO Session VALUES (6, 6, 'Broadway', 'Bronze', '" + n_days_after_today(22) + " 09:00:00', 300, 300);");
            sql_statement.add("INSERT INTO Session VALUES (7, 7, 'Burwood', 'Gold', '" + n_days_after_today(2) + " 20:00:00', 300, 100);");
            sql_statement.add("INSERT INTO Session VALUES (8, 2, 'Wynyard', 'Silver', '" + n_days_after_today(1) + " 21:00:00', 300, 50);");
            sql_statement.add("INSERT INTO Session VALUES (9, 1, 'Burwood', 'Bronze', '" + n_days_after_today(3) + " 22:00:00', 300, 25);");
            sql_statement.add("INSERT INTO Session VALUES (10, 3, 'Broadway', 'Gold', '" + n_days_after_today(3) + " 22:00:00', 300, 100);");
            sql_statement.add("INSERT INTO Session VALUES (11, 3, 'Broadway', 'Silver', '" + n_days_after_today(3) + " 22:00:00', 300, 75);");
            sql_statement.add("INSERT INTO Session VALUES (12, 6, 'Burwood', 'Bronze', '" + n_days_after_today(3) + " 22:00:00', 300, 150);");
            sql_statement.add("INSERT INTO Session VALUES (13, 6, 'Wynyard', 'Gold', '" + n_days_after_today(3) + " 22:00:00', 300, 100);");
            sql_statement.add("INSERT INTO Session VALUES (14, 6, 'Wynyard', 'Silver', '" + n_days_after_today(3) + " 22:00:00', 300, 250);");
            sql_statement.add("INSERT INTO Session VALUES (15, 1, 'Wynyard', 'Bronze', '" + n_days_after_today(3) + " 15:00:00', 300, 100);");

            // for Staff table
            sql_statement.add("INSERT INTO Staff VALUES (1, 'Manager', 'Helen');");
            sql_statement.add("INSERT INTO Staff VALUES (2, 'Manager', 'David');");
            sql_statement.add("INSERT INTO Staff VALUES (3, 'Cinema Staff', 'Winnie');");
            sql_statement.add("INSERT INTO Staff VALUES (4, 'Cinema Staff', 'Belinda');");
            sql_statement.add("INSERT INTO Staff VALUES (5, 'Cinema Staff', 'Yash');");

            // for Giftcard table
            sql_statement.add("INSERT INTO GiftCard VALUES ('12345678901234GC', 0);");
            sql_statement.add("INSERT INTO GiftCard VALUES ('12345678901235GC', 0);");
            sql_statement.add("INSERT INTO GiftCard VALUES ('12345678901236GC', 0);");
            sql_statement.add("INSERT INTO GiftCard VALUES ('12345678901237GC', 0);");
            sql_statement.add("INSERT INTO GiftCard VALUES ('12345678901238GC', 0);");

            for (int i = 0; i < sql_statement.size(); i++){
                stmt.executeUpdate(sql_statement.get(i));
            }

            app.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String n_days_after_today(int n) {
        java.util.Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        int current_month = cal.get(Calendar.MONTH) + 1;
        int current_day = cal.get(Calendar.DAY_OF_MONTH);
        int current_year = cal.get(Calendar.YEAR);
        String fdate = String.format("%02d-%02d", current_month, current_day);
        String format = current_year + "-" + fdate;
        return format;
    }

    @AfterEach
    void clearData(){
        try {
            App app = new App();
            app.connect();
            Statement stmt = app.conn.createStatement();
            String failed_sql = "DELETE FROM FailedTransaction;";
            String book_sql = "DELETE FROM Book;";
            String card_sql = "DELETE FROM Card;";
            String user_sql = "DELETE FROM Customer;";
            String session_sql = "DELETE FROM Session;";
            String cinema_sql = "DELETE FROM Cinema;";
            String movie_sql = "DELETE FROM Movie;";
            String staff_sql = "DELETE FROM Staff;";
            String giftcard_sql = "DELETE FROM GiftCard;";
            String reset_sequence_book = "UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='Book';";
            stmt.executeUpdate(failed_sql);
            stmt.executeUpdate(book_sql);
            stmt.executeUpdate(user_sql);
            stmt.executeUpdate(card_sql);
            stmt.executeUpdate(session_sql);
            stmt.executeUpdate(cinema_sql);
            stmt.executeUpdate(movie_sql);
            stmt.executeUpdate(staff_sql);
            stmt.executeUpdate(giftcard_sql);
            stmt.executeUpdate(reset_sequence_book);
            
            app.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    @Test
    public void testGetEmptyMovies() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        try{
            Statement stmt = app.conn.createStatement();
            String movie_sql = "DELETE FROM Movie;";
            String session_sql = "DELETE FROM Session;";

            stmt.executeUpdate(movie_sql);
            stmt.executeUpdate(session_sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(0, abe.getMovies().length);
        abe.closeConnection(); 
    }

    @Test
    public void testGetNoGiftCards() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertEquals(0, abe.getGiftCardInfo().length);
        abe.closeConnection();
    }

    @Test
    public void testNotExistAdminLogin() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertEquals("F", abe.check_adminLogin("NOTEXISTS", "NOTEXISTS"));
        abe.closeConnection();
    }

    @Test
    public void testGetNotExistMovieSessionInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertEquals(0, abe.getMovieSessionInfo("1").length);
        abe.closeConnection();
    }*/

    @Test
    public void testCheckValidLogin(){
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        //cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        boolean ret = cbe.check_login("Amy", "12345");
        assertTrue(ret, "Check valid login failed.");
        cbe.closeConnection();
    }

    @Test
    public void testCheckInvalidLogin(){
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        boolean ret = cbe.check_login("Something", "random");
        assertFalse(ret, "Check invalid login failed.");
        cbe.closeConnection();
    }

    @Test
    public void testGetAllLocation(){
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[] location = cbe.get_all_locations();
        assertEquals(3, location.length);
        cbe.closeConnection();
    }

    @Test
    public void testGetAllLocationEmpty(){
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        try{
            Statement stmt = app.conn.createStatement();
            String cinema_sql = "DELETE FROM Cinema;";
            String session_sql = "DELETE FROM Session;";
            stmt.executeUpdate(session_sql);
            stmt.executeUpdate(cinema_sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        String[] location = cbe.get_all_locations();
        assertEquals(0, location.length);
        cbe.closeConnection();
    }

    @Test
    public void testFilterMovie(){
        //testing for no valid time session for movie within 7 days
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        cbe.filter_movie("Gold", "Burwood");
        cbe.filter_movie(null, "Burwood");
        cbe.filter_movie("Gold", null);
        cbe.filter_movie(null, null);
        // assertEquals(3, cbe.filter_movie("Gold", "Burwood").length);
        // assertEquals(5, cbe.filter_movie(null, "Burwood").length);
        // assertEquals(5, cbe.filter_movie("Gold", null).length);
        cbe.closeConnection();
    }


    @Test
    public void testGetEmptyUsernames() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        try{
            Statement stmt = app.conn.createStatement();
            String cust_sql = "DELETE FROM Customer;";
            stmt.executeUpdate(cust_sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(0, cbe.getUsernames().size());
        cbe.closeConnection(); 
    }

    @Test
    public void testGetUsernames() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertEquals(cbe.getUsernames().get(0), "Amy");
        cbe.closeConnection(); 
    }

    @Test
    public void testGetMovies() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertNotNull(cbe.getMovies());
        cbe.closeConnection(); 
    }

    @Test
    public void testGetMovieInfoFromName() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        HashMap<String, String> info = cbe.getMovieInfoFromName("Toy Story");
        assertEquals(info.get("classification"), "G");
        assertEquals(info.get("movie_id"), "2");
        assertEquals(info.get("release_date"), "1995");
        assertEquals(info.get("director"), "John Lasseter");
        assertEquals(info.get("cast"), "Woody and Buzz");
        cbe.closeConnection(); 
    }

    @Test
    public void testGetMovieSessionInfo() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] info = cbe.getMovieSessionInfo("2", "Gold", "Burwood");
        assertNotNull(info);
        cbe.closeConnection(); 
    }

    @Test
    public void testGetMovieSessionInfoMoreThanOne() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] info = cbe.getMovieSessionInfo("2", null, null);
        assertNotNull(info);
        cbe.closeConnection(); 
    }

    @Test
    public void testGetMovieSessionInfoJustLocation() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] info = cbe.getMovieSessionInfo("2", null, "Burwood");
        assertNotNull(info);
        cbe.closeConnection(); 
    }

    @Test
    public void testGetMovieSessionInfoJustSize() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] info = cbe.getMovieSessionInfo("2", "Gold", null);
        assertNotNull(info);
        cbe.closeConnection(); 
    }

    @Test
    public void testGetMovieSessionInfoNoId() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] info = cbe.getMovieSessionInfo("", null, null);
        assertNotNull(info);
        assertEquals(info.length, 0);
        cbe.closeConnection(); 
    }


    @Test 
    public void testRemoveMovieIncorrectId() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertFalse(abe.removeMovie("40"));
        abe.closeConnection(); 
    }

    @Test 
    public void testRemoveMovieWrongId() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertFalse(abe.removeMovie("4ab"));
        abe.closeConnection(); 
    }

    @Test 
    public void testRemoveMovie() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertTrue(abe.removeMovie("2"));
        abe.closeConnection(); 
    }

    @Test
    public void testUpdateMovieInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        Customerbe cbe = new Customerbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        HashMap<String, String> info = cbe.getMovieInfoFromName("Toy Story");
        assertTrue(abe.updateMovieInfo(info, "2"));
        abe.closeConnection(); 
        cbe.closeConnection(); 
    }

    @Test
    public void testUpdateInvalidMovieInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        Customerbe cbe = new Customerbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        HashMap<String, String> info = cbe.getMovieInfoFromName("Toy Story");
        assertFalse(abe.updateMovieInfo(info, "21whatmovie?"));
        abe.closeConnection(); 
        cbe.closeConnection(); 
    }

    @Test
    public void testGetMovieInfo() {
        App app = new App();
        app.connect();
        Backend be = new Backend(app.conn);
        Customerbe cbe = new Customerbe(app.conn);
        be.executeSQLUpdate("PRAGMA foreign_keys=ON");
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        HashMap<String, String> info = be.getMovieInfo("2");
        assertEquals(info.get("classification"), "G");
        assertEquals(info.get("movie_id"), "2");
        assertEquals(info.get("release_date"), "1995");
        assertEquals(info.get("director"), "John Lasseter");
        assertEquals(info.get("cast"), "Woody and Buzz");
        assertEquals(info.get("name"), "Toy Story");
        be.closeConnection(); 
        cbe.closeConnection(); 
    }
    @Test
    public void testGetMovieSessionInfoBE(){
        App app = new App();
        app.connect();
        Backend be = new Backend(app.conn);
        be.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] movieSession = be.getMovieSessionInfo("2");
        assertEquals(2, movieSession.length);
        be.closeConnection();
    }

    @Test
    public void testGetMovieBE(){
        App app = new App();
        app.connect();
        Backend be = new Backend(app.conn);
        be.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] movies = be.getMovies();
        assertEquals(8, movies.length);
        be.closeConnection();
    }

    @Test
    public void testAddMovieInfoBE() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        HashMap<String, String> movie = new HashMap<>();
        movie.put("name", "Black Widow");
        movie.put("synopsis", "Superspy stabs bad people");
        movie.put("classification", "PG");
        movie.put("release_date", "2021");
        movie.put("director", "Cate Shortland");
        movie.put("cast", "Scarlett Johansson");
        assertNotNull(abe.addMovieInfo(movie));
        try {
            Integer.parseInt(abe.addMovieInfo(movie));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        abe.closeConnection();
    }

    @Test
    public void testDuplicateAddMovieInfoBE() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        HashMap<String, String> movie = new HashMap<>();
        movie.put("name", "Toy Story");
        movie.put("synopsis", "Toys have lives");
        movie.put("classification", "G");
        movie.put("release_date", "1995");
        movie.put("director", "John Lasseter");
        movie.put("cast", "Ken and Barbie");
        assertEquals("U", abe.addMovieInfo(movie));
        abe.closeConnection();
    }

    /**@Test
    public void testInvalidAddMovieInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");




    }**/

    @Test
    public void testAddSessionBE() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] newSession = new String[1][6];
        newSession[0][0] = "";
        newSession[0][1] = "Burwood";
        newSession[0][2] = "Gold";
        newSession[0][3] = "22:00:00";
        newSession[0][4] = "300";
        newSession[0][5] = "0";
        abe.addOrUpdateSessionInfo(newSession, new String[0], "2");
        String[][] movieSession = abe.getMovieSessionInfo("2");
        assertEquals(3, movieSession.length);
        abe.closeConnection();
    }

    @Test
    public void testUpdateSessionBE() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] updated = new String[1][6];
        updated[0][0] = "3";
        updated[0][1] = "Wynyard";
        updated[0][2] = "Gold";
        updated[0][3] = "14:00:00";
        updated[0][4] = "300";
        updated[0][5] = "150";
        abe.addOrUpdateSessionInfo(updated, new String[0], "3");
        String[][] movieSession = abe.getMovieSessionInfo("3");
        assertEquals(3, movieSession.length);
        assertEquals("Wynyard", movieSession[0][1]);
        assertEquals("Gold", movieSession[0][2]);
        assertEquals("14:00:00", movieSession[0][3]);
    }

    @Test
    public void testDeleteSessionBE() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[] deleted = new String[3];
        deleted[0] = "12";
        deleted[1] = "13";
        deleted[2] = "14";
        abe.addOrUpdateSessionInfo(new String[0][0], deleted, "6");
        String[][] movieSession = abe.getMovieSessionInfo("6");
        assertEquals(1, movieSession.length);

    }


    @Test
    public void testGetGiftCardInfoBE() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] giftcard = abe.getGiftCardInfo();
        assertEquals(5, giftcard.length);
        abe.closeConnection();
    }


    @Test
    public void testUpdateGiftCardInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] updated = new String[2][2];
        updated[0][0] = "12341234765432GC";
        updated[0][1] = "0";
        updated[1][0] = "87654321234567GC";
        updated[1][1] = "1";
        abe.updateGiftCardInfo(updated);
        String[][] giftcard = abe.getGiftCardInfo();
        assertEquals(2, giftcard.length);
        abe.closeConnection();
    }


    @Test
    public void testValidAdminLogin(){
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String ret = abe.check_adminLogin("staff", "12345");
        // assertEquals("S", ret);
        ret = abe.check_adminLogin("manager", "234");
        // assertEquals("M", ret);
        abe.closeConnection();
    }

    @Test
    public void testGetMovieCSVInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] ret = abe.getMovieCSVInfo();
        assertEquals(ret.length, 15);
        abe.closeConnection();
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] ret2 = abe.getMovieCSVInfo();
        assertEquals(ret2.length, 0);
    }


    @Test
    public void testCancelReservation() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        cbe.reserve_seats("8", 5);
        assertTrue(cbe.cancel_reservation("8", 5));
        String[][] movieInfo = cbe.getCustomerMovieSessionInfo("2", "Silver", "Wynyard");
        assertEquals("250", movieInfo[0][4]);
        cbe.closeConnection();
        assertFalse(cbe.cancel_reservation("8", 5));
    }

    @Test
    public void testCheckAvailableSeat() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertEquals(50, cbe.checkAvailableSeat("14"));
        assertEquals(0, cbe.checkAvailableSeat("6"));
        cbe.closeConnection();
        assertEquals(0, cbe.checkAvailableSeat("14"));
    }

    @Test
    public void testCheckFurtherFull() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertEquals(true, cbe.checkFurtherFull("2", 200));
        assertEquals(false, cbe.checkFurtherFull("9", 200));
        cbe.closeConnection();
        assertEquals(true, cbe.checkFurtherFull("2", 50));
    }

    @Test
    public void testCheckSessionFull() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        assertTrue(cbe.checkSessionFull("6"));
        assertFalse(cbe.checkSessionFull("7"));
        cbe.closeConnection();
        assertTrue(cbe.checkSessionFull("7"));
    }

    @Test
    public void testGetCustomerMovieSessionInfo() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        String[][] rs1 = cbe.getCustomerMovieSessionInfo("6", null, "Wynyard");
        assertEquals(2, rs1.length);
        String[][] rs2 = cbe.getCustomerMovieSessionInfo("1", "Bronze", null);
        assertEquals(2, rs2.length);
        String[][] rs3 = cbe.getCustomerMovieSessionInfo("6", "Silver", null);
        assertEquals(1, rs3.length);
        String[][] rs4 = cbe.getCustomerMovieSessionInfo("6", null, null);
        assertEquals(3, rs4.length);
        String[][] rs5 = cbe.getCustomerMovieSessionInfo("1", "Bronze", "Wynyard");
        assertEquals(1, rs5.length);
        assertEquals(n_days_after_today(3) + " 15:00:00", rs5[0][3]);
        String[][] rs7 = cbe.getCustomerMovieSessionInfo("", null, null);
        assertEquals(0, rs7.length);
        cbe.closeConnection();
        String[][] rs6 = cbe.getCustomerMovieSessionInfo("1", "Bronze", "Wynyard");
        assertEquals(0, rs6.length);
    }


    @Test
    public void testGetStaffInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] ret = abe.getStaffInfo();
        assertEquals(ret.length, 5);
        abe.closeConnection();
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] ret2 = abe.getStaffInfo();
        assertEquals(ret2.length, 0);
    }

 
    @Test
    public void testUpdateStaffInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] update = new String[][] {{"Enqi", "Manager", "666666"}};
        abe.updateStaffInfo(update);
        String ret = abe.check_adminLogin("Enqi", "666666");
        assertEquals("M", ret);
        abe.closeConnection();
        String[][] update1 = new String[][] {{"Enqi1", "Manager", "666666"}};
        abe.updateStaffInfo(update1);
        String ret2 = abe.check_adminLogin("Enqi1", "666666");
        assertEquals("F", ret2);
    }

    @Test
    public void testInsertRegisterInfo() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        cbe.InsertRegisterInfo("Helen", "12345");
        ArrayList<String> ret = cbe.getUsernames();
        assertNotNull(ret);
        cbe.closeConnection();
        cbe.InsertRegisterInfo("Helen", "12345");
        ArrayList<String> ret2 = cbe.getUsernames();
        assertNotNull(ret);

    }

    @Test
    public void testGetCancelledTransactions() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        
        boolean a = cbe.log_failed_transaction("2021", "Amy", "timeout");
        
        String[][] ret = abe.getCancelledTransactions();
        assertEquals(ret.length, 1);

        cbe.closeConnection();
        boolean b = cbe.log_failed_transaction("2019", "Charles Smith", "timeout");
        String[][] ret2 = abe.getCancelledTransactions();
        assertEquals(ret2.length, 0);
 
    }
    @Test
    public void testLog_failed_transaction() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");

        boolean a = cbe.log_failed_transaction("2021", "Amy", "timeout");
        boolean b = cbe.log_failed_transaction("2019", "Charles Smith", "wrong");
        assertTrue(a);
        assertFalse(b);

        cbe.closeConnection();
 
    }




    @Test
    public void testConfirmBook() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.confirm_book(5, "Kasey Chen", "2");
        Integer ret = cbe.get_transaction_id("Kasey Chen", "2");
        assertTrue(ret != -1);
        cbe.closeConnection();
        cbe.confirm_book(2, "Kasey Chen", "3");
        App app2 = new App();
        app2.connect();
        Customerbe cbe2 = new Customerbe(app.conn);
        Integer ret2 = cbe2.get_transaction_id("Kasey Chen", "3");
        assertTrue(ret2 == -1);
        cbe2.closeConnection();
    }

    @Test
    public void testGetTransactionId() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.confirm_book(5, "Kasey Chen", "2");
        Integer ret = cbe.get_transaction_id("Kasey Chen", "2");
        cbe.confirm_book(8, "Kasey Chen", "1");
        Integer ret2 = cbe.get_transaction_id("Kasey Chen", "1");
        assertEquals(ret+1, ret2);
        cbe.closeConnection();
        Integer ret3 = cbe.get_transaction_id("Kasey Chen", "1");
        assertEquals(-1, ret3);
    }

    @Test
    public void testRedeemGiftCard() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.redeem_gift_card("12345678901234GC");
        assertEquals("r", cbe.validate_gift_card("12345678901234GC"));
        cbe.closeConnection();
        cbe.redeem_gift_card("12345678901235GC");
        app.connect();
        cbe = new Customerbe(app.conn);
        assertEquals("p", cbe.validate_gift_card("12345678901235GC"));
        cbe.closeConnection();
    }

    @Test
    public void testReserveSeats() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.reserve_seats("11", 10);
        String[][] ret = cbe.getCustomerMovieSessionInfo("3", "Silver", "Broadway");
        assertEquals("215", ret[0][4]);
        cbe.closeConnection();
        cbe.reserve_seats("11", 10);
        app.connect();
        cbe = new Customerbe(app.conn);
        String[][] ret2 = cbe.getCustomerMovieSessionInfo("3", "Silver", "Broadway");
        assertEquals("215", ret2[0][4]);
        cbe.closeConnection();
    }

    @Test
    public void testReturnSaveCardInfo() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        String[] a = cbe.return_saved_card_info("q hduqd q");
        assertNull(a[0]);
        assertNull(a[1]);
        String[] b = cbe.return_saved_card_info("Kasey Chen");
        assertEquals("Kasey", b[0]);
        assertEquals("60146", b[1]);
        cbe.closeConnection();
        String[] c = cbe.return_saved_card_info("Kasey Chen");
        assertNull(c[0]);
        assertNull(c[1]);
    }

    @Test
    public void testSaveCard() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.save_card("Vicent Scott", "59141");
        String[] a = cbe.return_saved_card_info("Vicent Scott");
        assertEquals("Vincent", a[0]);
        assertEquals("59141", a[1]);
        cbe.closeConnection();
        cbe.save_card("Ruth Rutherford", "55134");
        app.connect();
        cbe = new Customerbe(app.conn);
        String[] b = cbe.return_saved_card_info("Ruth Rutherford");
        assertNull(b[0]);
        assertNull(b[1]);
        cbe.closeConnection();
    }

    @Test
    public void testUnsaveCard() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.unsave_card("Charles Smith", "40691");
        String[] b = cbe.return_saved_card_info("Charles Smith");
        assertNull(b[0]);
        assertNull(b[1]);
        cbe.closeConnection();
        cbe.unsave_card("Amy", "40691");
        app.connect();
        cbe = new Customerbe(app.conn);
        String[] c = cbe.return_saved_card_info("Amy");
        assertEquals("40691", c[1]);
        cbe.closeConnection();
    }

    @Test
    public void testValidateCreditCard() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        assertTrue(cbe.validate_credit_card("Charles", "40691"));
        assertFalse(cbe.validate_credit_card("Charles", "40291"));
        assertFalse(cbe.validate_credit_card("Donald Trump", "12342"));
        cbe.closeConnection();
        assertFalse(cbe.validate_credit_card("Charles", "40691"));
    }


    @Test
    public void testValidateGiftCard() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        String ret1 = cbe.validate_gift_card("12345678901234GC");
        assertEquals("p", ret1);
        cbe.redeem_gift_card("12345678901234GC");
        assertEquals("r", cbe.validate_gift_card("12345678901234GC"));
        assertEquals("n", cbe.validate_gift_card("12345678967234GC"));
        cbe.closeConnection();
        assertEquals("e", cbe.validate_gift_card("12345678901234GC"));
    }
}
