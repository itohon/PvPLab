package me.ventus.ticket;

import me.ventus.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class TicketsCommands extends ListenerAdapter {

    public void  onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equals("setticket")) {

            String title = event.getValue("title").getAsString();
            String description = event.getValue("description").getAsString();
            String footer = event.getValue("footer").getAsString();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.DARK_GRAY);
            embed.setTitle(title);
            embed.setDescription(description);
            embed.setFooter(footer);

            event.replyEmbeds(embed.build()).addActionRow(Button.of(ButtonStyle.SECONDARY, "openticket", "Open the Ticket", Emoji.fromUnicode("✅"))).queue();
        }
    }
}



