package com.eternalcode.core.feature.joinmessage;

import java.util.List;

public interface JoinQuitSettings {

    List<JoinQuitMessageEntry> joinMessages();

    List<JoinQuitMessageEntry> quitMessages();

}
