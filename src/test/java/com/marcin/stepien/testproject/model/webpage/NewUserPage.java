package com.marcin.stepien.testproject.model.webpage;

import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.utils.Config;
import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;


//@PageUrl(Config.newUserURL)
@PageUrl("http://85.93.17.135:9000")
public class NewUserPage extends FluentPage{

    @FindBy(className = "page-header")
    private FluentWebElement lblHeaderClass;

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

    @FindBy(id = "user.confirmationPassword.error\"")
    private FluentWebElement errorPasswordRepeatId;

    @FindBy(className = "form-actions")
    private FluentList<FluentWebElement> formActionClass;

    @FindBy(className = "btn btn-primary")
    private FluentList<FluentWebElement> buttons;

    @FindBy(tagName = "button")
    private FluentWebElement submitButton;


    public NewUserPage typeAllFields(User user ) {
        await().atMost(5, TimeUnit.SECONDS).until(lblHeaderClass).present();

        fieldNameId.write(user.getLogin());
        fieldEmailId.write(user.getEmail());
        fieldPasswordId.write(user.getPassword());
        fieldPasswordRepeatId.write(user.getPassword());
        return this;
    }

    public NewUserPage submitAddUserForm() {
        submitButton.submit();
        return this;
    }

}
