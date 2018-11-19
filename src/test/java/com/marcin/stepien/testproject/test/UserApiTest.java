package com.marcin.stepien.testproject.test;

import com.marcin.stepien.testproject.helper.itemhelper.UserHelper;
import com.marcin.stepien.testproject.controler.webapicontrol.UserApiControler;
import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.model.ItemFabric.UserFabric;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class UserApiTest{

    @Test
    public void testCanDeleteAllUsers(){

        List<User> allUsersBefore = UserApiControler.getAllUsers();
        if(allUsersBefore.size() == 0){
            User user = UserFabric.getRandomUser();
            assertTrue(UserApiControler.postNewUser(user));
        }

        Assert.assertThat(true, is(UserApiControler.deleteAllUsers()));

        List<User> allUsersAfter = UserApiControler.getAllUsers();

        Assert.assertThat(allUsersAfter.size(),is(0));
    }

    @Test
    public void testCanAddUserViaApi(){

        List<User> allUsersBefore = UserApiControler.getAllUsers();

        User user = UserFabric.getRandomUser();
        assertTrue(UserApiControler.postNewUser(user));

        List<User> allUsersAfter = UserApiControler.getAllUsers();

        Assert.assertThat(allUsersAfter.size(),is(allUsersBefore.size() + 1));
        Assert.assertThat(true ,is(UserHelper.isUserOnList(allUsersAfter,user)));
    }
}
