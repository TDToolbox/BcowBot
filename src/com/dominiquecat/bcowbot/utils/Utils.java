package com.dominiquecat.bcowbot.utils;

import java.util.List;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;

import net.dv8tion.jda.api.entities.Member;

public class Utils {

	public static Member FindMember(CommandEvent event, String members) {
		List<Member> members_found = FinderUtil.findMembers(members, event.getGuild());
		
		if (members_found.size() > 1 && !members.isEmpty()) {
			event.reply("Too many users.. Please have only one user.");
			return null;
		}
		if (members_found.size() == 1) {
			return members_found.get(0);
		}
		
		return event.getMember();
	}
	
}
