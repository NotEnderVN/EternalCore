package com.eternalcode.core.feature.joinmessage;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import java.util.List;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class JoinQuitConfig extends OkaeriConfig implements JoinQuitSettings {

    @Comment({
        "# Custom permission-based join messages",
        "# The plugin will check each entry in order, and use the message for the first permission the player has.",
        "# Placeholders: {PLAYER}"
    })
    public List<JoinQuitMessageEntry> joinMessages = List.of(
        new JoinQuitMessageEntry("eternalcore.join.admin", "<red>[Admin] {PLAYER} has joined the game!"),
        new JoinQuitMessageEntry("eternalcore.join.vip", "<gold>[VIP] {PLAYER} has joined the game!")
    );

    @Comment({
        "# Custom permission-based quit messages",
        "# The plugin will check each entry in order, and use the message for the first permission the player has.",
        "# Placeholders: {PLAYER}"
    })
    public List<JoinQuitMessageEntry> quitMessages = List.of(
        new JoinQuitMessageEntry("eternalcore.quit.admin", "<red>[Admin] {PLAYER} has left the game!"),
        new JoinQuitMessageEntry("eternalcore.quit.vip", "<gold>[VIP] {PLAYER} has left the game!")
    );
}
