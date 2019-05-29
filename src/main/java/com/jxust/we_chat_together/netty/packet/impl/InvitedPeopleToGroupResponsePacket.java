package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

public class InvitedPeopleToGroupResponsePacket implements Packet {
    private Boolean isSuccess;
    private String inviteName;
    private String acceptName;
    private String accpetImg;
    private String guuid;
    private String groupName;
    private int gid;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAccpetImg() {
        return accpetImg;
    }

    public void setAccpetImg(String accpetImg) {
        this.accpetImg = accpetImg;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getInviteName() {
        return inviteName;
    }

    public void setInviteName(String inviteName) {
        this.inviteName = inviteName;
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getGuuid() {
        return guuid;
    }

    public void setGuuid(String guuid) {
        this.guuid = guuid;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.INVITED_REIEND_TO_GROUP_RESPONSE;
    }
}
