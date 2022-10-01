package me.ventus.cmds;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class AnnounceCommand extends ListenerAdapter {

    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("announce")) {

            TextInput title = TextInput.create("title", "Title", TextInputStyle.SHORT)
                    .setMinLength(1)
                    .setRequired(true)
                    .build();

            TextInput description = TextInput.create("description", "Description", TextInputStyle.PARAGRAPH)
                    .setMinLength(10)
                    .setMaxLength(1000)
                    .setRequired(true)
                    .setPlaceholder("Put a cool Description")
                    .build();

            TextInput footer = TextInput.create("footer", "Footer", TextInputStyle.SHORT)
                    .setMinLength(1)
                    .setMaxLength(20)
                    .setRequired(false)
                    .build();

            Modal modal = Modal.create("announce", "Announce Create")
                    .addActionRows(ActionRow.of(title), ActionRow.of(description), ActionRow.of(footer))
                    .build();

            event.replyModal(modal).queue();
        }
    }

}
