package finalProject;

public abstract class AbstractActor implements Actor{

	protected ChannelInterface[] aInputChannels;
	protected ChannelInterface[] aOutputChannels; 
	
	@Override
	public void ConnectIn(ChannelInterface c, int i) {
		assert i >= 0 && i< aInputChannels.length;
		aInputChannels[i] = c ;
		
	}
	
	@Override
	public void ConnectOut(ChannelInterface c, int i) {
		assert i>=0 && i< aOutputChannels.length;
		aOutputChannels[i] = c ;
		
	}
	
	/**
	 * @return if an actor is wired up properly 
	 */
	protected boolean isComplete() {
		for (ChannelInterface channel : aInputChannels) {
			if (channel.isNull()) return false;
		}
		for (ChannelInterface channel : aOutputChannels) {
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
		aInputChannels = new ChannelInterface[a];
		for(ChannelInterface c:aInputChannels)c = ChannelInterface.NullChannel;
		aOutputChannels = new ChannelInterface[b];
		for(ChannelInterface c:aOutputChannels)c = ChannelInterface.NullChannel;
	}
}
