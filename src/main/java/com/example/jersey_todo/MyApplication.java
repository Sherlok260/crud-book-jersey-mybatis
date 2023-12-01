package com.example.jersey_todo;

import com.example.jersey_todo.utills.StaticVariables;
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("com.example.jersey_todo");
    }
}