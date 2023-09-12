package listeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MonkeyListens extends ListenerAdapter {


     public void onMessageReactionAdd(MessageReactionAddEvent event)  {


         TextChannel monkeyCodesTextChannel = event.getGuild().getTextChannelById(1101618712022351883L);
         // TODO: Refactor
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getFormatted();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();

            String message = user.getAsTag() + " reacted to a message with " + emoji + " in the " + channelMention + " channel\n"
                    + "Jumplink: " + jumpLink;

         monkeyCodesTextChannel.sendMessage(message).queue();
     //     DefaultGuildChannelUnion defaultChannelUnion = event.getGuild().getDefaultChannel();
     //     TextChannel defaultChannel = defaultChannelUnion.asTextChannel()
         // defaultChannel.sendMessage(message).queue();

     }


     public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getAuthor().isBot()) {

            TextChannel monkeyCodesTextChannel = event.getGuild().getTextChannelById(1101618712022351883L);

            // TODO: Consider Refactoring

            String user = event.getAuthor().getAsTag();
            String channelMention = event.getChannel().getAsMention();
            String messageSent = event.getMessage().getContentRaw();

            switch (messageSent.toLowerCase()) {
                case "mute":
                    event.getMember().mute(true).queue();
                    return;
                case "unmute":
                    event.getMember().mute(false).queue();
                    return;
                case "deafen":
                    event.getMember().deafen(true).queue();
                    return;
                case "undeafen":
                    event.getMember().deafen(false).queue();
                    return;
            }

            String createdMessage = user + " sent " + "\"" +
                    messageSent + "\"" + " in the " + channelMention + " channel";

                    //TIME
                // String time = event.getMessage().getTimeCreated().toString();
                // String total = createdMessage + " at " + time;


                String total = createdMessage;

                    //
                //       if(messageSent.contains("ping")){
                //           event.getChannel().sendMessage("pong").queue();
                //                }


                monkeyCodesTextChannel.sendMessage(total).queue();

        }
    }



   // TODO: Privileged intents
        //     System.out.println(event.getMessage().getContentDisplay());


    @Override
    public void onUserTyping(UserTypingEvent event) {


    }

    @Override
    // Interrupt handler for when someone changes their online status
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
         User user = event.getUser();
        TextChannel monkeyCodesTextChannel = event.getGuild().getTextChannelById(1101618712022351883L);

         String message = user.getAsTag() + " updated their status to " + event.getNewOnlineStatus().name().toLowerCase() + "!";
        monkeyCodesTextChannel.sendMessage(message).queue();
    }

}
