package Commands.CustomCommands.PandoraBots;

import Commands.SkypeChatCommand;
import Commands.SkypeSubCommand;
import Utils.SkypeMessagingModes;
import com.google.code.chatterbotapi.ChatterBotType;
import com.skype.ChatMessage;

/**
 * Created by hhg on 13.10.2015.
 */
class SubCommand_changeBotID extends SkypeSubCommand {

	@Override
	public void commandExcecuted(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand command) throws Exception {
		CommandPandoraBots bots = null;

		if(command instanceof CommandPandoraBots){
			bots = (CommandPandoraBots)command;
		}

		if(clearString.equalsIgnoreCase("reset")){
			bots.pandoraBots = bots.factory.create(ChatterBotType.PANDORABOTS, CommandPandoraBots.DEFAULT_BOT_ID);
			bots.pandoraBotsSession = bots.pandoraBots.createSession();

			bots.botID = CommandPandoraBots.DEFAULT_BOT_ID;

			message.getChat().send("[PandoraBots] Bot id has been reset.");
			return;
		}

		if(clearString != null && clearString.length() == 16){
			bots.pandoraBots = bots.factory.create(ChatterBotType.PANDORABOTS, clearString);
			bots.pandoraBotsSession = bots.pandoraBots.createSession();

			bots.botID = clearString;

			message.getChat().send("[PandoraBots] Changed bot id to: " + clearString);
		}else{
			message.getChat().send("[PandoraBots] Bot id is invalid! Check from the PandoraBots website");
		}

	}

	@Override
	public boolean canExcecute(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand command) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "-changeBotID";
	}

}