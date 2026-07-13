package com.eternalcode.core.feature.quitmessage;

import com.eternalcode.commons.RandomElementUtil;
import com.eternalcode.core.feature.joinmessage.JoinQuitMessageEntry;
import com.eternalcode.core.feature.joinmessage.JoinQuitSettings;
import com.eternalcode.core.injector.annotations.Inject;
import com.eternalcode.core.injector.annotations.component.Controller;
import com.eternalcode.core.notice.NoticeService;
import com.eternalcode.multification.notice.Notice;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@Controller
class PlayerQuitMessageController implements Listener {

    private static final String EMPTY_MESSAGE = null;

    private final NoticeService noticeService;
    private final JoinQuitSettings joinQuitSettings;

    @Inject
    PlayerQuitMessageController(NoticeService noticeService, JoinQuitSettings joinQuitSettings) {
        this.noticeService = noticeService;
        this.joinQuitSettings = joinQuitSettings;
    }

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(EMPTY_MESSAGE);

        for (JoinQuitMessageEntry entry : this.joinQuitSettings.quitMessages()) {
            if (player.hasPermission(entry.permission())) {
                this.noticeService.create()
                    .notice(translation -> Notice.chat(entry.message()))
                    .placeholder("{PLAYER}", player.getName())
                    .onlinePlayers()
                    .sendAsync();
                return;
            }
        }

        this.noticeService.create()
            .noticeOptional(translation -> RandomElementUtil.randomElement(translation.quit().playerLeftServer()))
            .placeholder("{PLAYER}", player.getName())
            .onlinePlayers()
            .sendAsync();
    }
}
