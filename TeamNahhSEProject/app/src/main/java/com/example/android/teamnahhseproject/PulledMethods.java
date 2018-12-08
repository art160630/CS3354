package com.example.android.teamnahhseproject;

import java.util.Date;

public class PulledMethods {

    public String getDate(){
        Date date = new Date();
        String s = date.toString().substring(0, (date.toString().length()) - 18);
        return s;
    }
}
