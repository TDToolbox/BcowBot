package com.dominiquecat.bcowbot.commands.general;

import com.dominiquecat.bcowbot.Config;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class InfoCommand extends Command {

    public InfoCommand() {
        this.name = "info";
        this.aliases = new String[]{"about"};
        this.help = "Get basic info about the bot";
        this.guildOnly = true;
    }
	
    @Override
    protected void execute(CommandEvent event)
    {
        event.reply("Info about `" + event.getJDA().getSelfUser().getName() + "`\n"
        		  + "Developer: `" + event.getJDA().getUserById(Config.OWNER).getName() + "`");
    }
}
