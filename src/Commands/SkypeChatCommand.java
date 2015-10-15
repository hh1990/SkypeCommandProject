package Commands;
/*
* Project: Random Java Creations
* Package: Commands
* Created: 26.06.2015
*/

import Utils.CommandFilter;
import Utils.SkypeMessagingModes;
import com.skype.ChatMessage;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class SkypeChatCommand {

	public ArrayList<SkypeSubCommand> subCommands = new ArrayList<>();

	protected boolean enabled = true;

	public boolean isEnabled(){
		return enabled;
	}

	public void setEnabled(boolean enabled){
		for(SkypeSubCommand sub : subCommands){
			sub.enabled = enabled;
		}

		this.enabled = enabled;
	}

	public abstract void commandExcecuted(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception;
	public abstract boolean canExcecute(ChatMessage message, String clearString, SkypeMessagingModes mode, ChatMessage.Type messageType) throws Exception;

	public abstract String commandPrefix();

	public abstract CommandFilter CommandMessageFilter();

	public ActionListener nonStandardSetting(){return null;}
}
