package com.eternalcode.core.feature.deathmessage;

import com.eternalcode.core.feature.deathmessage.config.DeathMessageSettings;
import com.eternalcode.core.injector.annotations.Inject;
import com.eternalcode.core.injector.annotations.component.Controller;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@Controller
class DeathMessageController implements Listener {

    private static final String NO_MESSAGE = "";

    private final DeathMessageService deathMessageService;
    private final DeathMessageSettings settings;

    @Inject
    DeathMessageController(
        DeathMessageService deathMessageService,
        DeathMessageSettings settings
    ) {
        this.deathMessageService = deathMessageService;
        this.settings = settings;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        if (!this.settings.customMessagesEnabled()) {
            return;
        }

        event.setDeathMessage(NO_MESSAGE);
        this.deathMessageService.handleDeath(victim);
    }
}
