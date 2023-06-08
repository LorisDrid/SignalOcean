package com.example.signalocean;

import android.content.Context;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<User> friends;
    private ArrayList<AbstractPost> posts;


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
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

    public void addFriend(User user, Context context){
        if(!this.getFriends().contains(user) && !this.equals(user)){
            this.getFriends().add(user);
        }
        String notificationTitle = "Demande d'ami";
        String notificationMessage = this.getFirstName() + " " + this.getLastName() + " vous as ajouté en ami !";

        Notification.createNotification(context, notificationTitle, notificationMessage, NotificationChannelManager.CHANNEL_ID_FRIEND_REQUEST);
    }
    public void removeFriend(User user){
        if(this.getFriends().contains(user)){
            this.getFriends().remove(user);
        }
    }

    public ArrayList<AbstractPost> getPosts() {
        return posts;
    }
    public void addPost(AbstractPost post, Context context) {
        posts.add(post);

        for (User friend : this.getFriends()) {
            String notificationTitle = "Nouveau post de " + friend.getFirstName() + " " + friend.getLastName();
            String notificationMessage = "Un ami a publié un nouveau post.";

            Notification.createNotification(context, notificationTitle, notificationMessage, NotificationChannelManager.CHANNEL_ID_NEW_POST);
        }
    }
}

