package com.dominiquecat.bcowbot.commands.general;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.dominiquecat.bcowbot.utils.Utils;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;

public class UserInfoCommand extends Command {

    public UserInfoCommand() {
        this.name = "userinfo";
        this.aliases = new String[]{"ui"};
        this.help = "Get info abot an user or yourself";
        this.guildOnly = true;
    }
	
    @Override
    protected void execute(CommandEvent event)
    {
    	Member member = Utils.FindMember(event, event.getArgs());
    	
    	String adj;
    	String nickname = member.getNickname();
    	
    	if (nickname == null) {
    		nickname = "None";
    	}
    	
		switch (member.getOnlineStatus()) {
			case OFFLINE:
				adj = "sleeping in ";
				break;
			case IDLE:
				adj = "afking in ";
				break;
			case ONLINE:
				adj = "lurking in ";
				break;
			case DO_NOT_DISTURB:
				adj = "annoyed in ";
				break;
			default:
				adj = "";
				break;
		}
    	
    	EmbedBuilder e = new EmbedBuilder();
    	
    	e.setColor(member.getColor());
    	e.setAuthor("Info for " + member.getEffectiveName());
    	e.setDescription(member.getEffectiveName() + " is currently " + adj + member.getOnlineStatus().getKey());
    	
    	e.addField("ID:", member.getId(), false);
    	e.addField("Bot:", "" + member.getUser().isBot(), false);
    	e.addField("Nickname:", "" + nickname, false);
    	e.addField("Joined discord:", member.getTimeCreated().format(DateTimeFormatter.ISO_LOCAL_DATE) + " (" + ChronoUnit.DAYS.between(member.getTimeCreated(), java.time.OffsetDateTime.now()) + "days" + ")", false);
    	e.addField("Joined server:", member.getTimeJoined().format(DateTimeFormatter.ISO_LOCAL_DATE)  + " (" + ChronoUnit.DAYS.between(member.getTimeJoined(), java.time.OffsetDateTime.now()) + "days" + ")", false);
    	
    	event.reply(new MessageBuilder().setEmbed(e.build()).build());
    }
}
