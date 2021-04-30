package finalProject;

import java.util.concurrent.TimeUnit;

public class Add extends AbstractActor{

	public Add() {
		super();
		initialize(2, 1);
	}
	
	@Override
	public void run() {
		assert isComplete();

		while (!Simulation.end) {
			try {
				int x = aInputChannels[0].take();
				int y = aInputChannels[1].take();
				Fire(x,y);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void Fire(int ...is) throws InterruptedException {
		aInputChannels[0].put(is[0]+is[1]);
	}

}
