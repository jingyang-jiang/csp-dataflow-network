package finalProject;

public interface Actor extends Runnable{

	
	void ConnectIn (ChannelInterface c, int i ) ; 
	void ConnectOut(ChannelInterface c, int i) ; 
	void Fire(int ...is ) throws InterruptedException;
}
