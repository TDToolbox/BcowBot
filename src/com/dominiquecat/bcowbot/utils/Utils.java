package com.dominiquecat.bcowbot.utils;

import java.util.List;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;

import net.dv8tion.jda.api.entities.Member;

public class Utils {

	public static Member FindMember(CommandEvent event, String members) {
		if (members.isEmpty()) {
			return event.getMember();
		}
		
		Member found = FinderUtil.findMembers(members, event.getGuild()).get(0);
		
		return found;
	}
	
}
