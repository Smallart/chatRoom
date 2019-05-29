package com.jxust.we_chat_together.netty.packet.impl;

import com.jxust.we_chat_together.netty.packet.Packet;
import com.jxust.we_chat_together.netty.packet.PacketType;

public class AnnounceOtherPeoplePakcet implements Packet {
    private int guid;
    private String inviteName;
    private String accpetName;
    private String accpetImg;
    private String groupName;

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

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public String getInviteName() {
        return inviteName;
    }

    public void setInviteName(String inviteName) {
        this.inviteName = inviteName;
    }

    public String getAccpetName() {
        return accpetName;
    }

    public void setAccpetName(String accpetName) {
        this.accpetName = accpetName;
    }

    @Override
    public Byte getPacketType() {
        return PacketType.ANNUNCIATION_GROUP_OTHERS;
    }
}
