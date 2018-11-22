package com.marcin.stepien.testproject.utils;

import java.util.Random;

public class Randomized {

    private int leftLimit = 97; // letter 'a'
    private int rightLimit = 122; // letter 'z'

    private String characters = "aąbcćdeęfghijklłmnoópqrsśtuvwxyzźż0123456789";
    private Random random;
    private int charactersLimit;

    public Randomized(){
        random = new Random();
        charactersLimit = characters.length()-1;
    }

    public String getRandomSmallString(int length){

        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = getRandomCharInt(leftLimit, rightLimit);
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public String getRandomFullString(int length){

        StringBuilder buffer = new StringBuilder(length);
        String character;
        for (int i = 0; i < length; i++) {
            int randomInt = getRandomCharInt(0, charactersLimit);
            character = String.valueOf(characters.charAt(randomInt));
            if(isUpperCase()){
                character = character.toUpperCase();
            }
            buffer.append(character);
        }
        return buffer.toString();
    }

    private int getRandomCharInt(int leftLimit, int rightLimit) {
        return leftLimit +(int) (random.nextFloat() * (rightLimit - leftLimit + 1));
    }

    private boolean isUpperCase() {
        int randomInt = (int) (random.nextFloat() * (10 ));
        if (randomInt %2 == 0){
            return true;
        }else {
            return false;
        }
    }

    public String getRandomEmail(int length){
        String login = getRandomSmallString(length);
        String domain = getRandomSmallString(5);
        return login+"@"+domain+".com";
    }

}
