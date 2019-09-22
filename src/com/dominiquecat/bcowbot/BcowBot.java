package com.dominiquecat.bcowbot;

import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dominiquecat.bcowbot.commands.general.InfoCommand;
import com.dominiquecat.bcowbot.commands.general.SuggestCommand;
import com.dominiquecat.bcowbot.commands.general.UserInfoCommand;
import com.dominiquecat.bcowbot.commands.moderation.SuggestionRemoveCommand;
import com.dominiquecat.bcowbot.database.DatabaseManager;
import com.dominiquecat.bcowbot.events.CommandExceptionListener;
import com.dominiquecat.bcowbot.events.MainListener;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BcowBot {

	private static RethinkDB r = RethinkDB.r;
	private static Connection db;
	private static Logger logger = LoggerFactory.getLogger(BcowBot.class);
	
	private static DatabaseManager databaseManager;
	
	public static void main(String[] args) throws LoginException, InterruptedException {
		init();
	}
	
	public static void init() throws LoginException, InterruptedException {
		// Create CommandClient instance
		CommandClientBuilder cbuilder = new CommandClientBuilder();
		// Create new EventWaiter
		EventWaiter waiter = new EventWaiter();
		// Create new Database connection
		db = new DatabaseManager().initDatabase();
		
		cbuilder.setActivity(Activity.playing("with Mallis"));
		cbuilder.setStatus(OnlineStatus.ONLINE);
		cbuilder.setOwnerId(Config.OWNER);
		cbuilder.setPrefix(Config.PREFIX);
		cbuilder.setListener(new CommandExceptionListener());
		cbuilder.addCommands(new InfoCommand(), new UserInfoCommand(), new SuggestCommand(), new SuggestionRemoveCommand());
		
		// Create new JDA instance
	    JDABuilder builder = new JDABuilder(Config.TOKEN);
	    
	    builder.addEventListeners(waiter, cbuilder.build(), new MainListener());
	    // Build the JDA instance
	    builder.build();
	}
	
	// Getters
	
	public static Connection getDatabase() {
		return db;
	}
}
