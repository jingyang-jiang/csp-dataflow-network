package finalProject;

import java.util.concurrent.SynchronousQueue;

public class SynchronousChannel extends AbstractChannel{

	
	public SynchronousChannel() {
		super();
		aBlockingQueue = new SynchronousQueue<>(true);
	}
	
	public SynchronousChannel(int i) {
		super();
		aBlockingQueue = new SynchronousQueue<>(true);
		set(i);
	}
	
	@Override
	public void set(int i) {
		try {
			aBlockingQueue.put(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Integer poll() {
			return aBlockingQueue.poll();
	}

	@Override
	public Integer peek() {
		
		return aBlockingQueue.peek();
	}


}
