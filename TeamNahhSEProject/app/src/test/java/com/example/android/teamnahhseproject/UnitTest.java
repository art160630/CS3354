package com.example.android.teamnahhseproject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UnitTest {
    @Test
    public void validate(){
        String input = "1234";
        boolean expected = true;

        ProfessorValidationActivity testObject = new ProfessorValidationActivity();
        testObject.validate(input);
        boolean output = testObject.test;
        assertEquals(expected, output);
    }

}