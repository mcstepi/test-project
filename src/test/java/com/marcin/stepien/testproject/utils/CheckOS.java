package com.marcin.stepien.testproject.utils;

public class CheckOS {
    private static String OS = null;

    public static String getOsName() {
        String os = System.getProperty("os.name").toLowerCase();

        if(os.contains("nux")) {
            return "linux";
        }else if(os.contains("mac")){
            return "mac";
        }
        return "win";
    }
}
