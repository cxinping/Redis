package com.dxtd.SpringSessionDemo.controller;

import java.util.Date;

public class CalTime {

    public static void main(String[] args) {
        long timestamp = 1606589640000L ;
        String date2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp ));
        System.out.println(date2);
    }
}
