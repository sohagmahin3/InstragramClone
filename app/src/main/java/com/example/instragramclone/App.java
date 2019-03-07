package com.example.instragramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("tmGfX6oKBC7JAh2hw5G8x8gvILoTmDhWIkRx7E8r")
                // if defined
                .clientKey("ZzA586AwhhS3HeuR314n9XY0wqhADdvK0q9NE6ww")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
