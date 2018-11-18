package com.marcin.stepien.testproject.controler.webapicontrol;

import com.jayway.restassured.response.Response;
import com.marcin.stepien.testproject.model.Item.User;
import com.marcin.stepien.testproject.utils.Config;

import java.util.Arrays;
import java.util.List;

public class UserApiControler  extends ApiControler{


    public static List<User> getAllUsers(){

        Response response = doGetRequest(Config.allUsersJson);
        List<User> list = Arrays.asList(response.getBody().as(User[].class));

        return list;
    }

    public static boolean deleteAllUsers(){

        int status = doDeleteRequest(Config.allUsersDelete);
        if(status == 200)
            return true;

        return false;
    }


    public static boolean postNewUser(User user){
        String json = " {'name': '%s'," +
                      " 'email': '%s', " +
                      " 'password': '%s' }";
        json = String.format(json,user.getName(),user.getEmail(),user.getPassword());
        int status = doPostRequest(Config.newUserPost,json);
        if(status == 200)
            return true;

        return false;
    }


}
