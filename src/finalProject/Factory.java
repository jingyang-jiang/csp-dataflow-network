package finalProject;

public class Factory {


	Actor createActor (String name) { 
		switch (name) {
		case "add": {
			return new Add();
		}
		case "cdr":{
			return new Cdr();
		}
		case "equals":{
			return new Equals();
		}
		case "fork":{
			return new Fork();
		}
		case "inc":{
			return new Inc();
		}
		case "input":{
			return new Input();
		}
		case "lessthan":{
			return new LessThan();
		}
		case "merge":{
			return new Merge();
		}
		case "output":{
			return new Output();
		}
		case "switch":{
			return new Switch();
		}
		default:
			throw new IllegalArgumentException("There is no defined actor named: " + name);
		}
	}
	static void connectActors (Channel pChannel,Actor sender, Actor receiver, int senderChannelNum, int receiverChannelNum) {
		sender.ConnectOut(pChannel, senderChannelNum);
		receiver.ConnectIn(pChannel, receiverChannelNum);
	}
    Channel createChannel() {
		return new ArrayBlockingChannel();
		
	}
}
