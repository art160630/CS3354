package com.example.android.teamnahhseproject;

import org.junit.Test;

import static org.junit.Assert.*;

public class PulledMethodsTest {

    PulledMethods p = new PulledMethods();

    @Test
    public void getDateTest() {
        String result = p.getDate();
        assertEquals(result, "Fri Dec 07");  //change according to current date of testing
                                                    //Weeks: Mon, Tue, Wed, Thu, Fri, Sat, Sun
                                                    //Months: Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
                                                    //Month number always is 2 characters, for example 05 and not 5
    }
}