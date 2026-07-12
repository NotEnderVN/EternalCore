package com.eternalcode.core.feature.chat;

public interface ChatSettings {

    boolean replaceStandardHelpMessage();

    int linesToClear();

    ChatSettings linesToClear(int lines);
}
