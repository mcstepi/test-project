package com.marcin.stepien.testproject.utils;

public class Config {
    public static final String mainPageAddress = "http://85.93.17.135:9000";
    public static final String newUser = "/user/new";
    public static final String allUsers = "/users/all";
    public static final String allUsersJson = mainPageAddress +"/user/all/json";
    public static final String allUsersDelete = mainPageAddress +"/user/all";
    public static final String newUserPost = mainPageAddress + "/user/save/json";
    public static final String newUserURL =  mainPageAddress + newUser;
    public static final String allUsersURL =  mainPageAddress + allUsers;


    public static String chromedriverPath = "src/test/resources/%s/chromedriver";
}
