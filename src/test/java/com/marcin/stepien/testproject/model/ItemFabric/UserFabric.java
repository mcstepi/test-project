package com.marcin.stepien.testproject.model.ItemFabric;

import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.utils.Randomizer;

public class UserFabric {

    public static User getRandomUser(){
        Randomizer randomizer = new Randomizer();
        User user = new User();

        user.setLogin(randomizer.getRandomString(5));
        user.setEmail(randomizer.getRandomEmail(5));
        user.setPassword(randomizer.getRandomString(8));

        return user;
    }
}
