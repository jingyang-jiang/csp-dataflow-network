package finalProject;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractChannel implements Channel {

	protected BlockingQueue<Integer> aBlockingQueue;
	
	@Override
	public int take() throws InterruptedException {
		int result = aBlockingQueue.take();
		return result;
		
	}
	@Override
	public void put(int i) throws InterruptedException {
		aBlockingQueue.put(i);
	}

}
