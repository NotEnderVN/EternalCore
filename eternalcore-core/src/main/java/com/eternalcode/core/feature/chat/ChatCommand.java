package com.eternalcode.core.feature.chat;

import com.eternalcode.annotations.scan.command.DescriptionDocs;
import com.eternalcode.core.event.EventCaller;
import com.eternalcode.core.feature.chat.event.ClearChatEvent;
import com.eternalcode.core.injector.annotations.Inject;
import com.eternalcode.core.notice.NoticeService;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Sender;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;

@Command(name = "chat")
@Permission("eternalcore.chat")
class ChatCommand {

    private static final String EMPTY_CHAT_STRING = " ";

    private final NoticeService noticeService;
    private final ChatSettings chatSettings;
    private final EventCaller eventCaller;
    private final Server server;

    @Inject
    ChatCommand(
        NoticeService noticeService,
        ChatSettings chatSettings,
        EventCaller eventCaller,
        Server server
    ) {
        this.noticeService = noticeService;
        this.chatSettings = chatSettings;
        this.eventCaller = eventCaller;
        this.server = server;
    }

    @Execute(name = "clear", aliases = "cc")
    @DescriptionDocs(description = "Clears chat")
    void clear(@Sender CommandSender sender) {
        ClearChatEvent event = this.eventCaller.callEvent(new ClearChatEvent(sender));

        if (event.isCancelled()) {
            return;
        }

        this.server.getOnlinePlayers().forEach(player -> {
            for (int i = 0; i < this.chatSettings.linesToClear(); i++) {
                player.sendMessage(EMPTY_CHAT_STRING);
            }
        });

        this.noticeService.create()
            .notice(translation -> translation.chat().chatCleared())
            .placeholder("{PLAYER}", sender.getName())
            .onlinePlayers()
            .send();
    }
}
