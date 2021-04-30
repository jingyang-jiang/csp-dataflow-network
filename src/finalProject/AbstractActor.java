package finalProject;

public abstract class AbstractActor implements Actor{

	protected Channel[] aInputChannels;
	protected Channel[] aOutputChannels; 
	
	@Override
	public void ConnectIn(Channel c, int i) {
		assert i >= 0 && i< aInputChannels.length;
		aInputChannels[i] = c ;
		
	}
	
	@Override
	public void ConnectOut(Channel c, int i) {
		assert i>=0 && i< aOutputChannels.length;
		aOutputChannels[i] = c ;
		
	}
	
	/**
	 * @return if an actor is wired up properly 
	 */
	protected boolean isComplete() {
		for (Channel channel : aInputChannels) {
			if (channel.isNull()) return false;
		}
		for (Channel channel : aOutputChannels) {
			if (channel.isNull()) return false;
		} 
		return true ;
	}
	/**this method initializes an actor with a input channels and b output channels.
	 * @param a
	 * @param b
	 */
	protected void initialize(int a,int b) {
		assert a>=0 && b>=0; 
		aInputChannels = new Channel[a];
		for(Channel c:aInputChannels)c = Channel.NullChannel;
		aOutputChannels = new Channel[b];
		for(Channel c:aOutputChannels)c = Channel.NullChannel;
	}
}
