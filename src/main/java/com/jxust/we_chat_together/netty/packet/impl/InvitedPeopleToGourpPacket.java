package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

public class InvitedPeopleToGourpPacket implements Packet
{
    private int groupId;
    private String groupUUID;
    private String groupName;
    private String groupImg;
    private String toUUID;
    private String inviteName;
    private String inviteUserUid;

    public String getInviteUserUid() {
        return inviteUserUid;
    }

    public void setInviteUserUid(String inviteUserUid) {
        this.inviteUserUid = inviteUserUid;
    }

    public String getInviteName() {
        return inviteName;
    }

    public void setInviteName(String inviteName) {
        this.inviteName = inviteName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupUUID() {
        return groupUUID;
    }

    public void setGroupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public String getToUUID() {
        return toUUID;
    }

    public void setToUUID(String toUUID) {
        this.toUUID = toUUID;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.INVITED_FRIEND_TO_GROUP;
    }
}
