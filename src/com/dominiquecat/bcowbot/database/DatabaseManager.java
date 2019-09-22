package com.dominiquecat.bcowbot.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dominiquecat.bcowbot.Config;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class DatabaseManager {

	private static Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
	private static RethinkDB r = RethinkDB.r;
	private static Connection db;
	
	public Connection initDatabase() {
		db = r.connection().hostname(Config.HOSTNAME).port(Config.PORT).connect();
		
		logger.info("Database connected with hostname " + Config.HOSTNAME + " and on port " + Config.PORT);
		
		return db;
	}
	
}
