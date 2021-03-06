package Commands.CustomCommands.ChatterBot;

import Commands.SkypeChatCommand;
import Commands.SkypeSubCommand;
import Utils.ChatUtils;
import Utils.SkypeMessagingModes;
import com.skype.ChatMessage;

/**
 * Created by hhg on 13.10.2015.
 */
class SubCommand_botThinkSelf extends SkypeSubCommand {

	ChatterBotCommand command;

	public SubCommand_botThinkSelf( ChatterBotCommand command ) {
		this.command = command;
	}

	@Override
	public void commandExcecuted( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand commandc ) throws Exception {
		ChatMessage[] ms = message.getChat().getRecentChatMessages();

		ChatMessage m = null;
		for (ChatMessage mss : ms) {
			if (mss.getContent().startsWith("[" + command.getBotName() + " Says] ")) {
				m = mss;
			}
		}

		if (m != null) {
			System.out.println(" - " + command.getBotName() + " is thinking to himself about: " + m.getContent());
			String reply = command.getBotSession().think(m.getContent());

			if (reply != null && !reply.isEmpty()) {
				if (message.getChat() != null) {

					System.out.println(" - " + command.getBotName() + " replied: " + reply);
					ChatUtils.sendMessage(message.getChat(), "[" + command.getBotName() + " Says] " + reply);
				}
			}

		} else {
			ChatUtils.sendMessage(message.getChat(), "[" + command.getBotName() + "] No recent messages from " + command.getBotName() + ".");
		}
	}

	@Override
	public boolean canExcecute( ChatMessage message, String[] args, SkypeMessagingModes mode, ChatMessage.Type messageType, SkypeChatCommand commandc ) throws Exception {
		return messageType == ChatMessage.Type.SAID;
	}

	@Override
	public String commandPrefix() {
		return "-thinkSelf";
	}

}
