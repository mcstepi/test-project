package com.marcin.stepien.testproject.model.Item;

public class User {
    private String login;
    private String email;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return "name:"+getLogin()+"\n"
                +"email:"+getEmail()+"\n"
                +"pass:"+getPassword()+"\n";
    }
}
