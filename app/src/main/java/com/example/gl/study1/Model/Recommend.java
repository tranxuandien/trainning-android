package com.example.gl.study1.Model;

public class Recommend  {
    public Recommend(String displayName) {
        this.displayName = displayName;
    }

    private String userId;
    private String displayName;
    private Boolean isFriendRequest;
    private boolean addFriends = true;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getFriendRequest() {
        return isFriendRequest;
    }

    public void setFriendRequest(Boolean friendRequest) {
        isFriendRequest = friendRequest;
    }

    public boolean isAddFriends() {
        return addFriends;
    }

    public void setAddFriends(boolean addFriends) {
        this.addFriends = addFriends;
    }
}
