package com.dominiquecat.bcowbot.commands.general;

import java.awt.Color;
import java.util.HashMap;

import com.dominiquecat.bcowbot.Config;
import com.dominiquecat.bcowbot.database.queries.SuggestionQueries;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class SuggestCommand extends Command {

    public SuggestCommand() {
        this.name = "suggest";
        this.aliases = new String[]{"request", "featurerequest", "fr"};
        this.help = "Suggest a new feature or change";
        this.guildOnly = true;
        this.cooldown = 30;
    }
	
    @Override
    protected void execute(CommandEvent event)
    {
    	TextChannel channel = event.getJDA().getTextChannelById(Config.SUGGEST_CHANNEL_ID);
    	String cleaned = event.getArgs().replace("@", "@\u200b");
    	
    	EmbedBuilder e = new EmbedBuilder();
    	
    	e.setAuthor(event.getAuthor().getName() + " " + event.getAuthor().getId());
    	e.setColor(Color.DARK_GRAY);
    	e.setDescription(cleaned);
    
    	channel.sendMessage(new MessageBuilder().setEmbed(e.build()).build()).queue(message -> {message.addReaction("\u2705").queue(); 
    		message.addReaction("\u274e").queue(); 
    		SuggestionQueries.InsertSuggestion(event.getAuthor().getId(), message.getId(), cleaned);});
    	
    	event.reply("Suggestion sent to " + channel.getAsMention());
    }
}
