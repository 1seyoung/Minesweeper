package com.practice;

public class User {
    private String name;

    public User(){ // 생성자를 잊지말기...
        this.name = "untitled";
    }

    public void setName(String newName){
        name = newName;
    }

    public String getName() {
        return name; // 반환값은 String
    }
}
