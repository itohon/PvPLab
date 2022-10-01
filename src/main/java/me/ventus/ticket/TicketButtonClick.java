package me.ventus.ticket;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public class TicketButtonClick extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        event.deferEdit().queue();
        if (event.getButton().getId().equals("openticket")) {
            String roles = String.valueOf(event.getMember().getRoles());
            if (!roles.contains("BlockTicket")) {
                int min = 1000;
                int max = 99999;
                int random_int = (int) ThreadLocalRandom.current().nextInt(1000, 100000);
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                Date date = new Date();
                Guild guild = event.getGuild();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.DARK_GRAY);
                embed.setTitle(event.getGuild().getName() + " | Support Center");
                embed.setDescription("Hello, Our support team will respond as soon as possible.");
                embed.setFooter(event.getGuild().getName() + " | Ticket System", event.getGuild().getIconUrl());
                guild.createTextChannel("Ticket-" + event.getUser().getName(), guild.getCategoryById("994295192402268203"))
                        .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                        .complete().sendMessageEmbeds(embed.build()).setActionRow(closeButton()).queue();
                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById("994295191072673893")).complete();
                EmbedBuilder embedTeam = new EmbedBuilder();
                embedTeam.setColor(Color.DARK_GRAY);
                embedTeam.setTitle("Moderation System | Ticket");
                embedTeam.addField("Created by", event.getMember().getAsMention(), true);
                embedTeam.addField("Date", formatter.format(date), true);
                guild.getTextChannelById("994295191567613995").sendMessageEmbeds(embedTeam.build()).queue();
            } else {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.DARK_GRAY);
                embed.setTitle("Ticket System");
                embed.setDescription("you already have a ticket");
                embed.setFooter(event.getGuild().getName() + " | Ticket", event.getGuild().getIconUrl());
                event.getUser().openPrivateChannel().complete().sendMessageEmbeds(embed.build()).queue();

            }

        } else {
            if (event.getButton().getId().equals("closebutton")) {
                event.getInteraction().getChannel().delete().queue();
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById("994295191072673893")).complete();

                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.DARK_GRAY);
                embed.setTitle("Close a Ticket");
                embed.setDescription("You just closed the ticket");
                embed.setFooter(event.getGuild().getName() + " | Ticket", event.getGuild().getIconUrl());
                event.getUser().openPrivateChannel().complete().sendMessageEmbeds(embed.build()).queue();
            }
        }
    }


    private Button closeButton() {
        return Button.danger("closebutton", "Close");
    }
}


