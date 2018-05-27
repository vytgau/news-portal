package com.newsportal.viewmodels;

public class GroupMembersSearchItem {

    private long id;
    private long groupid;
    private String username;
    private String firstname;
    private String lastname;

    public GroupMembersSearchItem() {}

    public GroupMembersSearchItem(long id, long groupid, String username, String firstname, String lastname) {
        this.id = id;
        this.groupid = groupid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
