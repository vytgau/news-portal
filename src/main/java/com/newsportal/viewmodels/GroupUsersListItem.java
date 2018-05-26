package com.newsportal.viewmodels;

public class GroupUsersListItem {

    private Long groupId;
    private String groupTitle;

    public GroupUsersListItem() {}

    public GroupUsersListItem(Long groupId, String groupTitle) {
        this.groupId = groupId;
        this.groupTitle = groupTitle;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
}
