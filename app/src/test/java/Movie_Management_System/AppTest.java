/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Movie_Management_System;

import org.junit.jupiter.api.Test;

import Movie_Management_System.App;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    // @Test void appHasAGreeting() {
    //     App classUnderTest = new App();
    //     assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    // }
    @Test void testAppInstance() {
        App app = new App();
        assertNotNull(app);
    }
}
