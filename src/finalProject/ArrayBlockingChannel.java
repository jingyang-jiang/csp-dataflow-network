package finalProject;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingChannel extends AbstractChannel{

	
	
	private int aCHANNELCAPACITY = 1000;
	
	
	public ArrayBlockingChannel() {
		super();
		aBlockingQueue = new ArrayBlockingQueue<>(aCHANNELCAPACITY, true);
	}
	public ArrayBlockingChannel(int i) {
		super();
		aBlockingQueue = new ArrayBlockingQueue<>(aCHANNELCAPACITY, true);
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
