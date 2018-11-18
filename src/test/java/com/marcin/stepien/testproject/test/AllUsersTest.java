package com.marcin.stepien.testproject.test;

import com.marcin.stepien.testproject.controler.webapicontrol.UserApiControler;
import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.model.ItemFabric.UserFabric;
import com.marcin.stepien.testproject.model.webpage.AllUsersPage;
import com.marcin.stepien.testproject.model.webpage.NewUserPage;
import com.marcin.stepien.testproject.utils.Config;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class AllUsersTest extends FluentTest {

    @Page
    AllUsersPage allUsersPage;
    @Page
    NewUserPage newUserPage;

    @Test
    public void testCheckLabels(){
        goTo(allUsersPage);

        Assert.assertThat(allUsersPage.getUrl(), is(Config.allUsersURL));
        Assert.assertThat(allUsersPage.getHeaderText(), is("All User"));

        Assert.assertThat(allUsersPage.getTableHeaderText(1), is("Name"));
        Assert.assertThat(allUsersPage.getTableHeaderText(2), is("Email"));
        Assert.assertThat(allUsersPage.getTableHeaderText(3), is("Password"));

        Assert.assertThat(allUsersPage.getBtnNewUserText(), is("New User"));
    }

    @Test
    public void testTableDisplaysAllUsers(){

        List<User> allUsersFromApi = UserApiControler.getAllUsers();
        if(allUsersFromApi.size() == 0){
            User user = UserFabric.getRandomUser();
            UserApiControler.postNewUser(user);
            allUsersFromApi = UserApiControler.getAllUsers();
        }
        List<User> allUsersFromPage = goTo(allUsersPage).getAllUsers();

        Assert.assertThat(allUsersFromApi.size(), is(allUsersFromPage.size()));

        for(int i=0; i<allUsersFromApi.size(); i++){
            Assert.assertThat(allUsersFromApi.get(i).toString(),
                    is(allUsersFromPage.get(i).toString()));
        }
    }

    @Test
    public void testEmptyTable(){

        UserApiControler.deleteAllUsers();

        List<User> allUsersFromPage = goTo(allUsersPage).getAllUsers();
        Assert.assertThat(allUsersFromPage.size(), is(0));
        Assert.assertThat(allUsersPage.getTableData().get(0).text(), is("No Users"));
    }

}
