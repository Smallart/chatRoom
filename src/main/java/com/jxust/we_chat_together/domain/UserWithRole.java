package com.jxust.we_chat_together.domain;

public class UserWithRole {
    private String uuid;
    private Role role;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserWithRole{" +
                "uuid='" + uuid + '\'' +
                ", role=" + role +
                '}';
    }
}
