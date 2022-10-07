package me.ventus;

import me.ventus.cmds.AnnounceCommand;
import me.ventus.cmds.ClearCommand;
import me.ventus.events.AnnounceModal;
import me.ventus.events.GuildMemberJoin;
import me.ventus.ticket.TicketButtonClick;
import me.ventus.ticket.TicketModal;
import me.ventus.ticket.TicketsCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

import static java.lang.Thread.sleep;

public class Main {
    public static JDA sharddMan;
    public static JDABuilder builder;
    public static String Prefix = "!";
    public static LabToken = System.getenv("TOKEN")

    public static void main(String[] args) throws LoginException, InterruptedException {
        builder = JDABuilder.createDefault(LabToken);

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("pvplab.us"));

        builder.addEventListeners(new GuildMemberJoin());
        builder.addEventListeners(new TicketButtonClick());
        builder.addEventListeners(new TicketsCommands());
        builder.addEventListeners(new ClearCommand());
        builder.addEventListeners(new AnnounceCommand());
        builder.addEventListeners(new AnnounceModal());
        builder.addEventListeners(new TicketModal());

        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);

            sharddMan = builder.build();

            sleep(5000);
            sharddMan.getGuildById("994295191072673892").upsertCommand("announce", "Create a Announce")
                    .addOption(OptionType.CHANNEL, "channel", "Add the channel where the announcement will be sent", true).queue();
            sharddMan.upsertCommand("suggest", "Make a suggestion")
                    .addOption(OptionType.STRING, "suggestion", "Add the Suggestion", true).queue();
            sharddMan.upsertCommand("setticket", "Set information Ticket").queue();


    }
}
