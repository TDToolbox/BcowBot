package com.dominiquecat.bcowbot.database.queries;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dominiquecat.bcowbot.BcowBot;
import com.dominiquecat.bcowbot.Config;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import net.dv8tion.jda.api.entities.Guild;

public class SuggestionQueries {

	private static Logger logger = LoggerFactory.getLogger(SuggestionQueries.class);
	private static RethinkDB r = RethinkDB.r;
	private static Connection db = BcowBot.getDatabase();

	public static HashMap InsertSuggestion(String uid, String mid, String suggestion) {
		HashMap response = r.db(Config.DATABASE_NAME).table("suggestions").insert(r.hashMap("uid", uid)
				.with("mid", mid)
				.with("text", suggestion)).run(db);
		
		return response;
	}
	
	public static HashMap RemoveSuggestion(String uid, Guild gid) {
		Cursor past = r.db(Config.DATABASE_NAME).table("suggestions").filter(r.hashMap("uid", uid)).getField("mid").limit(1).run(db);
		
		logger.info(past.toList().toString());
		
		if (past.toList().toString().isEmpty() == true || past.toList().size() == 0) {
			return r.hashMap("failed", true);
		}
		
 		String msgid = past.toList().get(0).toString();
		gid.getTextChannelById(Config.SUGGEST_CHANNEL_ID).retrieveMessageById(msgid).queue(message -> { message.delete().queue(); });
		HashMap response = r.db(Config.DATABASE_NAME).table("suggestions").filter(r.hashMap("mid", msgid)).limit(1).delete().run(db);
		
		past.close();
		
		return response;
	}
}
