package me.ventus.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class AnnounceModal extends ListenerAdapter {

    public void  onModalInteraction(@NotNull ModalInteractionEvent event) {
    if (event.getModalId().equals("announce")) {

        String title = event.getValue("title").getAsString();
        String description = event.getValue("description").getAsString();
        String footer = event.getValue("footer").getAsString();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.DARK_GRAY);
        embed.setTitle(title);
        embed.setDescription(description);
        embed.setFooter(footer);

        event.replyEmbeds(embed.build()).queue();

    }
}
}
