package com.marcin.stepien.testproject.utils;

import java.util.Random;

public class Randomizer {

    private int leftLimit = 97; // letter 'a'
    private int rightLimit = 122; // letter 'z'

    public String getRandomString(int length){

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public String getRandomEmail(int length){
        String login = getRandomString(length);
        String domain = getRandomString(5);
        return login+"@"+domain+".com";
    }
}
