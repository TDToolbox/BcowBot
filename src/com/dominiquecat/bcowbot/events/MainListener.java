package com.dominiquecat.bcowbot.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dominiquecat.bcowbot.Config;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MainListener extends ListenerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(MainListener.class);
	
	@Override
	public void onReady(ReadyEvent e) {
		logger.info("Bot ready!\n"
					+ "Prefix: " + Config.PREFIX + "\n"
					+ "Guilds: " + e.getGuildTotalCount() + "\n"
					+ "Members: " + e.getJDA().getUsers().size());
	}
	
}
