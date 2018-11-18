package com.marcin.stepien.testproject.model.webpage;

import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.utils.Config;
import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;


@PageUrl(Config.newUserURL)
public class NewUserPage extends FluentPage{

    private static final String FORM_ACTIONS = ".form-actions";

    @FindBy(css = ".page-header h1")
    private FluentWebElement lblHeader;

    @FindBy(id = "name")
    private FluentWebElement fieldNameId;

    @FindBy(id = "user.name.error")
    private FluentWebElement errorNameId;

    @FindBy(id = "email")
    private FluentWebElement fieldEmailId ;

    @FindBy(id = "user.email.error")
    private FluentWebElement errorEmailId ;

    @FindBy(id = "password")
    private FluentWebElement fieldPasswordId;

    @FindBy(id = "user.password.error")
    private FluentWebElement errorPasswordId;

    @FindBy(id = "confirmationPassword")
    private FluentWebElement fieldPasswordRepeatId;

    @FindBy(id = "user.confirmationPassword.error")
    private FluentWebElement errorPasswordRepeatId;

    @FindBy(className = "form-actions")
    private FluentList<FluentWebElement> formActionClass;

    @FindBy(css = "#registrationForm a")
    private FluentWebElement allUsersButton;

    @FindBy(css = "#registrationForm button")
    private FluentWebElement submitButton;

    @FindBy(css = ".control-group label")
    private FluentList<FluentWebElement> controlGroup;

    @Page
    AllUsersPage allUsersPage;

    public NewUserPage typeAllFields(User user ) {
        //await().atMost(5, TimeUnit.SECONDS).until(el(FORM_ACTIONS)).present();

        fieldNameId.write(user.getName());
        fieldEmailId.write(user.getEmail());
        fieldPasswordId.write(user.getPassword());
        fieldPasswordRepeatId.write(user.getPasswordConfirmation());

       // System.out.println("user in form "+user.toString());
        return this;
    }

    public String getHeaderText(){
        return lblHeader.text();
    }
    public FluentWebElement getSubmitButtonIcon(){
        return submitButton.el(By.tagName("i"));
    }
    public NewUserPage submitAddUserForm() {
        submitButton.submit();
        return this;
    }

    public FluentWebElement getAllUserButton(){
        return allUsersButton;
    }

    public AllUsersPage submitAllUserButton(){
        getAllUserButton().submit();
        return allUsersPage;
    }
    public String getFieldSetLabel(int setId){
        if(controlGroup.size() > setId)
          return  controlGroup.get(setId).text();

        return "";
    }
    public String getNameErrorText(){
       return errorNameId.text();
    }

    public String getEmailErrorText(){
        return errorEmailId.text();
    }

    public String getPasswordErrorText(){
        return errorPasswordId.text();
    }
    public String getPasswordConfirmationErrorText(){
        return errorPasswordRepeatId.text();
    }
}
