package com.newsportal.viewmodels;

public class GroupInvitationsItem {

    private long invitationId;
    private String groupTitle;

    public GroupInvitationsItem() {}

    public GroupInvitationsItem(long invitationId, String groupTitle) {
        this.invitationId = invitationId;
        this.groupTitle = groupTitle;
    }

    public long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(long invitationId) {
        this.invitationId = invitationId;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
}
