package commands;

import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    @Override
    //Fire when any slash command is run
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
     //   event.getChannel().sendMessage(command).queue();

        if(command.toLowerCase().equals("welcome")){
            String userName = event.getUser().getName();
            event.reply("Welcome to the server, **" + userName+ "**!").queue();
        } else if(command.toLowerCase().equals("p")) {
            // TODO: Implementation of Spotify feature
        }
    }

    //Guild commands  --- instantly updated (max 100 commands)
    // You chose between ^ or v
    //global commands -- up to an hour to update (unlimited amount of commands)


    // YOU HAVE TO REGISTER COMMANDS
    public void repeat(GenericGuildEvent event){
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "Get welcomed by the bot "));
        commandData.add(Commands.slash("p", "play any spotify music"));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }


    @Override
    // DOES NOT WORK ALL THE TIME
    // SO YOU HAVE TO PAIR IT WITH onGuildJoin
    public void onGuildReady(GuildReadyEvent event) {

        repeat(event);
    }
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
       repeat(event);
    }
}
