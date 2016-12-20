package com.example.project.springmvc;

/**
 *
 * Created by mlglenn on 12/20/2016.
 */
public class LoginService {

    public boolean validateUser(String user, String password) {
        return user.equalsIgnoreCase("in28Minutes") && password.equals("dummy");
    }

}
