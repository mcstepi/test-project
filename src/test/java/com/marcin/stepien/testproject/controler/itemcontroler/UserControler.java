package com.marcin.stepien.testproject.controler.itemcontroler;

import com.marcin.stepien.testproject.model.Item.User;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;

import java.util.List;

public class UserControler {

    public static boolean isUserOnList(List<User> allUsers, User user){
        return allUsers.stream().anyMatch(item ->{
            System.out.println(item.getName()+" equals to "+ user.getName());
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
