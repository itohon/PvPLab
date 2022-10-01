package me.ventus.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GuildMemberJoin extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        EmbedBuilder join = new EmbedBuilder();

        join.setColor(Color.DARK_GRAY);
        join.setTitle("Welcome to " + event.getGuild().getName());
        join.setDescription("Welcome " + event.getMember().getAsMention() + " to " + event.getGuild().getName() + "!.\nNow we're **" + event.getGuild().getMemberCount() + "** with you!\n \n**User Information**\n• Username: " + event.getUser().getName() + "\n• ID: " + event.getMember().getId());
        join.setFooter(event.getGuild().getName() + " | Welcome", event.getGuild().getIconUrl());
        join.setThumbnail(event.getGuild().getIconUrl());

        event.getGuild().getTextChannelById("994295191760539785").sendMessageEmbeds(join.build()).queue();
        event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById("994295191072673894")).complete();
    }
}
