package com.example.signalocean;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<User> friends;


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.friends = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<User> getFriends(){
        return this.friends;
    }

    public void addFriend(User user){
        if(!this.getFriends().contains(user) && !this.equals(user)){
            this.getFriends().add(user);
        }
    }
    public void removeFriend(User user){
        if(this.getFriends().contains(user)){
            this.getFriends().remove(user);
        }
    }
}

