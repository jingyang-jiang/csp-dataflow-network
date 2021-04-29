package finalProject;

public class Fork extends AbstractActor{

	public Fork() {
		super();
		initialize(1, 2);
	}

	@Override
	public void run() {
		assert isComplete();
		while (true) {
			try {
				int x = aInputChannels[0].take();
				Fire(x);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}


	@Override
	public void Fire(int... is) throws InterruptedException {
		for(int i : is) {
			aOutputChannels[0].put(i);
			aOutputChannels[1].put(i);
		}
		
	}

}
