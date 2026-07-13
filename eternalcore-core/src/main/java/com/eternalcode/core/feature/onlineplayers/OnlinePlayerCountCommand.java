package com.eternalcode.core.feature.onlineplayers;

import com.eternalcode.annotations.scan.command.DescriptionDocs;
import com.eternalcode.core.injector.annotations.Inject;
import com.eternalcode.core.notice.NoticeService;
import com.eternalcode.core.viewer.Viewer;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Sender;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import org.bukkit.Server;

@Command(name = "online")
@Permission("eternalcore.online")
class OnlinePlayerCountCommand {

    private final NoticeService noticeService;
    private final Server server;

    @Inject
    OnlinePlayerCountCommand(NoticeService noticeService, Server server) {
        this.noticeService = noticeService;
        this.server = server;
    }

    @Execute
    @DescriptionDocs(description = "Shows online players count")
    void execute(@Sender Viewer viewer) {
        long visiblePlayerCount = this.server.getOnlinePlayers().size();

        this.noticeService
            .create()
            .notice(translation -> translation.online().onlinePlayersCount())
            .viewer(viewer)
            .placeholder("{ONLINE}", String.valueOf(visiblePlayerCount))
            .send();
    }
}
