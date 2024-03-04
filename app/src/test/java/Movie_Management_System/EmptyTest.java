package Movie_Management_System;
import org.junit.jupiter.api.*;

import java.io.PrintWriter;
import java.sql.*;
import Movie_Management_System.App;
import java.util.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EmptyTest {

    @Test
    public void testEmptyGetMovieCSVInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] ret = abe.getMovieCSVInfo();
        assertEquals(ret.length, 0);
        abe.closeConnection();
    }

    @Test
    public void testNotExistCheckAvailableSeat() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertEquals(0, cbe.checkAvailableSeat("233"));
        cbe.closeConnection();
    }

    @Test
    public void testNotExistCheckFurtherFull() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        assertEquals(true, cbe.checkFurtherFull("1000", 200));
        cbe.closeConnection();
    }

    @Test
    public void testNotExistCheckSessionFull() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        assertTrue(cbe.checkSessionFull("7"));
        cbe.closeConnection();
    }

    @Test 
    public void testEmptyCustomerMovieSessionInfo() {
        App app = new App();
        app.connect();
        Customerbe cbe = new Customerbe(app.conn);
        String[][] rs5 = cbe.getCustomerMovieSessionInfo("1", "Bronze", "Wynyard");
        assertEquals(0, rs5.length);
        cbe.closeConnection();
    }

    @Test
    public void testEmptyGetStaffInfo() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] ret = abe.getStaffInfo();
        assertEquals(ret.length, 0);
        abe.closeConnection();
    }

    @Test
    public void testEmptyGetCancelledTransactions() {
        App app = new App();
        app.connect();
        Adminbe abe = new Adminbe(app.conn);
        Customerbe cbe = new Customerbe(app.conn);
        cbe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        abe.executeSQLUpdate("PRAGMA foreign_keys=ON");
        String[][] ret = abe.getCancelledTransactions();
        assertEquals(ret.length, 0);
        cbe.closeConnection();
    }
    

}
