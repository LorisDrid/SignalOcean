package com.example.signalocean;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    private User currentUser;

    public UserManager() {
        this.users = new ArrayList<>();
        User currentuser = new User("Bob", "Lennon", "pyrobarbare@gmail.com","fanta123");
        User user1 = new User("John", "Doe", "john.doe@example.com", "password1");
        User user2 = new User("Jane", "Smith", "jane.smith@example.com", "password2");
        User user3 = new User("Alice", "Johnson", "alice.johnson@example.com", "password3");
        addUser(currentuser);
        addUser(user1);
        addUser(user2);
        addUser(user3);
        this.currentUser = currentuser;
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }


    public void addFriend(User friend, Context context) {
        if (users.contains(currentUser) && users.contains(friend)) {
            currentUser.addFriend(friend, context);
        }
    }

    public void removeFriend(User user, User friend) {
        if (users.contains(user) && users.contains(friend)) {
            user.removeFriend(friend);
        }
    }
    public void removeFriend(User friend) {
        if (users.contains(currentUser) && users.contains(friend)) {
            currentUser.removeFriend(friend);
        }
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
