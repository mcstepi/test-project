package com.marcin.stepien.testproject.helper.itemhelper;

import com.marcin.stepien.testproject.model.Item.User;

import java.util.List;

public class UserHelper {

    public static boolean isUserOnList(List<User> allUsers, User user){
        return allUsers.stream().anyMatch(item ->{
                    if(  item.getName().equals(user.getName()) &&
                            item.getEmail().equals(user.getEmail())&&
                            item.getPassword().equals(user.getPassword()))
                        return true;
                    else
                        return false;
                }
        );
    }
}
