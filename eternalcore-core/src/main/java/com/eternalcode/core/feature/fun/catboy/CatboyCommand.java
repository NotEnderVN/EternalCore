package com.eternalcode.core.feature.fun.catboy;

import com.eternalcode.annotations.scan.command.DescriptionDocs;
import com.eternalcode.core.feature.catboy.CatboyService;
import com.eternalcode.core.injector.annotations.Inject;
import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Sender;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import java.util.Optional;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;

@Command(name = "catboy")
@Permission("eternalcore.catboy")
class CatboyCommand {

    private final CatboyService catboyService;

    @Inject
    CatboyCommand(CatboyService catboyService) {
        this.catboyService = catboyService;
    }

    @Execute
    @DescriptionDocs(description = "Mark yourself as a catboy.")
    void executeCatboySelf(@Sender Player player) {
        this.switchCatboy(player, Optional.empty());
    }

    @Execute
    @DescriptionDocs(description = "Mark yourself as a catboy.", arguments = "[type]")
    void executeCatboySelfType(@Sender Player player, @Arg("type") Cat.Type type) {
        this.switchCatboy(player, Optional.of(type));
    }

    @Execute
    @DescriptionDocs(description = "Mark a player as a catboy.", arguments = "<player>")
    @Permission("eternalcore.catboy.others")
    void executeCatboyOther(@Arg("target") Player target) {
        this.switchCatboy(target, Optional.empty());
    }

    @Execute
    @DescriptionDocs(description = "Mark a player as a catboy.", arguments = "<player> [type]")
    @Permission("eternalcore.catboy.others")
    void executeCatboyOtherType(@Arg("target") Player target, @Arg("type") Cat.Type type) {
        this.switchCatboy(target, Optional.of(type));
    }

    private void switchCatboy(Player target, Optional<Cat.Type> type) {
        if (this.catboyService.isCatboy(target.getUniqueId())) {
            if (type.isPresent()) {
                this.catboyService.changeCatboyType(target, type.get());
                return;
            }

            this.catboyService.unmarkAsCatboy(target);
            return;
        }

        this.catboyService.markAsCatboy(target, type.orElse(Cat.Type.BLACK));
    }

}
