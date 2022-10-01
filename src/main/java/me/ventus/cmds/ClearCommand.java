package me.ventus.cmds;

import me.ventus.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClearCommand extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(Main.Prefix + "clear")) {
            if (args.length < 2) {

                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.DARK_GRAY);
                embed.setTitle("Moderation System");
                embed.setDescription("Usage: " + Main.Prefix + "clear [#number]");
                embed.setFooter("Meteor | Projects | Moderation System", event.getGuild().getIconUrl());
                event.getChannel().sendMessageEmbeds(embed.build()).queue();
            }
            else {
                try {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().purgeMessages(messages);
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.DARK_GRAY);
                    embed.setTitle("Moderation System");
                    embed.setDescription("✔️Successfully deleted " + args[1] + " messages.");
                    embed.setFooter(event.getGuild().getName() + " | Moderation", event.getGuild().getIconUrl());
                    event.getChannel().sendMessageEmbeds(embed.build()).queue();

                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    Date date = new Date();
                    Guild guild = event.getGuild();
                    EmbedBuilder embedTeam = new EmbedBuilder();
                    embedTeam.setColor(Color.DARK_GRAY);
                    embedTeam.setTitle("Moderation System");
                    embedTeam.addField("Deleted by", event.getMember().getAsMention(), true);
                    embedTeam.addField("Date", formatter.format(date), true);
                    guild.getTextChannelById("992983113095450675").sendMessageEmbeds(embedTeam.build()).queue();
                }
                catch (IllegalArgumentException e) {
                    if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrival")) {

                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setColor(Color.DARK_GRAY);
                        embed.setTitle("Moderation System");
                        embed.setDescription("The maximum amount of deleting messages is 100");
                        embed.setFooter("Meteor | Projects | Moderation System", event.getGuild().getIconUrl());
                        event.getChannel().sendMessageEmbeds(embed.build()).queue();
                    }
                    else {
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setColor(Color.DARK_GRAY);
                        embed.setTitle("Moderation System");
                        embed.setDescription("You can't delete messages from 2 weeks ago.");
                        embed.setFooter(event.getGuild().getName()+ " | Moderation", event.getGuild().getIconUrl());
                        event.getChannel().sendMessageEmbeds(embed.build()).queue();
                    }
                    }
                }
            }
        }
    }

