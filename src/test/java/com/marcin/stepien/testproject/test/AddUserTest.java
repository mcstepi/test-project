package com.marcin.stepien.testproject.test;

import com.marcin.stepien.testproject.controler.itemcontroler.UserControler;
import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.model.ItemFabric.UserFabric;
import com.marcin.stepien.testproject.model.webpage.AllUsersPage;
import com.marcin.stepien.testproject.model.webpage.NewUserPage;
import com.marcin.stepien.testproject.utils.Config;
import com.marcin.stepien.testproject.utils.Randomized;
import com.marcin.stepien.testproject.controler.webapicontrol.UserApiControler;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class AddUserTest  extends FluentTest{

    @Page
    NewUserPage newUserPage;
    @Page
    AllUsersPage allUsersPage;

    private String maxSizeMessage = "Maximum size is 255";
    private String minSizeMessage = "Minimum size is 6";
    private String invalidEmailMessage = "Invalid email address";
    private String notSamePasswordMessage = "passwords are not the same";
    private String requiredFieldMessage = "Required";
    private String uniqueMessage = "Must be unique";

    @Test
    public void testCheckLabels(){
        goTo(newUserPage);

        Assert.assertThat(newUserPage.getUrl(), is(Config.newUserURL));
        Assert.assertThat(newUserPage.getHeaderText(), is("New User"));
        Assert.assertThat(newUserPage.getFieldSetLabel(0), is("Name:"));
        Assert.assertThat(newUserPage.getFieldSetLabel(1), is("Email:"));
        Assert.assertThat(newUserPage.getFieldSetLabel(2), is("Password:"));
        Assert.assertThat(newUserPage.getFieldSetLabel(3), is("Confirmation password:"));
    }

    @Test
    public void testIfSubmitButtonHasIcon(){

        NewUserPage page = goTo(newUserPage);
        Assert.assertThat("icon-lock icon-white", is(page.getSubmitButtonIcon().attribute("class")));
    }

    @Test
    public void testNoErrorsVisibleOnPageInit(){
        NewUserPage page = goTo(newUserPage);
        Assert.assertThat(page.getNameErrorText(), is(""));
        Assert.assertThat(page.getEmailErrorText(), is(""));
        Assert.assertThat(page.getPasswordErrorText(), is(""));
        Assert.assertThat(page.getPasswordConfirmationErrorText(), is(""));
    }

    @Test
    public void testAddNewUser() {
        User user = UserFabric.getRandomUser();
        addUser(user);

        List<User> allUsers = UserApiControler.getAllUsers();
        Assert.assertThat(UserControler.isUserOnList(allUsers,user), is(true));
    }

    @Test
    public void testTryAddEmptyUser(){

        List<User> usersBefore = UserApiControler.getAllUsers();

        NewUserPage page = goTo(newUserPage);

        page.submitAddUserForm();
        Assert.assertThat(page.getUrl(), is(Config.newUserURL));

        List<User> usersAfter = UserApiControler.getAllUsers();

        Assert.assertThat(usersBefore.size() == usersAfter.size(), is(true));

        Assert.assertThat(page.getNameErrorText(), is(requiredFieldMessage));
        Assert.assertThat(page.getEmailErrorText(), is(requiredFieldMessage));
        Assert.assertThat(page.getPasswordErrorText(), is(requiredFieldMessage));
        Assert.assertThat(page.getPasswordConfirmationErrorText(), is(""));

    }

    @Test
    public void testAddUserVeryLongName(){
        User user = UserFabric.getRandomUserWitNameLength(255);
        addUser(user);

        assertUserInAllUsers(user, true);
    }

    @Test
    public void testUserNameExceedsLimit(){
        User user = UserFabric.getRandomUserWitNameLength(256);

        NewUserPage page = addUser(user);

        Assert.assertThat(page.getNameErrorText(), is(maxSizeMessage));

        assertUserInAllUsers(user, false);
    }

    @Test
    public void testMaxLenghtEmail(){
        User user = UserFabric.getRandomUser();
        Randomized randomized = new Randomized();
        user.setEmail(randomized.getRandomString(200)+"@"+randomized.getRandomString(51)+".pl");

        addUser(user);

        assertUserInAllUsers(user, true);
    }

    @Test
    public void testMaxLenghtExceedEmail(){
        User user = UserFabric.getRandomUser();
        Randomized randomized = new Randomized();
        user.setEmail(randomized.getRandomString(200)+"@"+randomized.getRandomString(52)+".pl");

        NewUserPage page = addUser(user);

        Assert.assertThat(page.getEmailErrorText(), is(maxSizeMessage));

        assertUserInAllUsers(user, false);
    }

    @Test
    public void testAddUserVeryLongPassword(){
        User user = UserFabric.getRandomUserWitPasswdLength(255);

        addUser(user);

        assertUserInAllUsers(user, true);
    }

    @Test
    public void testPasswordExceedsLimit(){
        User user = UserFabric.getRandomUserWitPasswdLength(256);

        NewUserPage page = addUser(user);

        Assert.assertThat(page.getPasswordErrorText(), is(maxSizeMessage));

        assertUserInAllUsers(user, false);
    }

    @Test
    public void testBadEmail(){
        User user = UserFabric.getRandomUser();
        Randomized randomized = new Randomized();
        user.setEmail(randomized.getRandomString(10));

        NewUserPage page = addUser(user);

        Assert.assertThat(page.getEmailErrorText(), is(invalidEmailMessage));

        assertUserInAllUsers(user, false);
    }

    @Test
    public void testMissingPasswordConfirmation(){
        User user = UserFabric.getRandomUser();
        user.setPasswordConfirmation("");

        NewUserPage page = addUser(user);

        Assert.assertThat(page.getPasswordConfirmationErrorText(), is(notSamePasswordMessage));

        assertUserInAllUsers(user, false);
    }

    @Test
    public void testIncorrectPasswordConfirmation(){
        User user = UserFabric.getRandomUser();
        Randomized randomized = new Randomized();
        user.setPasswordConfirmation(randomized.getRandomString(10));

        NewUserPage page = addUser(user);

        Assert.assertThat(page.getPasswordConfirmationErrorText(), is(notSamePasswordMessage));

        assertUserInAllUsers(user, false);
    }

    @Test
    public void testAddAccountWithoutPassword(){
        User user = UserFabric.getRandomUser();
        user.setPassword("");
        user.setPasswordConfirmation("");

        NewUserPage page = addUser(user);

       Assert.assertThat(page.getPasswordErrorText(), is(requiredFieldMessage));

       assertUserInAllUsers(user, false);
    }

    @Test
    public void testToShortPassword(){
        User user = UserFabric.getRandomUserWitPasswdLength(5);

        NewUserPage page = addUser(user);
       System.out.println(user);

        Assert.assertThat(page.getPasswordErrorText(), is(minSizeMessage));

        assertUserInAllUsers(user, false);
    }

    @Test
    public void testToAddSameUserNameAgain(){
        User user = UserFabric.getRandomUser();

        addUser(user);

        List<User> usersBefore = assertUserInAllUsers(user, true);

        Randomized randomized = new Randomized();
        user.setEmail(randomized.getRandomEmail(10));

        NewUserPage page = addUser(user);

        Assert.assertThat(page.getNameErrorText(), is(uniqueMessage));

        List<User> usersAfter = assertUserInAllUsers(user, false);
        Assert.assertThat(usersBefore.size() == usersAfter.size(), is(true));
    }

    @Test
    public void testToAddSameUserEmailAgain(){
        User user = UserFabric.getRandomUser();

        addUser(user);

        List<User> usersBefore = assertUserInAllUsers(user, true);

        Randomized randomized = new Randomized();
        user.setName(randomized.getRandomEmail(10));

        NewUserPage page =  addUser(user);

        Assert.assertThat(page.getEmailErrorText(), is(uniqueMessage));

        List<User> usersAfter = assertUserInAllUsers(user, false);
        Assert.assertThat(usersBefore.size() == usersAfter.size() , is(true));
    }

    @Test
    public void testXss(){
        User user = UserFabric.getRandomUser();
        user.setName("<script>alert(‘"+user.getName()+"’)</script>");

        addUser(user);

        assertUserInAllUsers(user, true);

        List<User> usersList = allUsersPage.getAllUsers();
        Assert.assertThat(UserControler.isUserOnList(usersList, user), is(true));
    }

    @Test
    public void testCharactersInName(){
        User user = UserFabric.getRandomUser();
        user.setName(user.getName()+";:\"',./?\\!@#$%^&*()_+- =£§");

        addUser(user);

        assertUserInAllUsers(user, true);
    }

    @Test
    public void testCharactersInEmail(){
        User user = UserFabric.getRandomUser();
        user.setEmail(";:\"',./?\\!@#$%^&*()_+- =.£§");

        NewUserPage page = addUser(user);

        Assert.assertThat(page.getEmailErrorText(), is(invalidEmailMessage));

        assertUserInAllUsers(user, false);
    }

   @Test
    public void testCharactersInPassword(){
        User user = UserFabric.getRandomUser();
        user.setPassword(";:\"',./?\\!@#$%^&*()_+- =.£§");
        user.setPasswordConfirmation(user.getPassword());

        addUser(user);

        assertUserInAllUsers(user, true);
    }

    @Test
    public void testAllUserButton(){

        NewUserPage page = goTo(newUserPage);

        Assert.assertThat(Config.allUsersURL, is(page.getAllUserButton().attribute("href")));

        AllUsersPage allPage = page.submitAllUserButton();
        Assert.assertThat(Config.allUsersURL, is((allPage.getUrl())));

    }

    /*
    * Helping methods
    * */

    private NewUserPage addUser(User user){
        return goTo(newUserPage)
                .typeAllFields(user )
                .submitAddUserForm();
    }

    private List<User> assertUserInAllUsers(User user, boolean exists) {
        List<User> allUsers = UserApiControler.getAllUsers();
        Assert.assertThat(UserControler.isUserOnList(allUsers,user), is(exists));
        return allUsers;
    }

}
