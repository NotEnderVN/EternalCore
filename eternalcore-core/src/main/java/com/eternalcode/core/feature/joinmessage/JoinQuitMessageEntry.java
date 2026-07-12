package com.eternalcode.core.feature.joinmessage;

import java.io.Serializable;

public class JoinQuitMessageEntry implements Serializable {

    public String permission;
    public String message;

    public JoinQuitMessageEntry() {}

    public JoinQuitMessageEntry(String permission, String message) {
        this.permission = permission;
        this.message = message;
    }

    public String permission() {
        return this.permission;
    }

    public String message() {
        return this.message;
    }
}
