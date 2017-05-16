package com.example.framgia.myapplication;

import javax.inject.Inject;

/**
 * Created by phanvanlinh on 12/05/2017.
 * Email: phanvanlinh.94vn@gmail.com
 */

public class NetworkApi {

    @Inject
    public NetworkApi(){

    }

    public boolean validataUser(String username, String password){
        // imagine an actual network call here
        // for demo purpose return false in "real" life
        if(username == null || username.length() == 0){
            return false;
        }else{
            return true;
        }
    }
}
