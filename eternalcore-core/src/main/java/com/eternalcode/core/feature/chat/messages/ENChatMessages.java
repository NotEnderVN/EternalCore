package com.eternalcode.core.feature.chat.messages;

import com.eternalcode.multification.notice.Notice;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class ENChatMessages extends OkaeriConfig implements ChatMessages {

    Notice chatCleared = Notice.chat("<color:#9d6eef>► <white>Chat has been cleared by <color:#9d6eef>{PLAYER}<white>!");

    @Comment
    Notice commandNotFound = Notice.chat("<red>✘ <dark_red>Command <red>{COMMAND} <dark_red>does not exist!");
}
