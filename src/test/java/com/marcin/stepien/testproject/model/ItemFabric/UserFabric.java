package com.marcin.stepien.testproject.model.ItemFabric;

import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.utils.Randomized;

public class UserFabric {
    private static int standardLenght = 10;
    public static User getRandomUser(){
        Randomized randomized = new Randomized();
        User user = new User();

        user.setName(randomized.getRandomFullString(standardLenght));
        user.setEmail(randomized.getRandomEmail(standardLenght));
        user.setPassword(randomized.getRandomFullString(standardLenght));
        user.setPasswordConfirmation(user.getPassword());
        return user;
    }
    public static User getRandomUserWitNameLength(int lenght){
        Randomized randomized = new Randomized();
        User user = getRandomUser();
        user.setName(randomized.getRandomFullString(lenght));

        return user;
    }

    public static User getRandomUserWitPasswdLength(int lenght){
        Randomized randomized = new Randomized();
        User user = getRandomUser();
        user.setPassword(randomized.getRandomFullString(lenght));
        user.setPasswordConfirmation(user.getPassword());
        return user;
    }

}
