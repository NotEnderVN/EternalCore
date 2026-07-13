package com.eternalcode.core.feature.fun.catboy;

import com.eternalcode.core.litecommand.argument.AbstractViewerArgument;
import com.eternalcode.core.injector.annotations.Inject;
import com.eternalcode.core.injector.annotations.lite.LiteArgument;
import com.eternalcode.core.translation.Translation;
import com.eternalcode.core.translation.TranslationManager;
import dev.rollczi.litecommands.argument.Argument;
import dev.rollczi.litecommands.argument.parser.ParseResult;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.suggestion.SuggestionContext;
import dev.rollczi.litecommands.suggestion.SuggestionResult;
import java.util.Locale;
import org.bukkit.Registry;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Cat;
import org.bukkit.command.CommandSender;

@LiteArgument(type = Cat.Type.class)
class CatTypeArgument extends AbstractViewerArgument<Cat.Type> {

    @Inject
    CatTypeArgument(TranslationManager translationManager) {
        super(translationManager);
    }

    @Override
    public ParseResult<Cat.Type> parse(Invocation<CommandSender> invocation, String argument, Translation translation) {
        NamespacedKey key;
        try {
            key = NamespacedKey.minecraft(argument.toLowerCase(Locale.ROOT));
        }
        catch (IllegalArgumentException exception) {
            return ParseResult.failure(translation.argument().invalidCatType());
        }

        Cat.Type type = Registry.CAT_VARIANT.get(key);

        if (type == null) {
            return ParseResult.failure(translation.argument().invalidCatType());
        }

        return ParseResult.success(type);
    }

    @Override
    public SuggestionResult suggest(Invocation<CommandSender> invocation, Argument<Cat.Type> argument, SuggestionContext context) {
        return Registry.CAT_VARIANT.stream()
            .map(type -> type.getKey().getKey())
            .collect(SuggestionResult.collector());
    }

}
