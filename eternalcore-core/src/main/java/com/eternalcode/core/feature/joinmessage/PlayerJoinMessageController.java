package com.eternalcode.core.feature.joinmessage;

import com.eternalcode.commons.RandomElementUtil;
import com.eternalcode.core.injector.annotations.Inject;
import com.eternalcode.core.injector.annotations.component.Controller;
import com.eternalcode.core.notice.NoticeService;
import com.eternalcode.multification.notice.Notice;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@Controller
class PlayerJoinMessageController implements Listener {

    private static final String EMPTY_MESSAGE = null;

    private final NoticeService noticeService;
    private final JoinQuitSettings joinQuitSettings;

    @Inject
    PlayerJoinMessageController(NoticeService noticeService, JoinQuitSettings joinQuitSettings) {
        this.noticeService = noticeService;
        this.joinQuitSettings = joinQuitSettings;
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(EMPTY_MESSAGE);

        for (JoinQuitMessageEntry entry : this.joinQuitSettings.joinMessages()) {
            if (player.hasPermission(entry.permission())) {
                this.noticeService.create()
                    .notice(translation -> Notice.chat(entry.message()))
                    .placeholder("{PLAYER}", player.getName())
                    .onlinePlayers()
                    .sendAsync();
                return;
            }
        }

        boolean firstTime = !player.hasPlayedBefore();

        this.noticeService.create()
            .noticeOptional(translation -> RandomElementUtil.randomElement(
                firstTime
                    ? translation.join().playerJoinedServerFirstTime()
                    : translation.join().playerJoinedServer()
            ))
            .placeholder("{PLAYER}", player.getName())
            .onlinePlayers()
            .sendAsync();
    }
}
