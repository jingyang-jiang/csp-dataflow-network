package finalProject;

public class Input extends AbstractActor{

	
	public Input() {
		super();
		initialize(1, 1);
	}
	
	@Override
	public void run() {
		assert isComplete();
		
		while(!Simulation.end) {
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
		for(int i: is)aOutputChannels[0].put(i);		
	}



}
