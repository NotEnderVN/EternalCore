package com.eternalcode.core.feature.sudo.messages;

import com.eternalcode.multification.notice.Notice;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class ENSudoMessages extends OkaeriConfig implements SudoMessages {
    @Comment({"# {PLAYER} - Player who executed the command, {TARGET} - Player or console on which the command was executed, {COMMAND} - Command"})
    Notice sudoMessageSpy = Notice.chat("<dark_gray>[<dark_red>Sudo<dark_gray>] <red>{PLAYER}<dark_gray> executed command: <white>{COMMAND} <dark_gray>on: <white>{TARGET}");

    @Comment({"# {TARGET} - Player or console on which the command was executed, {COMMAND} - Command"})
    Notice sudoMessage = Notice.chat("<color:#9d6eef>► <white>You executed command: <color:#9d6eef>{COMMAND} <white>on: <color:#9d6eef>{TARGET}");

    @Comment({"# {PLAYER} - Player who executed the command, {TARGET} - Player on which the command was executed, {MESSAGE} - Message"})
    Notice sudoChatMessageSpy = Notice.chat("<dark_gray>[<dark_red>Sudo<dark_gray>] <red>{PLAYER}<dark_gray> forced <white>{TARGET} <dark_gray>to say: <white>{MESSAGE}");

    @Comment({"# {TARGET} - Player on which the command was executed, {MESSAGE} - Message"})
    Notice sudoChatMessage = Notice.chat("<color:#9d6eef>► <white>You forced <color:#9d6eef>{TARGET} <white>to say: <color:#9d6eef>{MESSAGE}");
}
