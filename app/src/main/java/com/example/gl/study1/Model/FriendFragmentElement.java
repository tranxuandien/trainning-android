package com.example.gl.study1.Model;

import java.util.List;

/**
 * Created by truongdat277 on 8/31/2016.
 */
public class FriendFragmentElement {
    private String name;
    List<Friends> friendses;
    private boolean isExpandable=true;

    public FriendFragmentElement(String name, boolean isExpandable) {
        this.name = name;
        this.isExpandable = isExpandable;
    }

    public List<Friends> getFriendses() {
        return friendses;
    }

    public void setFriendses(List<Friends> friendses) {
        this.friendses = friendses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
