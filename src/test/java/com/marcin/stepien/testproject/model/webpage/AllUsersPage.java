package com.marcin.stepien.testproject.model.webpage;

import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.utils.Config;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@PageUrl(Config.allUsersURL)
public class AllUsersPage extends FluentPage {

    @FindBy(css = ".page-header h1")
    private FluentWebElement lblHeader;

    @FindBy(css = "#users thead tr")
    private FluentWebElement tableHeader;

    @FindBy(css = "#users tbody tr")
    private FluentList<FluentWebElement> dataUsers;

    @FindBy(css = "a.btn")
    private FluentWebElement btnNewUser;

    @Page
    NewUserPage newUserPage;

    public NewUserPage submitNewUser() {
        btnNewUser.submit();
        return newUserPage;
    }

    public FluentList<FluentWebElement> getTableData(){
        return dataUsers;
    }


    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();

        dataUsers.forEach(item ->{
           if( item.find("td:nth-child(3)").present()) {
               User user = new User();
               user.setName(item.el(By.cssSelector("td:nth-child(1)")).text());
               user.setEmail(item.el(By.cssSelector("td:nth-child(2)")).text());
               user.setPassword(item.el(By.cssSelector("td:nth-child(3)")).text());
               users.add(user);
           }
        });

        return users;
    }

    public String getHeaderText(){
        return lblHeader.text();
    }

    public String getTableHeaderText(int colId){
        return tableHeader.el(By.cssSelector("th:nth-child("+colId+")")).text();
    }

    public String getBtnNewUserText() {
        return btnNewUser.text();
    }
}
