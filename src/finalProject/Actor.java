package finalProject;

import java.util.concurrent.Callable;

public interface Actor extends Runnable{

	
	void ConnectIn (Channel c, int i ) ; 
	void ConnectOut(Channel c, int i) ; 
	void Fire(int ...is ) throws InterruptedException;
}
