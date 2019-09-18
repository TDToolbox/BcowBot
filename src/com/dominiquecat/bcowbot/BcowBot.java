package com.dominiquecat.bcowbot;

import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dominiquecat.bcowbot.commands.general.InfoCommand;
import com.dominiquecat.bcowbot.commands.general.UserInfoCommand;
import com.dominiquecat.bcowbot.events.CommandExceptionListener;
import com.dominiquecat.bcowbot.events.MainListener;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BcowBot {

	private static MongoClient mongoClient;
	private static MongoDatabase mongoDatabase;
	private static Logger logger;
	
	public static void main(String[] args) throws LoginException, InterruptedException {
		init();
	}
	
	public static void init() throws LoginException, InterruptedException {
		// Create Logger
		logger = LoggerFactory.getLogger(BcowBot.class);
		// Create CommandClient instance
		CommandClientBuilder cbuilder = new CommandClientBuilder();
		// Create new EventWaiter
		EventWaiter waiter = new EventWaiter();
		// Create new MongoDB connection
		mongoClient = MongoClients.create(Config.CONNECTION_STRING);
		mongoDatabase = mongoClient.getDatabase(Config.DATABASE_NAME);
		
		logger.info("MongoDB connected to database.");
		
		cbuilder.setActivity(Activity.playing("with Mallis"));
		cbuilder.setStatus(OnlineStatus.ONLINE);
		cbuilder.setOwnerId(Config.OWNER);
		cbuilder.setPrefix(Config.PREFIX);
		cbuilder.setListener(new CommandExceptionListener());
		cbuilder.addCommands(new InfoCommand(), new UserInfoCommand());
		
		// Create new JDA instance
	    JDABuilder builder = new JDABuilder(Config.TOKEN);
	    
	    builder.addEventListeners(waiter, cbuilder.build(), new MainListener());
	    // Build the JDA instance
	    builder.build();
	}
	
	// Getters
	
	public static MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}
	
	public static Logger getLogger() {
		return logger;
	}
}
