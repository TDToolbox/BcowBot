package com.dominiquecat.bcowbot.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.CommandListener;

import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

public class CommandExceptionListener implements CommandListener {

	private static Logger logger = LoggerFactory.getLogger(CommandExceptionListener.class);
	
	@Override
	public void onCommandException(CommandEvent event, Command command, Throwable throwable) {
		if (throwable instanceof InsufficientPermissionException) {
			event.reply(throwable.getMessage());
		}
		
		logger.error("[" + event.getGuild().getName() + "/" + event.getChannel().getName() + "/" + event.getAuthor().getName() + "] " + command.getName().toString().toUpperCase() + " " + throwable.getMessage());
	}
	
}
