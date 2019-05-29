package com.jxust.we_chat_together.netty.packet;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jxust.we_chat_together.netty.packet.impl.*;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.EXISTING_PROPERTY,property ="packetType" )
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoginRequestPacket.class ,name = "1"),
        @JsonSubTypes.Type(value = SendMessageToUserPacket.class ,name = "4"),
        @JsonSubTypes.Type(value = UserReadInfoPacket.class ,name = "7"),
        @JsonSubTypes.Type(value = GroupMessageRequestPacket.class ,name = "8"),
        @JsonSubTypes.Type(value = AddFriendRequestPacket.class ,name = "9"),
        @JsonSubTypes.Type(value = InvitedPeopleToGourpPacket.class ,name = "10"),
        @JsonSubTypes.Type(value = AnnounceOtherPeoplePakcet.class ,name = "11"),
        @JsonSubTypes.Type(value = InvitedPeopleToGroupResponsePacket.class ,name = "12"),
        @JsonSubTypes.Type(value = LoginOutRequestPakcet.class ,name = "13"),
        @JsonSubTypes.Type(value = ExitGroupRequestPacket.class ,name = "14"),
        @JsonSubTypes.Type(value = AddGroupRequestPacket.class ,name = "16"),
        @JsonSubTypes.Type(value = CreateGroupRequestPacket.class ,name = "18")
})
public interface Packet {
    Byte getPacketType();
}
