package com.dominiquecat.bcowbot.commands.moderation;

import java.util.HashMap;

import com.dominiquecat.bcowbot.Config;
import com.dominiquecat.bcowbot.database.queries.SuggestionQueries;
import com.dominiquecat.bcowbot.utils.Utils;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

public class SuggestionRemoveCommand extends Command {
	
	    public SuggestionRemoveCommand() {
	        this.name = "remove";
	        this.aliases = new String[]{"suggestionremove", "sr"};
	        this.help = "Remove a suggestion";
	        this.requiredRole = Config.STAFF_ROLE;
	        this.guildOnly = true;
	    }
		
	    @Override
	    protected void execute(CommandEvent event)
	    {
	    	Member member = Utils.FindMember(event, event.getArgs());
	    			
	    	HashMap r = SuggestionQueries.RemoveSuggestion(member.getId(), event.getGuild());
	    	
	    	if (r.containsKey("failed")) {
	    		event.reply(member.getEffectiveName() + " has no active suggestions");
	    		return;
	    	}
	    	
	    	event.reply("Suggestion from " + member.getEffectiveName() + " removed.");
	}

}
