package com.marcin.stepien.testproject.test;

import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.model.ItemFabric.UserFabric;
import com.marcin.stepien.testproject.model.webpage.NewUserPage;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

public class AddUserTest  extends FluentTest{

    @Page
    NewUserPage newUserPage;

    @Test
    public void addNewUser() {
        User user = UserFabric.getRandomUser();
        goTo(newUserPage)
                .typeAllFields(user )
                .submitAddUserForm();

    }
}
